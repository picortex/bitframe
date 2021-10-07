package pimonitor.monitors

import later.Later

interface MonitorDao {
    fun create(params: SignUpParams): Later<Monitor>
}