package chapter6

import chapter6.book.Book
import chapter6.book.BookProcess
import chapter6.book.BookRepository
import chapter6.book.BookStore
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BookStoreTest {
    @Test
    fun emptyOrder() {
        val bookRepo: BookRepository = mock()
        val process: BookProcess = mock()
        val bookStore = BookStore(bookRepo, process)

        val orderMap = mutableMapOf<String, Int?>()
        val overview = bookStore.getPriceForCart(orderMap)

        assertThat(overview).isNotNull
        assertThat(overview?.totalPrice).isEqualTo(0)
        assertThat(overview?.getUnavailable()).isEmpty()
    }

    @Test
    fun nullOrder() {
        val bookRepo: BookRepository = mock()
        val process: BookProcess = mock()
        val bookStore = BookStore(bookRepo, process)

        val overview = bookStore.getPriceForCart(null)

        assertThat(overview).isNull()
    }

    @Test
    fun moreComplexOrder() {
        val bookRepo: BookRepository = mock()
        val process: BookProcess = mock()
        val orderMap: MutableMap<String, Int?> = HashMap()

        orderMap["PRODUCT-ENOUGH-QTY"] = 5
        orderMap["PRODUCT-PRECISE-QTY"] = 10
        orderMap["PRODUCT-NOT-ENOUGH"] = 22

        val book1 = Book("PRODUCT-ENOUGH-QTY", 20, 11)
        whenever(bookRepo.findByISBN("PRODUCT-ENOUGH-QTY"))
            .thenReturn(book1)

        val book2 = Book("PRODUCT-PRECISE-QTY", 25, 10)
        whenever(bookRepo.findByISBN("PRODUCT-PRECISE-QTY"))
            .thenReturn(book2)

        val book3 = Book("PRODUCT-NOT-ENOUGH", 37, 21)
        whenever(bookRepo.findByISBN("PRODUCT-NOT-ENOUGH"))
            .thenReturn(book3)

        val bookStore = BookStore(bookRepo, process)
        val overview = bookStore.getPriceForCart(orderMap)

        val expectedPrice = 5 * 20 + 10 * 25 + 21 * 37

        assertThat(overview).isNotNull
        assertThat(overview?.totalPrice).isEqualTo(expectedPrice)

        verify(process).buyBook(book1, 5)
        verify(process).buyBook(book2, 10)
        verify(process).buyBook(book3, 21)

        assertThat(overview?.getUnavailable())
            .containsExactly(entry(book3, 1))
    }
}
