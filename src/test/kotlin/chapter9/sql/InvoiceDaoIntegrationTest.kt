package chapter9.sql

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InvoiceDaoIntegrationTest : SqlIntegrationTestBase() {
    @Test
    fun save() {
        val inv1 = Invoice("Mauricio", 10)
        val inv2 = Invoice("Frank", 11)
        dao.save(inv1)

        val afterSaving = dao.all()
        assertThat(afterSaving).containsExactlyInAnyOrder(inv1)

        dao.save(inv2)
        val afterSavingAgain = dao.all()

        assertThat(afterSavingAgain).contains(inv2)
    }

    @Test
    fun atLeast() {
        val value = 50

        val inv1 = Invoice("Mauricio", value - 1)
        val inv2 = Invoice("Arie", value)
        val inv3 = Invoice("Frank", value + 1)

        dao.save(inv1)
        dao.save(inv2)
        dao.save(inv3)

        val afterSaving = dao.allWithAtLeast(value)

        assertThat(afterSaving).isNotNull
            .containsExactlyInAnyOrder(inv2, inv3)
    }
}
