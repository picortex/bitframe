package bitframe.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

expect object Dispatchers {
    val Universal: CoroutineDispatcher
    val Main: MainCoroutineDispatcher
}
