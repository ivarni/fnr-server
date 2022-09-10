package solutions.nilsen.ssn.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

enum class Gender { MALE, FEMALE }

@Serializable
data class Ssn(val ssn: String, val date: LocalDate, val gender: Gender) {
}