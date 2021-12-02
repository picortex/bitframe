package pimonitor.monitors

import bitframe.authentication.users.UserRef
import bitframe.daos.conditions.Condition
import identifier.Email
import kotlinx.coroutines.delay
import later.later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.MonitorDao

class MonitorDaoInMemory(
    val config: MonitorDaoInMemoryConfig = MonitorDaoInMemoryConfig()
) : MonitorDao {
    private val scope = config.scope
    private val monitors = config.monitors
    private val lock = config.lock
    override fun create(params: SignUpParams, ref: UserRef) = scope.later {
        lock.lock()
        delay(config.simulationTime)
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
        lock.unlock()
        monitor
    }

    override fun all(where: Condition<String, Any>?) = scope.later {
        delay(config.simulationTime)
        if (where?.lhs == "userRef.uid") {
            val uid = where.rhs.toString()
            monitors.values.filter {
                when (it) {
                    is CooperateMonitor -> it.contacts.any { c -> c.userRef.uid == uid }
                    is IndividualMonitor -> it.userRef.uid == uid
                }
            }
        } else monitors.values.toList()
    }
}