@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE", "NON_EXPORTABLE_TYPE")

import bitframe.authentication.signin.exports.SignInScope
import bitframe.events.EventBus
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.evaluation.business.BusinessesScope
import react.useEffectOnce

fun signIn(service: PiMonitorService) = SignInScope(service.signIn)

fun signUp(service: PiMonitorService) = SignUpScope(service.signUp)

fun business(service: PiMonitorService) = BusinessesScope(service.businesses)

internal fun <D> useEventHandler(
    bus: EventBus,
    eventId: String,
    callback: (D) -> Unit
) = useEffectOnce {
    val subscriber = bus.subscribe(eventId, callback)
    cleanup { subscriber.unsubscribe() }
}

fun useSignUpEvent(
    service: PiMonitorService,
    callback: (SignUpResult) -> Unit
) = useEventHandler(service.bus, SignUpService.SIGN_UP_EVENT_ID, callback)