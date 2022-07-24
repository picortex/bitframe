package collections

import expect.expect
import presenters.collections.internal.CollectionPaginator
import presenters.collections.internal.SelectorImpl
import viewmodel.ViewModelConfig
import kotlin.test.Test

class SelectorTest {

    @Test
    fun should_select_a_row_by_number() {
        val paginator = CollectionPaginator(Person.List)
        val selector = SelectorImpl(paginator, ViewModelConfig())

        paginator.loadFirstPage()

        selector.select(row = 1)
        expect(selector.isSelected(row = 1)).toBe(true)
        expect(selector.isSelected(row = 2)).toBe(false)

        selector.select(row = 2)
        expect(selector.isSelected(row = 1)).toBe(false, "Row 1 was selected")
        expect(selector.isSelected(row = 2)).toBe(true, "Row 2 was not selected")
    }

    @Test
    fun should_select_multiple_rows_by_number() {
        val paginator = CollectionPaginator(Person.List)
        val selector = SelectorImpl(paginator, ViewModelConfig())

        paginator.loadFirstPage()

        selector.addItemToSelection(1)
        selector.addItemToSelection(2)
        expect(selector.isSelected(row = 2)).toBe(true, "Row 2 was not selected")
        expect(selector.isSelected(row = 1)).toBe(true, "Row 1 was not selected")
    }

    @Test
    fun should_select_multiple_rows_by_number_from_different_pages() {
        val paginator = CollectionPaginator(Person.List)
        val selector = SelectorImpl(paginator, ViewModelConfig())

        paginator.loadFirstPage()

        selector.addItemToSelection(1)
        selector.addItemToSelection(2)

        paginator.loadNextPage()
        selector.addItemToSelection(1)
        selector.addItemToSelection(2)
        expect(selector.isSelected(row = 2, page = 1)).toBe(true, "Row 2 was not selected")
        expect(selector.isSelected(row = 1, page = 1)).toBe(true, "Row 1 was not selected")
        expect(selector.isSelected(row = 2)).toBe(true, "Row 2 was not selected")
        expect(selector.isSelected(row = 1)).toBe(true, "Row 1 was not selected")
    }

    @Test
    fun should_be_able_to_clear_selection_of_the_current_page_only() {
        val paginator = CollectionPaginator(Person.List)
        val selector = SelectorImpl(paginator, ViewModelConfig())

        paginator.loadFirstPage()
        expect(paginator.currentPageOrNull?.number).toBe(1)

        selector.addItemToSelection(1)
        selector.addItemToSelection(2)

        paginator.loadNextPage()
        expect(paginator.currentPageOrNull?.number).toBe(2)
        selector.addItemToSelection(1)
        selector.addItemToSelection(2)

        expect(selector.isSelected(row = 2)).toBe(true, "Row 2 / Page 2: was not selected")
        expect(selector.isSelected(row = 1)).toBe(true, "Row 1 / Page 2: was not selected")

        selector.unSelectAllFromCurrentPage() // unSelectAllFromPage(2)
        expect(selector.isSelected(row = 2, page = 1)).toBe(true, "Row 2 / Page 1: Was not selected")
        expect(selector.isSelected(row = 1, page = 1)).toBe(true, "Row 1 / Page 1: Was not selected")

        expect(selector.isSelected(row = 2)).toBe(false, "Row 2 / Page 2: was selected")
        expect(selector.isSelected(row = 1)).toBe(false, "Row 1 / Page 2: was selected")
    }
}