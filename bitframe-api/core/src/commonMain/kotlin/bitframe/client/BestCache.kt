package bitframe.client

import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

expect fun BestCache(namespace: String, json: Json, scope: CoroutineScope): Cache