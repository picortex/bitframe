package pimonitor.authentication

import bitframe.MiniService
import later.Later
import pimonitor.Monitor

interface SignUpService : MiniService {
    fun registerWith(business: Monitor.Business, person: Monitor.Person): Later<Monitor>
}