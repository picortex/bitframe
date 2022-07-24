package collections

import expect.expect
import presenters.collections.Paginator
import presenters.collections.internal.CollectionPaginator
import presenters.collections.internal.SinglePagePaginator
import kotlin.test.Test

class PaginatorTest {
    @Test
    fun single_page_paginator_should_always_return_the_same_list() {
        val p: Paginator<Person> = SinglePagePaginator(List(5) { Person("Andy $it", age = 12 + it) })
        expect(p.currentPageOrNull).toBe(null)
        p.refresh()
        expect(p.currentPageOrNull?.capacity).toBe(5)
    }

    @Test
    fun paginator_should_be_able_to_paginate_through_different_pages() {
        val p: Paginator<Person> = CollectionPaginator(Person.List)
        expect(p.currentPageOrNull).toBe(null)

        p.refresh()
        expect(p.currentPageOrNull?.number).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadNextPage()
        expect(p.currentPageOrNull?.number).toBe(2)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadPreviousPage()
        expect(p.currentPageOrNull?.number).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadPage(2)
        expect(p.currentPageOrNull?.number).toBe(2)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadNextPage()
        expect(p.currentPageOrNull?.number).toBe(3)
        expect(p.currentPageOrNull?.items?.size).toBe(5)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadFirstPage()
        expect(p.currentPageOrNull?.number).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.loadLastPage()
        expect(p.currentPageOrNull?.number).toBe(3)
        expect(p.currentPageOrNull?.items?.size).toBe(5)
        expect(p.currentPageOrNull?.capacity).toBe(10)
    }
}