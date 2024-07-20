package chapter7.domain

import chapter7.ports.CustomerNotifier
import chapter7.ports.DeliveryCenter
import chapter7.ports.SAP
import chapter7.ports.ShoppingCartRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class PaidShoppingCartsBatchTest {
    @Mock
    lateinit var db: ShoppingCartRepository

    @Mock
    lateinit var deliveryCenter: DeliveryCenter

    @Mock
    lateinit var notifier: CustomerNotifier

    @Mock
    lateinit var sap: SAP

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun happyPath() {
        val batch = PaidShoppingCartsBatch(db, deliveryCenter, notifier, sap)
        val someCart = ShoppingCart(100.0)

        assertThat(someCart.readyForDelivery).isFalse()

        // kotlinx datetime?
        val someDate: LocalDate = LocalDate.now()

        whenever(db.cartsPaidToday())
            .thenReturn(listOf(someCart))

        whenever(deliveryCenter.deliver(someCart))
            .thenReturn(someDate)

        batch.processAll()

        verify(deliveryCenter).deliver(someCart)
        verify(notifier).sendEstimatedDeliveryNotification(someCart)

        verify(db).persist(someCart)
        verify(sap).cartReadyForDelivery(someCart)

        assertThat(someCart.readyForDelivery).isTrue()
    }

    @Test
    fun theWholeProcessHappens() {
        val batch = PaidShoppingCartsBatch(db, deliveryCenter, notifier, sap)
        val someCart = spy<ShoppingCart>()
        /* Without spy
            val someCart = ShoppingCart(0.0) // 생성자 없이 어떻게 처리?
            -> 기본 value를 추가해줘서 해결했는데, 그럼 아무 데이터 없이 선언하면 바로 그 기본값이 들어가기 때문인가?
            assertThat 자동으로 import 안되는 이슈..
            val someDate = Calendar.getInstance()
         */
        val someDate = LocalDate.now()

        whenever(db.cartsPaidToday()).thenReturn(listOf(someCart))
        whenever(deliveryCenter.deliver(someCart)).thenReturn(someDate)

        batch.processAll()

        verify(deliveryCenter).deliver(someCart)
        verify(notifier).sendEstimatedDeliveryNotification(someCart)
        verify(db).persist(someCart)
        verify(sap).cartReadyForDelivery(someCart)
        verify(someCart).markAsReadyForDelivery(someDate)
    }
}
