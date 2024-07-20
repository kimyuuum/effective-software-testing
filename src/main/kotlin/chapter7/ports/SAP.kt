package chapter7.ports

import chapter7.domain.ShoppingCart

interface SAP {
    fun cartReadyForDelivery(cart: ShoppingCart)
}
