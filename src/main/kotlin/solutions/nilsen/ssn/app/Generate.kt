package solutions.nilsen.ssn.app

import kotlinx.datetime.*
import solutions.nilsen.ssn.models.Gender
import solutions.nilsen.ssn.models.Ssn
import java.time.format.DateTimeFormatter

object Generate {
    val formatter = DateTimeFormatter.ofPattern("ddMMyy")

    // Source: https://no.wikipedia.org/wiki/F%C3%B8dselsnummer
    val allowedIntervals = mapOf(
        Pair(1900..1999, 0..499),
        Pair(2000..2039, 500..999),
        Pair(1940..1999, 900..999)
    )

    fun fromToday(): List<Ssn> {
        return fromDate(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    }

    fun fromDate(date: LocalDate): List<Ssn> {
        val now = Clock.System.todayIn(TimeZone.currentSystemDefault());
        if (date > now || date.year < 1900) {
            throw IllegalArgumentException("Invalid year: ${date.year}")
        }

        val base = formatter.format(date.toJavaLocalDate())

        return allowedIntervals.filter { it.key.contains(date.year) }.values.flatMap { it }.mapNotNull {
            val padded = it.toString().padStart(3, '0')
            val checksum = Checksum.calculate(base + padded)
            if (checksum != null) {
                val ssn = base + padded + checksum
                val gender = if (padded.last().code % 2 != 0) Gender.MALE else Gender.FEMALE
                Ssn(ssn, date, gender)
            } else null
        }
    }

}