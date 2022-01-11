package pimonitor.client.monitors

import live.Live

val Live<Session>.currentMonitorOrThrow get() = value.currentMonitorOrThrow()

val Live<Session>.currentMonitorOrNull get() = value.currentMonitorOrNull()