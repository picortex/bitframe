import datetime.toSimpleDateTime
import expect.expect
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test

class DateParserToTimestamp {
    @Test
    fun should_parse_string_to_local_date_to_simple_date_time() {
        val timezone: TimeZone = TimeZone.UTC
        val date = LocalDate.parse("2022-04-01").toSimpleDateTime(timezone)
        expect(date.format("{DD}/{MM}/{YYYY}", timezone)).toBe("01/04/2022")
    }

    @Test
    fun should_parse_string_to_local_date_time_to_simple_date_time() {
        val timezone: TimeZone = TimeZone.UTC
        val date = LocalDateTime.parse("2022-04-01T14:00").toSimpleDateTime(timezone)
        expect(date.format("{DD}/{MM}/{YYYY}", timezone)).toBe("01/04/2022")
    }
}