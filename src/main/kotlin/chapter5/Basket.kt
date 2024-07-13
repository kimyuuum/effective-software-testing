package chapter5

import java.math.BigDecimal
import java.util.*

class Basket {
    var totalValue: BigDecimal = BigDecimal.ZERO
    private val basket = mutableMapOf<Product, Int>()

    fun add(
        product: Product?,
        qtyToAdd: Int,
    ) {
        assert(product != null) { "Product is required" }
        assert(qtyToAdd > 0) { "Quantity has to be greater than zero" }

        // require로 변수 검증을 해야 아래서 스마트캐스팅이 된다.
        requireNotNull(product) { "Product is required" }
        require(qtyToAdd > 0) { "Quantity has to be greater than zero" }

        val oldTotalValue = totalValue
        val existingQuantity = basket.getOrDefault(product, 0)
        val newQuantity = existingQuantity + qtyToAdd
        basket[product] = newQuantity

        val valueAlreadyInTheCart = product.price.multiply(BigDecimal.valueOf(existingQuantity.toLong()))
        val newFinalValueForTheProduct = product.price.multiply(BigDecimal.valueOf(newQuantity.toLong()))

        totalValue = totalValue.subtract(valueAlreadyInTheCart).add(newFinalValueForTheProduct)

        assert(basket.containsKey(product)) { "Product was not inserted in the basket" }
        assert(totalValue.compareTo(oldTotalValue) == 1) { "Total value should be greater than previous total value" }
        assert(invariant()) { "Invariant does not hold" } // 사후 조건 및 불변식을 검사한다.
    }

    private fun invariant(): Boolean {
        return totalValue >= BigDecimal.ZERO
    }

    fun remove(product: Product?) {
        requireNotNull(product) { "product can't be null" }
//        assert(product != null) { "product can't be null" }
        assert(basket.containsKey(product)) { "Product must already be in the basket" }

        // 하나하나 다 !! 해줘야하나..?
        // null 이면 early return, 아니면 requireNotNull or error(Message)를 사용해줘도 됨.
        val qty = basket[product] ?: return

        val productPrice: BigDecimal = product.price
        val productTimesQuantity = productPrice.multiply(BigDecimal.valueOf(qty.toLong()))
        totalValue = totalValue.subtract(productTimesQuantity)

        basket.remove(product)

        assert(basket.containsKey(product) == false) { "Product is still in the basket" }
        assert(invariant()) { "Invariant does not hold" }
    }

    fun quantityOf(product: Product): Int {
//        assert(basket.containsKey(product))
        val result = basket[product]
        requireNotNull(result) { "product is not null" }
        return result
    }

    // 제품을 복제해서 반환한다
    fun products(): Set<Product> {
        // kotlin 은 Collection이 기본적으로 readOnly.
        // 요렇게 unmodifiable을 선언할수도 있다.
        val test = basket.keys.toSet()
        return Collections.unmodifiableSet(basket.keys)
    }
}
