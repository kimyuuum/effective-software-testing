package chapter6

import chapter6.invoice.Invoice
import chapter6.invoice.InvoiceFilter
import chapter6.invoice.IssuedInvoices
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class InvoiceFilterTest {
    @Test
    fun filterInvoices() {
        /*
            mockk = mock와 stub을 같이 만들어주는 형태
         */
        val issuedInvoice: IssuedInvoices = mock()

        val mauricio = Invoice("Mauricio", 20)
        val steve = Invoice("Steve", 99)
        val frank = Invoice("Frank", 100)

        val listOfInvoices =
            listOf(
                mauricio,
                steve,
                frank,
            )

//         kotlin의 예약어와 충돌하지 않게 하기 위해서 백틱을 사용한다. ``
//        `when`(issuedInvoice.all()).thenReturn(listOfInvoices)
        whenever(issuedInvoice.all()).thenReturn(listOfInvoices)

        val filter = InvoiceFilter(issuedInvoice)

        // assertThat 호출할 때 마다 수동으로 위에 import 해주는지?
        assertThat(filter.lowValueInvoices())
            .containsExactlyInAnyOrder(mauricio, steve)
    }
}
