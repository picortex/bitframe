package pimonitor.evaluation.business

import logging.console
import pimonitor.PiMonitorService
import pimonitor.evaluation.business.forms.CreateBusinessState
import pimonitor.evaluation.business.forms.CreateBusinessState.*
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.ErrorBox
import reakt.LoadingBox
import useViewModelState

private external interface AddBusinessProps : Props {
    var uid: String?
    var scope: AddBusinessScope
}

private val AddBusiness = fc<AddBusinessProps> { props ->
    val scope = props.scope
    val vm = scope.viewModel
    useEffectOnce {
        scope.showForm(props.uid)
    }
    when (val state = useViewModelState(vm)) {
        is Loading -> LoadingBox(state.message)
        is Form -> CreateBusinessForm(
            state = state,
            onSubmit = {
                console.log(it)
            }
        )
        is Failure -> ErrorBox(state.cause)
    }
}

internal fun RBuilder.AddBusiness(service: PiMonitorService, uid: String? = null) = child(AddBusiness) {
    attrs.scope = AddBusinessScope(service)
    attrs.uid = uid
}