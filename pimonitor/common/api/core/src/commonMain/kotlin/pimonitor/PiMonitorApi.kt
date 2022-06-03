package pimonitor

import bitframe.client.BitframeApi
import authenticator.AuthenticatorApi

interface PiMonitorApi : BitframeApi {
    val authenticator: AuthenticatorApi
}