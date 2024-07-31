package chapter9.large

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExtraChargeForElectronicsTest {
    @ParameterizedTest
    @CsvSource(
        "1",
        "2",
    )
    fun chargeTheExtraPriceIfThereIsAnyElectronicInTheCart(numberOfElectronics: Int) {
        val cart = ShoppingCart()
        for (i in 0 until numberOfElectronics) {
            cart.add(Item(ItemType.ELECTRONIC, "ANY ELECTRONIC", 1, 1.0))
        }

        val price = ExtraChargeForElectronics().priceToAggregate(cart)

        assertThat(price).isEqualTo(7.50)
    }

    @Test
    fun noExtraChargesIfNoElectronics() {
        val cart = ShoppingCart()
        cart.add(Item(ItemType.OTHER, "BOOK", 1, 1.0))
        cart.add(Item(ItemType.OTHER, "CD", 1, 1.0))
        cart.add(Item(ItemType.OTHER, "BABY", 1, 1.0))

        val price = ExtraChargeForElectronics().priceToAggregate(cart)

        assertThat(price).isEqualTo(0.0)
    }

    @Test
    fun noItems() {
        val cart = ShoppingCart()
        val price = ExtraChargeForElectronics().priceToAggregate(cart)

        assertThat(price).isEqualTo(0.0)
    }
}
