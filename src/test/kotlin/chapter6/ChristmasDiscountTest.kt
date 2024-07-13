package chapter6

import chapter6.christmas.ChristmasDiscount
import chapter6.christmas.Clock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.Month

class ChristmasDiscountTest {
    private val clock: Clock = mock()
    private val cd: ChristmasDiscount = ChristmasDiscount(clock)

    @Test
    fun christmas() {
        val christmas = LocalDate.of(2015, Month.DECEMBER, 25)
        whenever(clock.now()).thenReturn(christmas)

        val finalValue = cd.applyDiscount(100.0)
        assertThat(finalValue)
            .isCloseTo(85.0, offset(0.001))
    }

    @Test
    fun notChristmas() {
        val notChristmas = LocalDate.of(2015, Month.DECEMBER, 26)
        whenever(clock.now()).thenReturn(notChristmas)

        val finalValue = cd.applyDiscount(100.0)
        assertThat(finalValue).isCloseTo(100.0, offset(0.001))
    }
}
