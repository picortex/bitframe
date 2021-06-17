package com.picortex.bitframe

import bitframe.Application
import bitframe.StaticModule
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlinx.serialization.mapper.Mapper

class Person(val name: String)

fun main() {
    val create = Action(
        name = "create",
        params = mapOf(
            "name" to null
        ),
        route = HttpRoute(Post, "/") {
            val body = it.body ?: return@HttpRoute HttpResponse(BadRequest, "Body is not present")
            val map = Mapper.decodeFromString(body)
            val name = map["name"] as? String ?: return@HttpRoute HttpResponse(BadRequest, "name is not set")
            val p = Person(name)
            HttpResponse(OK, p.toString())
        }
    )

    val fetch = Action(
        name = "fetch",
        params = mapOf(),
        route = HttpRoute(Get, "/") {
            HttpResponse(OK, listOf("Andy", "Luge").toString())
        }
    )

    val module = StaticModule("People", create, fetch)
    val app = Application(listOf(module))
    app.start()
}