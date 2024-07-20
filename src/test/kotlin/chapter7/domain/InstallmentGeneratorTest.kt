package chapter7.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.time.LocalDate

class InstallmentGeneratorTest {
    @Mock
    lateinit var repository: InstallmentRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun checkInstallments() {
        val generator = InstallmentGenerator(repository)

        val cart = ShoppingCart(100.0)
        generator.generateInstallments(cart, 10)

        val captor = argumentCaptor<Installment>()
        val persist = verify(repository, times(10)).persist(captor.capture())
        val allInstallments = captor.allValues

        assertThat(allInstallments)
            .hasSize(10)
            .allMatch { it -> it.value == 10.0 }

        for (month in 1..10) {
            val dueDate = LocalDate.now().plusMonths(month.toLong())
            assertThat(allInstallments).anyMatch { i -> i.date.equals(dueDate) }
        }
    }

    @Test
    fun checkInstallmentLists() {
        val cart = ShoppingCart(100.0)
        val generator = InstallmentGenerator(repository)

        val allInstallments = generator.generateInstallmentLists(cart, 10)
        assertThat(allInstallments)
            .hasSize(10)
            .allMatch { it -> it.value == 10.0 }

        for (month in 1..10) {
            val dueDate = LocalDate.now().plusMonths(month.toLong())
            assertThat(allInstallments).anyMatch { i -> i.date.equals(dueDate) }
        }
    }
}
