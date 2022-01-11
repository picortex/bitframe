package pimonitor.evaluation.businesses

import kotlinx.css.JustifyContent
import kotlinx.css.justifyContent
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.client.monitors.currentMonitorOrNull
import pimonitor.client.monitors.currentMonitorOrThrow
import pimonitor.evaluation.businesses.exports.BusinessesReactScope
import pimonitor.evaluation.businesses.exports.BusinessesScope
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.Link
import react.useEffectOnce
import reakt.*
import styled.css
import styled.styledDiv
import useViewModelState

private external interface BusinessContainerProps : Props {
    var scope: BusinessesReactScope
}

private val BusinessContainer = fc<BusinessContainerProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val loadBusinesses = scope.loadBusinesses
    val useBusinessAddedEvent = scope.useBusinessAddedEvent
    val monitor = scope.service.monitorSession.currentMonitorOrNull
    useBusinessAddedEvent { loadBusinesses() }
    useEffectOnce { loadBusinesses() }
    when (val state = scope.useStateFromViewModel()) {
        is BusinessesState.Loading -> LoadingBox(state.message)
        is BusinessesState.Businesses -> styledDiv {
            FlexBox {
                css { justifyContent = JustifyContent.flexEnd }
                ContainedButton("Create") { viewModel.post(BusinessesIntent.ShowCreateBusinessForm) }
                val link = "/invite/${monitor?.uid}"
                Link {
                    attrs.to = link
                    +link
                }
            }
            if (state.table.isEmpty) EmptyBusiness()
            else BusinessTable(state.table)
        }
        is BusinessesState.Success -> SuccessBox(state.message)
        is BusinessesState.Failure -> ErrorBox(state.cause)
    }
}

fun RBuilder.BusinessContainer(config: PiMonitorViewModelConfig) = child(BusinessContainer) {
    attrs.scope = BusinessesReactScope(config)
}