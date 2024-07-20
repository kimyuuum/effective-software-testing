package chapter7.ports

import chapter7.domain.ShoppingCart

interface ShoppingCartRepository {
    fun cartsPaidToday(): List<ShoppingCart>

    fun persist(cart: ShoppingCart)
}
