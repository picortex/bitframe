package search

import expect.expect
import kotlinx.collections.interoperable.emptyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import pimonitor.client.search.SearchIntent
import pimonitor.client.search.SearchState
import utils.PiMonitorMockScope
import kotlin.test.Test

class SearchViewModelTest {
    val scope = PiMonitorMockScope()
    val vm = scope.search.viewModel

    @Test
    fun should_start_with_an_empty_state_and_no_loading() = runTest {
        val state = vm.ui.value
        expect(state).toBe(SearchState(loading = null, results = emptyList()))
    }

    @Test
    fun should_debounce_searches() = runTest {
        withContext(Dispatchers.Default) {
            var counts = 0
            vm.ui.watch { counts++ }
            vm.post(SearchIntent.SearchDebouncing("t"))
            delay(100)
            vm.post(SearchIntent.SearchDebouncing("te"))
            delay(100)
            vm.post(SearchIntent.SearchDebouncing("tes"))
            delay(6000)
            expect(counts).toBe(4)
        }
    }

    @Test
    fun should_search_after_debounce() = runTest {
        withContext(Dispatchers.Default) {
            var counts = 0
            vm.ui.watch { counts++ }
            vm.post(SearchIntent.SearchDebouncing("t"))
            delay(6000)
            expect(counts).toBe(2)
            vm.post(SearchIntent.SearchDebouncing("te"))
            delay(100)
            vm.post(SearchIntent.SearchDebouncing("tes"))
            delay(6000)
            expect(counts).toBe(5)
        }
    }
}