package chapter9.large

class FinalPriceCalculatorFactory {
    fun build(): FinalPriceCalculator {
        val priceRules =
            listOf(
                PriceOfItems(),
                ExtraChargeForElectronics(),
                DeliveryPrice(),
            )

        return FinalPriceCalculator(priceRules)
    }
}
