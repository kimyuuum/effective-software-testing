package chapter5

import net.jqwik.api.*
// 둘 다 stateful.* 쓸 수 있도록 해야됨....
import net.jqwik.api.stateful.Action
import net.jqwik.api.stateful.ActionSequence
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal

class ActionTest {
    /*
        원래 코드에는 toString을 override해서 사용하는게 있어서 data class로 만들어도 나쁘지 않음
     */
    class AddAction(
        // 생성자로 제품과 수량을 받을 때 jquwik을 통해 무작위로 생성된 값을 받는다.
        private val product: Product,
        private val qty: Int,
    ) : Action<Basket> {
        // java - @Override
        override fun run(basket: Basket): Basket {
            val currentValue = basket.totalValue
            basket.add(product, qty)

            val newProductValue = product.price.multiply(BigDecimal.valueOf(qty.toLong()))
            val newValue = currentValue.add(newProductValue)

            assertThat(basket.totalValue).isEqualByComparingTo(newValue)

            return basket
        }
    }

    class RemoveAction : Action<Basket> {
        override fun run(basket: Basket): Basket {
            val currentValue = basket.totalValue
            val productsInBasket = basket.products()

            if (productsInBasket.isEmpty() == true) {
                return basket
            }

            val randomProduct = pickRandom(productsInBasket)
            val currentProductQty = basket.quantityOf(randomProduct)
            basket.remove(randomProduct)

            val basketValueWithoutRandomProduct =
                currentValue
                    .subtract(
                        randomProduct.price
                            .multiply(BigDecimal.valueOf(currentProductQty.toLong())),
                    )

            assertThat(basket.totalValue)
                .isEqualByComparingTo(basketValueWithoutRandomProduct)

            return basket
        }

        private fun pickRandom(products: Set<Product>): Product {
            return products.random()
        }
    }

    private val randomProducts =
        listOf(
            (Product("TV", BigDecimal("100"))),
            (Product("Playstation", BigDecimal("150.3"))),
            (Product("Refrigerator", BigDecimal("180.27"))),
            (Product("Soda", BigDecimal("2.69"))),
        )

    // AddAction 인스턴스 생성
    private fun addAction(): Arbitrary<AddAction> {
        val products =
            Arbitraries.oneOf(
                randomProducts.map { p -> Arbitraries.of(p) }.toList(),
            )

        val qtys = Arbitraries.integers().between(1, 100)

        return Combinators
            .combine(products, qtys)
            .`as` { product, qty -> AddAction(product, qty) }
    }

    private fun removeAction(): Arbitrary<RemoveAction> {
        return Arbitraries.of(RemoveAction())
    }

    @Provide
    fun addsAndRemoves(): Arbitrary<ActionSequence<Basket>> {
        return Arbitraries.sequences(
            Arbitraries.oneOf(
                addAction(),
                removeAction(),
            ),
        )
    }

    @Property(afterFailure = AfterFailureMode.SAMPLE_ONLY)
    fun sequenceOfAddsAndRemoves(
        @ForAll("addsAndRemoves")
        actions: ActionSequence<Basket>,
    ) {
        actions.run(Basket())
    }
}
