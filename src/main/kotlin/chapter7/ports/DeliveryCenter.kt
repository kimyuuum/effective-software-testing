package chapter7.ports

import chapter7.domain.ShoppingCart
import java.time.LocalDate

interface DeliveryCenter {
    fun deliver(cart: ShoppingCart): LocalDate
}
