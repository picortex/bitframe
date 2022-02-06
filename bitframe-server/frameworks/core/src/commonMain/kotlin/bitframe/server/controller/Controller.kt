package bitframe.server.controller

import bitframe.server.http.HttpResponse

interface Controller {
    suspend fun create(body: String?): HttpResponse

    suspend fun load(body: String?): HttpResponse

    suspend fun loadMany(body: String?): HttpResponse

    suspend fun update(body: String?): HttpResponse

    suspend fun delete(body: String?): HttpResponse

    companion object {
        operator fun <D : Any> invoke(config: ControllerConfig<D>): Controller = ControllerImpl(config)
    }
}