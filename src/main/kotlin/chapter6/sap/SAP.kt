package chapter6.sap

import chapter6.invoice.Invoice

interface SAP {
    fun send(invoice: Invoice)

    fun send(invoice: SapInvoice)
}
