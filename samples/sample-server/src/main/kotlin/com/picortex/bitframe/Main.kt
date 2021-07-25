package com.picortex.bitframe

import bitframe.Application
import bitframe.StaticModule
import bitframe.actions.Action
import bitframe.http.HttpResponse
import bitframe.http.HttpRoute
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK

class Cutomer(
    val name: String,
    val email: String
)
fun main() {

    val createAction = Action(
        name = "Create",
        params = mapOf(
            "name" to "",
            "email" to ""
        ),
        route = HttpRoute(Post, "/") {
            HttpResponse(OK, "Success")
        }
    )

    val updateAction = Action(
        name = "Update",
        params = mapOf(),
        route = HttpRoute(Put, "/") {
            HttpResponse(OK, it.body ?: "")
        }
    )

    val createInvoiceAction = Action(
        name = "Create",
        params = mapOf(),
        route = HttpRoute(Post, "/") {
            HttpResponse(Created, it.body ?: "")
        }
    )

    val fetchAction = Action(
        name = "Fetch",
        params = mapOf(),
        route = HttpRoute(Get, "/") {
            HttpResponse(OK, "Andy and George")
        }
    )

    val customersModule = StaticModule("Customers", createAction, updateAction, fetchAction)
    val invoiceModule = StaticModule("Invoice", createInvoiceAction)
    val app = Application(listOf(customersModule, invoiceModule))
    app.start(8080)
}