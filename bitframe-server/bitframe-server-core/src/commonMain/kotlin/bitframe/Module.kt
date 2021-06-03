package bitframe

import bitframe.http.HttpRoute

interface Module {
    val routes: List<HttpRoute>
}