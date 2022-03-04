package search

import expect.expect
import expect.toBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import pimonitor.client.search.SearchIntent
import pimonitor.client.search.SearchState
import pimonitor.client.search.SearchMode
import utils.PiMonitorMockScope
import kotlin.test.Test

class SearchViewModelTest {
    val scope = PiMonitorMockScope()
    val vm = scope.search.viewModel

    @Test
    fun should_start_with_a_empty_results_and_a_pending_search_state() = runTest {
        val state = vm.ui.value
        expect(state).toBe<SearchState.Pending>()
    }

    @Test
    fun should_debounce_searches() = runTest {
        withContext(Dispatchers.Default) {
            var counts = 0
            vm.ui.watch { counts++ }
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "t"))
            delay(50)
            expect(counts).toBe(1)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "te"))
            delay(10)
            expect(counts).toBe(2)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "tes"))
            delay(10)
            expect(counts).toBe(3)
            delay(2100)
            expect(counts).toBe(4)
        }
    }

    @Test
    fun should_search_after_debounce() = runTest {
        withContext(Dispatchers.Default) {
            var count = 0
            vm.ui.watch { count++ }
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "t"))
            delay(10)
            expect(count).toBe(1)
            delay(2100)
            expect(count).toBe(2)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "te"))
            delay(10)
            expect(count).toBe(3)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "tes"))
            delay(10)
            expect(count).toBe(4)
            delay(2100)
            expect(count).toBe(5)
        }
    }
}