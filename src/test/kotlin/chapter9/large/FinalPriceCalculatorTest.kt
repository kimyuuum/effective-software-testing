package chapter9.large

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FinalPriceCalculatorTest {
    @Test
    fun callAllPriceRules() {
        val rule1: PriceRule = mock()
        val rule2: PriceRule = mock()
        val rule3: PriceRule = mock()

        val cart = ShoppingCart()

        cart.add(Item(ItemType.OTHER, "ITEM", 1, 1.0))

        whenever(rule1.priceToAggregate(cart)).thenReturn(1.0)
        whenever(rule2.priceToAggregate(cart)).thenReturn(0.0)
        whenever(rule3.priceToAggregate(cart)).thenReturn(2.0)

        val rules: List<PriceRule> = listOf(rule1, rule2, rule3)
        val calculator = FinalPriceCalculator(rules)
        val price = calculator.calculate(cart)

        assertThat(price).isEqualTo(3.0)
    }
}
