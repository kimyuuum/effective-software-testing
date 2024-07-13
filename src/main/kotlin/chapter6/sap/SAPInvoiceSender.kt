package chapter6.sap

import chapter6.invoice.Invoice
import chapter6.invoice.InvoiceFilter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SAPInvoiceSender(
    private val filter: InvoiceFilter,
    private val sap: SAP,
) {
    fun sendLowValuedInvoices(): MutableList<Invoice> {
        val failedInvoices = mutableListOf<Invoice>()

        val lowValuedInvoices = filter.lowValueInvoices()
        for (invoice in lowValuedInvoices) {
            val customer = invoice.customer
            val value = invoice.value
            val sapId = generateId(invoice)

            val sapInvoice = SapInvoice(customer, value, sapId)

            try {
                sap.send(sapInvoice)
            } catch (e: SAPException) {
                failedInvoices.add(invoice)
            }
        }

        return failedInvoices
    }

    private fun generateId(invoice: Invoice): String {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))
        val customer = invoice.customer

        return date +
            (if (customer.length >= 2) customer.substring(0, 2) else "X")
    }
}
