package unit

import akkounts.utils.toYYYYMMDD
import expect.expect
import kotlinx.datetime.LocalDate
import kotlin.test.Test

class LocalDateUtilsTest {
    @Test
    fun should_convert_to_YYYY_MM_DD() {
        val date = LocalDate(2021, 4, 1)
        expect(date.toYYYYMMDD()).toBe("2021-04-01")
    }
}