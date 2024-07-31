package chapter9.large

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalPriceCalculatorLargerTest {
    private val calculator: FinalPriceCalculator = FinalPriceCalculatorFactory().build()

    @Test
    fun appliesAllRules() {
        val cart = ShoppingCart()
        cart.add(Item(ItemType.ELECTRONIC, "PS5", 1, 299.0))
        cart.add(Item(ItemType.OTHER, "BOOK", 1, 29.0))
        cart.add(Item(ItemType.OTHER, "CD", 2, 12.0))
        cart.add(Item(ItemType.OTHER, "CHOCOLATE", 3, 1.5))

        val price = calculator.calculate(cart)

        val expectedPrice = 299 + 29 + 12 * 2 + 1.50 * 3 + 7.50 + 12.5
        assertThat(price).isEqualTo(expectedPrice)
    }
}
