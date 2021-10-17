package pimonitor.evaluation.business

import kotlinx.css.JustifyContent
import kotlinx.css.justifyContent
import pimonitor.PiMonitorService
import pimonitor.monitors.Session
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.routeLink
import react.useEffectOnce
import reakt.ContainedButton
import reakt.FlexBox
import reakt.LoadingBox
import reakt.SuccessBox
import styled.css
import styled.styledDiv
import useLive
import useViewModelState

private external interface BusinessContainerProps : Props {
    var scope: BusinessesScope
}

private val BusinessContainer = fc<BusinessContainerProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val monitor = when (val session = useLive(scope.service.monitors.session)) {
        Session.Unknown -> null
        is Session.Active -> session.monitor
    }
    useEffectOnce { scope.loadBusiness() }
    when (val state = useViewModelState(viewModel)) {
        is BusinessesState.Loading -> LoadingBox(state.message)
        is BusinessesState.Businesses -> styledDiv {
            FlexBox {
                css { justifyContent = JustifyContent.flexEnd }
                ContainedButton("Create") { viewModel.post(BusinessesIntent.ShowBusinessForm) }
                val link = "/invite/${monitor?.uid}"
                routeLink(to = link) { +link }
            }
            if (state.data.isEmpty()) EmptyBusiness()
            else BusinessList(state.data)
        }
        is BusinessesState.BusinessForm -> AddBusiness(scope.service)
        is BusinessesState.Success -> SuccessBox(state.message)
    }
}

fun RBuilder.BusinessContainer(service: PiMonitorService) = child(BusinessContainer) {
    attrs.scope = BusinessesScope(service)
}