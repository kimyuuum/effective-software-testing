package chapter7.domain

import java.time.LocalDate

data class ShoppingCart(
    var value: Double = 0.0,
) {
    var readyForDelivery: Boolean = false

    fun markAsReadyForDelivery(estimatedDayOfDelivery: LocalDate) {
        this.readyForDelivery = true
    }

    fun isReadyForDelivery(): Boolean {
        return readyForDelivery
    }

    fun numberOfItems(): Int {
        return 0
    }
}
