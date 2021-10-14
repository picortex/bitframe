package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.Condition
import bitframe.daos.config.InMemoryDaoConfig
import contacts.Email
import kotlinx.coroutines.delay
import later.Later
import later.later

class MonitorDaoInMemory(
    private val monitors: MutableMap<String, Monitor> = mutableMapOf(),
    val config: InMemoryDaoConfig
) : MonitorDao {
    private val scope = config.scope
    override fun create(params: SignUpParams, ref: UserRef) = scope.later {
        delay(config.simulationTime.toLong())
        val uid = "monitor-${monitors.size + 1}"
        val monitor = when (params) {
            is SignUpParams.Individual -> IndividualMonitor(uid, params.name, Email(params.email), ref)
            is SignUpParams.Business -> CooperateMonitor(
                uid,
                name = params.businessName,
                email = Email(params.individualEmail),
                contacts = listOf(
                    CooperateMonitor.ContactPerson(
                        uid,
                        name = params.individualName,
                        email = Email(params.individualEmail),
                        ref
                    )
                )
            )
        }
        monitors["monitor-${monitors.size + 1}"] = monitor
        monitor
    }

    override fun all(where: Condition<String, Any>?): Later<List<Monitor>> {
        TODO("Not yet implemented")
    }
}