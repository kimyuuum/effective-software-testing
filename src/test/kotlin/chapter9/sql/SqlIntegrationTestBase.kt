package chapter9.sql

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

open class SqlIntegrationTestBase {
    private lateinit var connection: Connection
    lateinit var dao: InvoiceDao

    @BeforeEach
    @Throws(SQLException::class)
    fun openConnectionAndCleanup() {
        connection = DriverManager.getConnection("jdbc:h2:mem:book")

        val preparedStatement: PreparedStatement =
            connection.prepareStatement("create table if not exists invoice (name varchar(100), amount double)")

        preparedStatement.execute()
        connection.commit()
        dao = InvoiceDao(connection)

        connection.prepareStatement("truncate table invoice")?.execute()
    }

    @AfterEach
    @Throws(SQLException::class)
    fun close() {
        connection.close()
    }
}
