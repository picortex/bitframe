package bitframe.server.controller

import bitframe.actors.modal.Savable
import bitframe.server.http.HttpResponse
import bitframe.server.http.toHttpResponse
import kotlinx.collections.interoperable.List
import kotlinx.serialization.builtins.ListSerializer
import later.await
import response.response

open class GenericController<D : Savable>(val config: GenericControllerConfig<D>) {
    private val service by lazy { config.service }
    private val serializer by lazy { config.serializer }
    suspend fun create(body: String?): HttpResponse = response<D> {
        val json = body ?: reject("Can't perform a creation with an empty body")
        val input = config.json.decodeFromString(serializer, json)
        resolve(service.create(input).await())
    }.toHttpResponse(serializer)

    suspend fun load(body: String?): HttpResponse = response<D> {
        TODO("Haven't implemented generic loading just yet")
    }.toHttpResponse(serializer)

    suspend fun loadMany(body: String?): HttpResponse = response<List<D>> {
        val all = service.all().await()
        resolve(all)
    }.toHttpResponse(ListSerializer(serializer))

    suspend fun update(body: String?): HttpResponse = response<D> {
        TODO("Haven't implemented generic update just yet")
    }.toHttpResponse(serializer)

    suspend fun delete(body: String?): HttpResponse = response<D> {
        TODO("Haven't implemented generic delete just yet")
    }.toHttpResponse(serializer)
}