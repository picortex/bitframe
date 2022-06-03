package pimonitor.core.dashboards

import datetime.Date
import later.Later

interface DashboardProvider {

    fun technicalDashboardOf(
        /** start time in milli seconds */
        start: Date,
        /** end time in milli seconds*/
        end: Date
    ): Later<OperationalDashboard>

    fun technicalDifferenceDashboardOf(
        /** start time in milli seconds */
        start: Date,
        /** end time in milli seconds*/
        end: Date
    ): Later<OperationalDifferenceBoard>
}