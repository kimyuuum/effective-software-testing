package chapter9.sql

import java.sql.Connection
import java.sql.PreparedStatement

class InvoiceDao(
    private val connection: Connection,
) {
    fun all(): MutableList<Invoice> {
        try {
            val ps: PreparedStatement = connection.prepareStatement("select * from invoice")
            val rs = ps.executeQuery()

            val allInvoices = mutableListOf<Invoice>()
            while (rs.next() == true) {
                allInvoices.add(Invoice(rs.getString("name"), rs.getInt("amount")))
            }

            return allInvoices
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun allWithAtLeast(amount: Int): List<Invoice> {
        try {
            val ps = connection.prepareStatement("select * from invoice where amount >= ?")
            ps.setInt(1, amount)
            val rs = ps.executeQuery()

            val allInvoices = mutableListOf<Invoice>()
            while (rs.next() == true) {
                allInvoices.add(
                    Invoice(rs.getString("name"), rs.getInt("amount")),
                )
            }

            return allInvoices
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun save(inv: Invoice) {
        try {
            val ps =
                connection.prepareStatement(
                    "insert into invoice (name,amount) values (?,?)",
                )

            ps.setString(1, inv.customer)
            ps.setInt(2, inv.amount)

            ps.execute()
            connection.commit()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
