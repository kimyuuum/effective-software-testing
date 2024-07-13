package chapter6.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, String> {
    fun findByISBN(isbn: String?): Book
}
