package search

import expect.expect
import kotlinx.collections.interoperable.emptyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import pimonitor.client.search.SearchIntent
import pimonitor.client.search.SearchState
import pimonitor.client.search.SearchFeedback
import pimonitor.client.search.SearchMode
import utils.PiMonitorMockScope
import kotlin.test.Test

class SearchViewModelTest {
    val scope = PiMonitorMockScope()
    val vm = scope.search.viewModel

    @Test
    fun should_start_with_a_empty_results_and_a_pending_searh_state() = runTest {
        val state = vm.ui.value
        expect(state).toBe(SearchState(status = SearchFeedback.Pending, results = emptyList()))
    }

    @Test
    fun should_debounce_searches() = runTest {
        withContext(Dispatchers.Default) {
            var counts = 0
            vm.ui.watch { counts++ }
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "t"))
            delay(100)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "te"))
            delay(100)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "tes"))
            delay(6000)
            expect(counts).toBe(4)
        }
    }

    @Test
    fun should_search_after_debounce() = runTest {
        withContext(Dispatchers.Default) {
            var counts = 0
            vm.ui.watch { counts++ }
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "t"))
            delay(6000)
            expect(counts).toBe(2)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "te"))
            delay(100)
            vm.post(SearchIntent.Search(SearchMode.DEBOUNCING, "tes"))
            delay(6000)
            expect(counts).toBe(5)
        }
    }
}