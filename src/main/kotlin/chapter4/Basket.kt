package chapter4

import java.math.BigDecimal

class Basket {
    private val totalValue: BigDecimal = BigDecimal.ZERO
    private val basket = mutableMapOf<Product, Int>()

    fun add(
        product: Product?,
        qtyToAdd: Int,
    ) {
        // 들어올 때 : require, check도 있음 : Preconditions 참조
        // 함수에서 마지막 파라미터가 lambda일 때 아래와 같이 괄호를 사용하여 쓸 수 있다.
        assert(product != null) { "Product is required" }
        assert(qtyToAdd > 0) { "Quantity has to be greater than zero" }

        val oldTotalValue = totalValue
        // 제폼을 추가한다
        // 합계를 갱신한다

        assert(basket.containsKey(product)) { "Product was not inserted in the basket" }
        assert(totalValue.compareTo(oldTotalValue) == 1) { "Total value should be greater than previous total value" }
        assert(totalValue >= BigDecimal.ZERO) { "Total value can't be negative" }
        assert(invariant()) { "Invariant does not hold" } // ?
    }

    fun remove(product: Product?) {
        assert(product != null) { "Product cannot be null" }
        assert(basket.containsKey(product)) { "Product must already be in the basket" }
        // 장바구니에서 제품을 뺀다
        // 합계를 갱신한다

        assert(basket.containsKey(product) == false) { "Product is still in the basket" }
        assert(totalValue >= BigDecimal.ZERO) { "Total value can't be negative" }
    }

    private fun invariant(): Boolean {
        return totalValue >= BigDecimal.ZERO
    }
}
