package chapter2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShoppingCartTest {
    private val cart = ShoppingCart()

    @Test
    fun noItems() {
        assertThat(cart.totalPrice())
            .isEqualTo(0.0)
    }

    @Test
    fun itemsInTheCart() {
        cart.add(CartItem("TV", 1, 120.0))
        assertThat(cart.totalPrice())
            .isEqualTo(120.0)

        cart.add(CartItem("Chocolate", 2, 2.5))
        assertThat(cart.totalPrice())
            .isEqualTo(120 + 2.5 * 2)
    }
}
