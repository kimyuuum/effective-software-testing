package chapter6.invoice

class IssuedInvoices(private val connection: DatabaseConnection?) {
    private val invoices = mutableListOf<Invoice>()

    fun all(): List<Invoice> {
        return invoices
    }

    fun allWithAtLeast(value: Int): List<Invoice> {
        return emptyList()
    }

    fun save(inv: Invoice) {
        invoices.add(inv)
    }
}
