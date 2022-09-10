package solutions.nilsen.ssn.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import solutions.nilsen.ssn.routes.*

fun Application.configureRouting() {
    routing {
        ssnRouting()
    }
}
