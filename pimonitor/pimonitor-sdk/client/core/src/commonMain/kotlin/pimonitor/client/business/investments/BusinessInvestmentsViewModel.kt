package pimonitor.client.business.investments

import bitframe.client.UIScopeConfig
import kash.Currency
import kash.MoneyFormatterOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.investments.BusinessInvestmentsIntent.*
import pimonitor.client.business.investments.dialogs.CaptureInvestmentDialog
import pimonitor.client.business.investments.dialogs.CreateDisbursementDialog
import pimonitor.core.business.investments.Investment
import pimonitor.core.business.investments.params.CreateInvestmentsRawParamsContextual
import pimonitor.core.business.investments.params.toValidatedCreateDisbursementParams
import pimonitor.core.business.investments.params.toValidatedCreateInvestmentsParams
import presenters.cases.CrowdState
import presenters.cases.Feedback
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class BusinessInvestmentsViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessInvestmentsIntent, CrowdState<Investment>>(CrowdState(), config.viewModel) {
    private val api get() = config.service
    lateinit var businessId: String
    override fun CoroutineScope.execute(i: BusinessInvestmentsIntent): Any = when (i) {
        is ExitDialog -> exitDialog()
        is LoadAllInvestments -> loadAllInvestments(i)
        is ShowCreateInvestmentForm -> showCreateInvestmentForm(i)
        is SendCreateInvestmentForm -> sendCreateInvestmentForm(i)
        is ShowCreateDisbursementForm -> showCreateDisbursementForm(i)
        is SendCreateDisbursementForm -> sendCreateDisbursementForm(i)
    }

    private fun exitDialog() {
        ui.value = ui.value.copy(dialog = null)
    }

    private fun CoroutineScope.sendCreateDisbursementForm(i: SendCreateDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Creating a disbursement, please wait . . ."), dialog = null))
            val investmentId = state.focus?.uid ?: error("Failed to send disbursement form: Couldn't get investment Id from viewmodel context")
            val params = i.params.toValidatedCreateDisbursementParams(investmentId)
            api.businessInvestments.disburse(params).await()
            emit(state.copy(status = Feedback.Success("Success. Loading your investment, please wait . . ."), dialog = null))
            val table = investmentsTable(api.businessInvestments.all(businessId).await())
            emit(state.copy(status = Feedback.None, table = table, dialog = null))
        }.catchAndCollectToUI(state, i)
    }

    private fun showCreateDisbursementForm(i: ShowCreateDisbursementForm) {
        ui.value = ui.value.copy(
            status = Feedback.None,
            focus = i.investment,
            dialog = CreateDisbursementDialog(i.investment.name) {
                onCancel { post(ExitDialog) }
                onSubmit { post(SendCreateDisbursementForm(it)) }
            }
        )
    }

    private fun CoroutineScope.sendCreateInvestmentForm(i: SendCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Submitting your form, please wait . . ."), dialog = null))
            val params = i.params.toValidatedCreateInvestmentsParams(businessId)
            api.businessInvestments.capture(params).await()
            emit(state.copy(status = Feedback.Success("Investment captured successfully. Loading investments, please wait . . ."), dialog = null))
            val table = investmentsTable(api.businessInvestments.all(params.businessId).await())
            emit(state.copy(status = Feedback.None, table = table, dialog = null))
        }.catchAndCollectToUI(state, i)
    }

    private fun CoroutineScope.showCreateInvestmentForm(i: ShowCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Preparing form, please wait . . ."), dialog = null))
            val business = api.businesses.load(i.businessId).await()
            val dialog = CaptureInvestmentDialog(business.name) {
                onCancel { post(ExitDialog) }
                onSubmit { post(SendCreateInvestmentForm(it)) }
            }
            emit(state.copy(status = Feedback.None, dialog = dialog))
        }.catchAndCollectToUI(state, i)
    }

    private fun CoroutineScope.loadAllInvestments(i: LoadAllInvestments) = launch {
        businessId = i.businessId
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Loading investments, please wait . . ."), dialog = null))
            val table = investmentsTable(api.businessInvestments.all(i.businessId).await())
            emit(state.copy(status = Feedback.None, table = table, dialog = null))
        }.catchAndCollectToUI(state, i)
    }

    private suspend fun Flow<CrowdState<Investment>>.catchAndCollectToUI(state: CrowdState<Investment>, i: BusinessInvestmentsIntent) = catch {
        emit(state.copy(status = Feedback.Failure(it) {
            onRetry { post(i) }
        }, dialog = null))
    }.collect {
        ui.value = it
    }

    private fun investmentsTable(data: List<Investment>) = tableOf(data) {
        primaryAction("Add Investment") {
            post(ShowCreateInvestmentForm(businessId))
        }
        singleAction("Issue Disbursement") {
            post(ShowCreateDisbursementForm(it.data))
        }
        selectable()
        column("Name") { it.data.name }
        column("Source") { it.data.source }
        column("Type") { it.data.type }
        column("Amount") {
            val options = MoneyFormatterOptions(prefix = "", decimals = 0)
            Currency.ZAR.of(it.data.amount).toFormattedString(options)
        }
        column("Created By") { it.data.createdBy.name }
        actionsColumn("Actions") {
            action("Issue Disbursement") { post(ShowCreateDisbursementForm(it.data)) }
        }
    }
}
