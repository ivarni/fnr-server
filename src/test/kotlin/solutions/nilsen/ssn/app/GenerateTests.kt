package solutions.nilsen.ssn.app

import kotlinx.datetime.*
import solutions.nilsen.ssn.models.Gender
import java.lang.IllegalArgumentException
import java.time.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GenerateTests {

    @Test
    fun testGeneratesSsnsOfLength11() {
        val list = Generate.fromToday()

        assertTrue {
            list.all { it.ssn.length == 11 }
        }
    }

    @Test
    fun testGeneratesValidSsns() {
        val list = Generate.fromToday()

        assertTrue {
            list.all { Checksum.validate(it.ssn) }
        }
    }

    @Test
    fun testGeneratesAllPossibleSsnsFromGivenDate() {
        val date = LocalDate(2000, Month.JANUARY, 1);
        val list = Generate.fromDate(date)

        assertEquals(413, list.size)
        assertTrue {
            list.all { it.date == date }
        }
    }

    @Test
    fun testDoesNotAllowYearsBefore1900() {
        assertFailsWith<IllegalArgumentException> {
            Generate.fromDate(LocalDate(1899, Month.DECEMBER, 31))
        }
    }

    @Test
    fun testDoesNotAllowFutureDate() {
        assertFailsWith<IllegalArgumentException> {
            Generate.fromDate(Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(DatePeriod(days = 1)))
        }
    }

    @Test
    fun testHasGenderMarker() {
        val list = Generate.fromToday();
        val grouped = list.groupingBy { it.gender }.eachCount()

        assertTrue { grouped.get(Gender.MALE)!! > 0 }
        assertTrue { grouped.get(Gender.FEMALE)!! > 0 }
    }
}