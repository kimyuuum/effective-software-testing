package chapter5

import net.jqwik.api.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*

class BookTest {
    @Property
    fun differentBooks(
        @ForAll("books") book: Book,
    ) {
        // 다른 책이다
        println(book)

        assertThat(book.title.length).isLessThanOrEqualTo(100)
            .isGreaterThanOrEqualTo(10)

        assertThat(book.author.length).isLessThanOrEqualTo(21)
            .isGreaterThanOrEqualTo(5)

        assertThat(book.qtyOfPages).isBetween(0, 450)
    }

    @Provide
    fun books(): Arbitrary<Book> {
        val titles =
            Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(10).ofMaxLength(100)

        val authors =
            Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(5).ofMaxLength(21)

        val qtyOfPages = Arbitraries.integers().between(0, 450)

        return Combinators.combine(titles, authors, qtyOfPages)
            .`as` { title, author, page -> Book(title, author, page) }
    }
}
