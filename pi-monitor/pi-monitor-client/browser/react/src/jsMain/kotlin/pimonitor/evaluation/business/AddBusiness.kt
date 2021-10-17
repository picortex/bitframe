package pimonitor.evaluation.business

import logging.console
import pimonitor.PiMonitorService
import pimonitor.evaluation.business.forms.CreateBusinessState
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.LoadingBox
import useViewModelState

private external interface AddBusinessProps : Props {
    var scope: AddBusinessScope
}

private val AddBusiness = fc<AddBusinessProps> { props ->
    val scope = props.scope
    val vm = scope.viewModel
    useEffectOnce {
        scope.showForm(null)
    }
    when (val state = useViewModelState(vm)) {
        is CreateBusinessState.Loading -> LoadingBox(state.message)
        is CreateBusinessState.Form -> CreateBusinessForm(
            state = state,
            onSubmit = {
                console.log(it)
            }
        )
    }
}

internal fun RBuilder.AddBusiness(service: PiMonitorService) = child(AddBusiness) {
    attrs.scope = AddBusinessScope(service)
}