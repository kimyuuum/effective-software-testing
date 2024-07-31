package chapter9.large

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DeliveryPriceTest {
    @ParameterizedTest
    @CsvSource(
        "0,0",
        "1,5",
        "3,5",
        "4,12.5",
        "10,12.5",
        "11,20",
    )
    fun deliveryIsAccordingToTheNumberOfItems(
        noOfItems: Int,
        expectedDeliveryPrice: Double,
    ) {
        val cart = ShoppingCart()
        for (i in 0 until noOfItems) {
            cart.add(Item(ItemType.OTHER, "ANY", 1, 1.0))
        }

        val price = DeliveryPrice().priceToAggregate(cart)
        assertThat(price).isEqualTo(expectedDeliveryPrice)
    }
}
