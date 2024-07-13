package chapter6

import chapter6.invoice.Invoice
import chapter6.invoice.InvoiceFilter
import chapter6.sap.SAP
import chapter6.sap.SAPException
import chapter6.sap.SAPInvoiceSender
import chapter6.sap.SapInvoice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.kotlin.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SAPInvoiceSenderTest {
    private val filter: InvoiceFilter = mock()
    private val sap: SAP = mock()
    private val sender = SAPInvoiceSender(filter, sap)

    @Test
    fun sendToSap() {
        val mauricio = Invoice("Mauricio", 20)
        val frank = Invoice("Frank", 99)

        val invoices =
            listOf(
                mauricio,
                frank,
            )

        whenever(filter.lowValueInvoices()).thenReturn(invoices)

        sender.sendLowValuedInvoices()

        verify(sap).send(mauricio)
        verify(sap).send(frank)

        verify(sap, times(1)).send(mauricio)
        verify(sap, times(1)).send(frank)
    }

    @Test
    fun noLowValueInvoices() {
        val invoices = emptyList<Invoice>()
        whenever(filter.lowValueInvoices()).thenReturn(invoices)

        sender.sendLowValuedInvoices()
        // 기존대로 검증하면 nullPointerException이 발생해서 mockito-kotlin을 사용함.
        verify(sap, never()).send(any<Invoice>())
    }

    @Test
    fun sendSapInvoiceToSap() {
        val mauricio = Invoice("Mauricio", 20)
        val invoices = listOf(mauricio)

        whenever(filter.lowValueInvoices()).thenReturn(invoices)
        sender.sendLowValuedInvoices()

        verify(sap).send(any<SapInvoice>())
    }

    @ParameterizedTest
    @CsvSource(
        "Mauricio,Ma",
        "M,X",
    )
    fun sendToSapWithTheGeneratedId(
        customer: String,
        customerCode: String,
    ) {
        val mauricio = Invoice(customer, 20)
        val invoices = listOf(mauricio)

        whenever(filter.lowValueInvoices()).thenReturn(invoices)
        sender.sendLowValuedInvoices()

        val captor = argumentCaptor<SapInvoice>()

        verify(sap).send(captor.capture())

        val generatedSapInvoice = captor.firstValue

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        assertThat(generatedSapInvoice)
            .isEqualTo(SapInvoice(customer, 20, date + customerCode))
    }

    @Test
    fun returnFailedInvoice() {
        val mauricio = Invoice("Mauricio", 20)
        val frank = Invoice("Frank", 25)
        val steve = Invoice("Steve", 48)

        val invoices =
            listOf(
                mauricio,
                frank,
                steve,
            )

        whenever(filter.lowValueInvoices()).thenReturn(invoices)

        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val franksInvoice = SapInvoice("Frank", 25, date + "Fr")
        doThrow(SAPException())
            .whenever(sap).send(franksInvoice)

        val failedInvoices = sender.sendLowValuedInvoices()
        assertThat(failedInvoices).containsExactly(frank)

        val mauriciosInvoice = SapInvoice("Mauricio", 20, date + "Ma")
        verify(sap).send(mauriciosInvoice)

        val stevesInvoice = SapInvoice("Steve", 48, date + "St")
        verify(sap).send(stevesInvoice)
    }
}
