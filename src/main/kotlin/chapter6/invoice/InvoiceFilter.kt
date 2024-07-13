package chapter6.invoice

class InvoiceFilter(
//    private val database: Database,
    private val issuedInvoices: IssuedInvoices,
) {
    fun lowValueInvoices(): List<Invoice> {
//        transaction(database) {
//            val all = issuedInvoices.all()
//            all.filter { invoice -> invoice.value < 100 }
//        }
        val all = issuedInvoices.all()
        return all.filter { invoice -> invoice.value < 100 }
    }
}
