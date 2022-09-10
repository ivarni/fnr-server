package solutions.nilsen.ssn.routes

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import solutions.nilsen.ssn.models.Ssn
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SsnRouteTests {
    val mapper = jacksonObjectMapper()

    @BeforeTest
    fun setup() {
        mapper.registerModule(JavaTimeModule())
    }

    @Test
    fun testGenerateWithNoDate() = testApplication {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

        val response = client.get("/generate")

        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue("All dates should match today's date") {
            mapper.readValue<List<Ssn>>(response.bodyAsText()).all { it.date == today }
        }
    }

    @Test
    fun testGenerateWithGivenDate() = testApplication {
        val dateString = "2007-02-01"
        val date = LocalDate.parse(dateString)

        val response = client.get("/generate/${dateString}")

        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue("All dates should match given date") {
            mapper.readValue<List<Ssn>>(response.bodyAsText()).all { it.date == date }
        }
    }
}