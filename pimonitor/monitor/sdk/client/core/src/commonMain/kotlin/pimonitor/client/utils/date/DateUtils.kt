package pimonitor.client.utils.date

import datetime.Date

private val DEFAULT_PATTERN = "{DD}{th}-{MMMM}-{YYYY}"

fun Date.toDefaultFormat() = format(DEFAULT_PATTERN)