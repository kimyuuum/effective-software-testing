package chapter2

class ShoppingCart {
    private val items = mutableListOf<CartItem>()

    fun add(item: CartItem) {
        this.items.add(item)
    }

    fun totalPrice(): Double {
        var totalPrice = 0.0

        for (item in items) {
            totalPrice += (item.unitPrice.times(item.quantity))
        }

        return totalPrice
    }
}
