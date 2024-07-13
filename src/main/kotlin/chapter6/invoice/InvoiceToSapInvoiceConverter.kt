package chapter6.invoice

import chapter6.sap.SapInvoice
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class InvoiceToSapInvoiceConverter {
    fun convert(invoice: Invoice): SapInvoice {
        val customer = invoice.customer
        val value = invoice.value
        val sapId = generateId(invoice)

        return SapInvoice(
            customer,
            value,
            sapId,
        )
    }

    private fun generateId(invoice: Invoice): String {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val customer = invoice.customer

        return date +
            (if (customer.length >= 2) customer.substring(0, 2) else "X")
    }
}
