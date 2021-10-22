package pimonitor.evaluation.business

import pimonitor.PiMonitorService
import pimonitor.evaluation.business.exports.CreateBusinessScope
import pimonitor.evaluation.business.forms.CreateBusinessState.*
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.ErrorBox
import reakt.LoadingBox
import reakt.SuccessBox
import useViewModelState
import pimonitor.evaluation.business.forms.CreateBusinessIntent as Intent

private external interface AddBusinessProps : Props {
    var uid: String?
    var scope: CreateBusinessScope
}

private val AddBusiness = fc<AddBusinessProps> { props ->
    val scope = props.scope
    val vm = scope.viewModel
    useEffectOnce { scope.showForm(props.uid) }
    when (val state = useViewModelState(vm)) {
        is Loading -> LoadingBox(state.message)
        is Form -> CreateBusinessForm(
            state = state,
            onSubmit = { vm.post(Intent.SubmitForm(it)) }
        )
        is Success -> SuccessBox(state.message)
        is Failure -> ErrorBox(state.cause)
    }
}

internal fun RBuilder.AddBusiness(service: PiMonitorService, uid: String? = null) = child(AddBusiness) {
    attrs.scope = CreateBusinessScope(service)
    attrs.uid = uid
}