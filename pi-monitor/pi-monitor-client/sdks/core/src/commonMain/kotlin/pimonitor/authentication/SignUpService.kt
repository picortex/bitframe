package pimonitor.authentication

import bitframe.MiniService
import bitframe.authentication.signin.LoginConundrum
import later.Later
import pimonitor.IndividualRegistrationParams
import pimonitor.Monitor

interface SignUpService : MiniService {
    fun registerIndividuallyAs(person: IndividualRegistrationParams): Later<LoginConundrum>
    fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum>
}