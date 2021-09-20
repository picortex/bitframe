package pimonitor.evaulation.business

import bitframe.MiniService
import later.Later
import pimonitor.Monitor

interface BusinessService : MiniService {
    fun all(): Later<List<Monitor.Business>>
}