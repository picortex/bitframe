package pimonitor.evaluation.business

import pimonitor.PiMonitorService
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.LoadingBox
import reakt.SuccessBox
import useViewModelState

private external interface BusinessContainerProps : Props {
    var scope: BusinessesScope
}

private val BusinessContainer = fc<BusinessContainerProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    useEffectOnce { scope.loadBusiness() }
    when (val state = useViewModelState(viewModel)) {
        is BusinessesState.Loading -> LoadingBox(state.message)
        is BusinessesState.Businesses -> if (state.data.isNotEmpty()) BusinessList(state.data) else EmptyBusiness()
        is BusinessesState.Success -> SuccessBox(state.message)
    }
}

fun RBuilder.BusinessContainer(service: PiMonitorService) = child(BusinessContainer) {
    attrs.scope = BusinessesScope(service.businesses)
}