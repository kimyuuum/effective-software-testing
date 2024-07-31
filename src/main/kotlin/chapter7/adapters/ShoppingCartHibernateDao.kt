package chapter7.adapters

import chapter7.domain.ShoppingCart
import chapter7.ports.ShoppingCartRepository

class ShoppingCartHibernateDao : ShoppingCartRepository {
    override fun cartsPaidToday(): List<ShoppingCart> {
        return emptyList()
    }

    override fun persist(cart: ShoppingCart) {
        TODO("Not yet implemented")
    }
}
