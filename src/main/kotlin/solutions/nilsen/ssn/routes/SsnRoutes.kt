package solutions.nilsen.ssn.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate
import solutions.nilsen.ssn.app.Generate

fun Route.ssnRouting() {
    route("/generate") {
        get {
            call.respond(Generate.fromToday())
        }
        get("{date?}") {
            val dateString = call.parameters["date"]
                ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)

            val date = try { LocalDate.parse(dateString) } catch (e: Exception) {
                return@get call.respondText("Bad Request: ${e.message}", status = HttpStatusCode.BadRequest)
            }

            call.respond(Generate.fromDate(date))
        }
    }
}