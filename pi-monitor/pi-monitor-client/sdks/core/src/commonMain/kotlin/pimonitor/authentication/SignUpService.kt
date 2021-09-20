package pimonitor.authentication

import bitframe.MiniService
import later.Later
import pimonitor.Monitor

interface SignUpService : MiniService {
    fun registerIndividuallyAs(person: Monitor.Person): Later<Monitor.Person>
    fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<Monitor>
}