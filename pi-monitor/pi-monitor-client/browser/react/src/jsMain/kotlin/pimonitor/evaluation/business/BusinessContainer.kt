package pimonitor.evaluation.business

import pimonitor.PiMonitorService
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.Grid
import reakt.LoadingBox
import reakt.SuccessBox
import reakt.centerContent
import styled.css
import styled.styledDiv
import useViewModelState

private external interface BusinessContainerProps : Props {
    var scope: BusinessScope
}

private val BusinessContainer = fc<BusinessContainerProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    useEffectOnce { scope.loadBusiness() }
    when (val state = useViewModelState(viewModel)) {
        is BusinessState.Loading -> LoadingBox(state.message)
        is BusinessState.Businesses -> if (state.data.isNotEmpty()) BusinessList(state.data) else EmptyBusiness()
        is BusinessState.Success -> SuccessBox(state.message)
    }
}

fun RBuilder.BusinessContainer(service: PiMonitorService) = child(BusinessContainer) {
    attrs.scope = BusinessScope(service.business)
}