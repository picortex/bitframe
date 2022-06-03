package pimonitor

import bitframe.client.configurators.ApiConfigurator
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import pimonitor.client.api
import validation.BlankFieldException

fun main(args: Array<String>) = runBlocking<Unit> {
    val server = System.getenv("API_URL") ?: throw BlankFieldException("API_URL")

    val configurator: ApiConfigurator.() -> Unit = {
        appId = "populator"
        url = server
        logging {
            console = true
        }
    }
    println("Populating to: ${server}}")

    val ops = listOf(
        Operator(
            name = "Mohammed Majapa",
            email = "mmajapa@gmail.com"
        ),
        Operator(
            name = "Steven Sajja",
            email = "ssajja@gmail.com"
        ),
        Operator(
            name = "Lugendo Paul",
            email = "luge@gmail.com"
        )
    )

    ops.forEach { operator ->
        val api = api(configurator)
        api.populateFor(operator)
    }
//    ops.map { operator ->
//        async {
//            val api = api(configurator)
//            api.populateFor(operator)
//        }
//    }.awaitAll()
}