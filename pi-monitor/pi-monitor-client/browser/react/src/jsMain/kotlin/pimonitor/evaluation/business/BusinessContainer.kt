package pimonitor.evaluation.business

import kotlinx.css.JustifyContent
import kotlinx.css.justifyContent
import pimonitor.PiMonitorService
import react.Props
import react.RBuilder
import react.fc
import react.useEffectOnce
import reakt.ContainedButton
import reakt.FlexBox
import reakt.LoadingBox
import reakt.SuccessBox
import styled.css
import styled.styledDiv
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
        is BusinessesState.Businesses -> styledDiv {
            FlexBox {
                css { justifyContent = JustifyContent.flexEnd }
                ContainedButton("Create") {}
            }
            if (state.data.isEmpty()) EmptyBusiness()
            else BusinessList(state.data)
        }
        is BusinessesState.Success -> SuccessBox(state.message)
    }
}

fun RBuilder.BusinessContainer(service: PiMonitorService) = child(BusinessContainer) {
    attrs.scope = BusinessesScope(service.businesses)
}