package chapter5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BasketTest {
    // System under test = sut
    private val basket = Basket()

    @Test
    fun addProducts() {
        basket.add(Product("TV", BigDecimal.valueOf(10)), 2)
        basket.add(Product("Playstation", BigDecimal.valueOf(100)), 1)

        assertThat(basket.totalValue).isEqualByComparingTo(BigDecimal.valueOf(10 * 2 + 100 * 1))
    }

    @Test
    fun addSameProductTwice() {
        val p = Product("TV", BigDecimal.valueOf(10))
        basket.add(p, 2)
        basket.add(p, 3)

        assertThat(basket.totalValue)
            .isEqualTo(BigDecimal.valueOf(10 * 5))
    }

    @Test
    fun removeProducts() {
        basket.add(Product("TV", BigDecimal.valueOf(100)), 1)
        val p = Product("Playstation", BigDecimal.valueOf(10))

        basket.add(p, 2)
        basket.remove(p)

        assertThat(basket.totalValue)
            .isEqualByComparingTo(BigDecimal.valueOf(100))
        // BigDecimal은 다른 BigDecimal과 비교하려면 compareTo()를 사용해야 한다.
    }
}
