package chapter9.large

class ExtraChargeForElectronics : PriceRule {
    override fun priceToAggregate(cart: ShoppingCart): Double {
        val items = cart.getItems()

        val hasAnElectronicDevice =
            items.any {
                it.type == ItemType.ELECTRONIC
            }

        if (hasAnElectronicDevice == true) {
            return 7.50
        }

        return 0.0
    }
}
