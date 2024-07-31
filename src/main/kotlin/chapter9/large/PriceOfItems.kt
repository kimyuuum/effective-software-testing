package chapter9.large

class PriceOfItems : PriceRule {
    override fun priceToAggregate(cart: ShoppingCart): Double {
        var price = 0.0
        val items = cart.getItems()
        for (item in items) {
            price += item.pricePerUnit * item.quantity
        }

        return price
    }
}
