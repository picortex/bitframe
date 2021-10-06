package pimonitor.evaluation.businesses

import later.Later
import pimonitor.Monitor

class BusinessServiceImpl : BusinessService() {
    override fun all(): Later<List<Monitor.Business>> {
        TODO("Not yet implemented")
    }
}