package solutions.nilsen.ssn

import io.ktor.server.application.*
import solutions.nilsen.ssn.plugins.configureCORS
import solutions.nilsen.ssn.plugins.configureRouting
import solutions.nilsen.ssn.plugins.configureSerialization

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureCORS()
    configureSerialization()
    configureRouting()
}
