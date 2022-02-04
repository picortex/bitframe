package bitframe.server.controller

import bitframe.server.http.HttpResponse

class ControllerImpl : Controller {
    override suspend fun create(body: String?): HttpResponse {
        TODO()
    }

    override suspend fun load(body: String?): HttpResponse {
        TODO()
    }

    override suspend fun loadMany(body: String?): HttpResponse {
        TODO()
    }

    override suspend fun update(body: String?): HttpResponse {
        TODO()
    }

    override suspend fun delete(body: String?): HttpResponse {
        TODO()
    }
}