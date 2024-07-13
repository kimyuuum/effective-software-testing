package chapter6.book

class BookStore(
    private val bookRepository: BookRepository,
    private val bookProcess: BookProcess,
) {
    private fun retrieveBook(
        ISBN: String?,
        amountParam: Int?,
        overview: Overview,
    ) {
        var amount = amountParam ?: return
        val book = bookRepository.findByISBN(isbn = ISBN) ?: return

        if (book.amount < amount) {
            overview.addUnavailable(book, amount - book.amount)
            amount = book.amount
        }

        overview.addToTotalPrice(amount * book.price)
        bookProcess.buyBook(book, amount)
    }

    fun getPriceForCart(order: MutableMap<String, Int?>?): Overview? {
        val orders = order ?: return null

        val overview = Overview()

        for (isbn in orders.keys) {
            retrieveBook(isbn, orders[isbn], overview)
        }

        return overview
    }
}
