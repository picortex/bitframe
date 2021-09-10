package pimonitor.authentication

import bitframe.MiniService
import later.Later
import pimonitor.Monitor

interface SignUpService : MiniService {
    fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<Monitor>
}