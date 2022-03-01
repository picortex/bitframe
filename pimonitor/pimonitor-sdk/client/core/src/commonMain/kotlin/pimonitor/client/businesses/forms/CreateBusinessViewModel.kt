package pimonitor.client.businesses.forms

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

class CreateBusinessViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    private val businessService by lazy { config.service.businesses }

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.SubmitForm -> submitForm(i)
    }

    private fun CoroutineScope.submitForm(i: Intent.SubmitForm) = launch {
        val state = ui.value.copy(
            fields = ui.value.fields.copy(i.params)
        )
        flow {
            emit(state.copy(status = Feedback.Loading("Adding ${i.params.businessName}, please wait . . .")))
            businessService.create(i.params).await()
            val phase2 = state.copy(
                status = Feedback.Success("${i.params.businessName} has successfully been added"),
                fields = CreateBusinessFields()
            )
            emit(phase2)
        }.catch {
            emit(state.copy(status = Feedback.Failure(it)))
            delay(config.viewModel.recoveryTime)
            emit(state.copy(status = Feedback.None))
        }.collect { ui.value = it }
    }
}