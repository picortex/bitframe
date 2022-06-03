package emiiter

import emitter.SimpleEmitter
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class EmitterApiTest {
    @Test
    fun can_be_constructed_from_non_functional_languages_like_java_and_wont_run_before_collection() {
        SimpleEmitter<Int> {
            repeat(10) { i ->
                it.emit(i)
            }
        }
    }

    @Test
    fun can_be_constructed_from_non_functional_languages_like_java() {
        SimpleEmitter<Int> {
            repeat(10) { i -> it.emit(i) }
        }.collect { println("Collecting: $it") }
    }

    @Test
    fun can_catch_errors_and_recover() = runTest {
        SimpleEmitter<String> {
            it.emit("Hello")
            throw IllegalStateException("test error")
        }.onError { it, _ ->
            it.emit("World")
        }.collect {
            println("collected: $it")
        }
        delay(2000)
    }

    @Test
    fun can_map_from_one_type_to_another() = runTest {
        SimpleEmitter<Int> {
            it.emit(1)
            it.emit(2)
        }.map {
            "$it as string"
        }.collect {
            println("collected: $it")
        }
        delay(2000)
    }

    @Test
    fun can_block_thread_to_collect() = runTest {
        SimpleEmitter<Int> {
            it.emit(1)
            it.emit(2)
        }.ceaseAndCollect {
            println("Ceased to collect: $it")
        }
    }
}