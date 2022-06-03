#!/usr/bin/env kotlin

data class Server(
    val name: String,
    val host: String
)

val servers = listOf(
    Server("localhost", "127.0.0.1:8080"),
    Server("Staging", "65.21.254.230:90"),
    Server("Production", "65.21.254.230")
)

val url = System.getenv("API_URL")

val API_URL = if (url != null) {
    println("Auto populating to $url")
    url
} else {
    println("Select server to populate")
    servers.forEachIndexed { index, server ->
        println("${index + 1}\t ${server.name} \t${server.host}")
    }
    "http://" + servers[readln().toInt() - 1].host
}

val process = Runtime.getRuntime().exec(
    "./gradlew :pimonitor-monitor-app-populator:runDebug",
    arrayOf("API_URL=$API_URL") + System.getenv().map { (key, value) -> "$key=$value" }
)

process.inputStream.transferTo(System.out)

process.onExit().get()