package pimonitor.evaluation.business

import kotlinx.css.JustifyContent
import kotlinx.css.justifyContent
import pimonitor.PiMonitorService
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.monitored.MonitoredBusiness
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.routeLink
import react.useEffectOnce
import reakt.*
import styled.css
import styled.styledDiv
import useViewModelState

private external interface BusinessContainerProps : Props {
    var scope: BusinessesScope
}

private val BusinessContainer = fc<BusinessContainerProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val loadBusinesses = scope.loadBusinesses
    val useBusinessAddedEvent = scope.useBusinessAddedEvent
    val monitor = scope.service.monitors.currentMonitorOrNull
    useBusinessAddedEvent { loadBusinesses() }
    useEffectOnce { loadBusinesses() }
    when (val state = useViewModelState(viewModel)) {
        is BusinessesState.Loading -> LoadingBox(state.message)
        is BusinessesState.Businesses -> styledDiv {
            FlexBox {
                css { justifyContent = JustifyContent.flexEnd }
                ContainedButton("Create") { viewModel.post(BusinessesIntent.ShowBusinessForm) }
                val link = "/invite/${monitor?.uid}"
                routeLink(to = link) { +link }
            }
            if (state.table.isEmpty) EmptyBusiness()
            else BusinessTable(state.table)
        }
        is BusinessesState.BusinessForm -> AddBusiness(scope.service)
        is BusinessesState.Success -> SuccessBox(state.message)
        is BusinessesState.Failure -> ErrorBox(state.cause)
    }
}

fun RBuilder.BusinessContainer(service: PiMonitorService) = child(BusinessContainer) {
    attrs.scope = BusinessesScope(service)
}