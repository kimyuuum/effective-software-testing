package chapter9.large

interface PriceRule {
    fun priceToAggregate(cart: ShoppingCart): Double
}
