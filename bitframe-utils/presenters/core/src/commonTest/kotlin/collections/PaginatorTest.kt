package collections

import expect.expect
import koncurrent.Later
import koncurrent.MockExecutor
import presenters.collections.Page
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
        expect(p.currentPageOrNull?.no).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.next()
        expect(p.currentPageOrNull?.no).toBe(2)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.previous()
        expect(p.currentPageOrNull?.no).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.page(2)
        expect(p.currentPageOrNull?.no).toBe(2)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.next()
        expect(p.currentPageOrNull?.no).toBe(3)
        expect(p.currentPageOrNull?.items?.size).toBe(5)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.first()
        expect(p.currentPageOrNull?.no).toBe(1)
        expect(p.currentPageOrNull?.items?.size).toBe(10)
        expect(p.currentPageOrNull?.capacity).toBe(10)

        p.last()
        expect(p.currentPageOrNull?.no).toBe(3)
        expect(p.currentPageOrNull?.items?.size).toBe(5)
        expect(p.currentPageOrNull?.capacity).toBe(10)
    }
}