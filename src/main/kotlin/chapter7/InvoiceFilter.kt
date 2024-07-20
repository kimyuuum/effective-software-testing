package chapter7

import chapter6.invoice.Invoice
import java.sql.DriverManager

class InvoiceFilter {
    fun all(): List<Invoice> {
        try {
            val connection = DriverManager.getConnection("db", "root", "")
            val ps = connection.prepareStatement("select * from invoice")
            val rs = ps.executeQuery()

            val allInvoices = arrayListOf<Invoice>()
            while (rs.next()) {
                allInvoices.add(
                    Invoice(
                        rs.getString("name"),
                        rs.getInt("value"),
                    ),
                )
            }

            ps.close()
            connection.close()

            return allInvoices
        } catch (e: Exception) {
            TODO("예외 처리")
        }
    }

    fun lowValueInvoices(): List<Invoice> {
        val issuedInvoices = all()
        return issuedInvoices.filter { it -> it.value < 100 }
    }
}
