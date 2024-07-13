package chapter6.christmas

import java.time.Month.DECEMBER

class ChristmasDiscount(
    private val clock: Clock,
) {
    fun applyDiscount(rawAmount: Double): Double {
        val today = clock.now()

        var discountPercentage = 0.0
        val isChristmas = today.month == DECEMBER && today.dayOfMonth == 25

        if (isChristmas) {
            discountPercentage = 0.15
        }

        return rawAmount - (rawAmount * discountPercentage)
    }
}
