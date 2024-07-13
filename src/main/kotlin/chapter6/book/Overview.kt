package chapter6.book

import java.util.*

class Overview {
    var totalPrice: Int = 0
        private set
    private val unavailable = mutableMapOf<Book, Int>()

    fun addUnavailable(
        book: Book,
        unavailableQty: Int,
    ) {
        unavailable[book] = unavailableQty
    }

    fun addToTotalPrice(valueToAdd: Int) {
        totalPrice += valueToAdd
    }

    fun getUnavailable(): Map<Book, Int> {
        return Collections.unmodifiableMap(unavailable)
    }
}
