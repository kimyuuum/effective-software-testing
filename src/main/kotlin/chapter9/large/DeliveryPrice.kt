package chapter9.large

class DeliveryPrice : PriceRule {
    override fun priceToAggregate(cart: ShoppingCart): Double {
        val totalItems = cart.numberOfItems()
        if (totalItems == 0) {
            return 0.0
        }

        if (totalItems in 1..3) {
            return 5.0
        }

        if (totalItems in 4..10) {
            return 12.5
        }

        return 20.0
    }
}
