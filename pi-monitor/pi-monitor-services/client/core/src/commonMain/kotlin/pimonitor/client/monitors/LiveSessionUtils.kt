package pimonitor.client.monitors

import live.Live
import live.value

val Live<Session>.currentMonitorOrThrow get() = value.currentMonitorOrThrow()

val Live<Session>.currentMonitorOrNull get() = value.currentMonitorOrNull()