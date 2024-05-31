package com.example.effectingsoftwaretesting.chapter1

import java.util.*

class PlanningPoker {
    fun identifyExtremes(estimates: List<Estimate>?): List<String?> {
        var lowestEstimate: Estimate? = null
        var highestEstimate: Estimate? = null

        if (estimates == null) {
            throw IllegalArgumentException("estimates cannot be null")
        }

        if (estimates.size <= 1) {
            throw IllegalArgumentException("there has to be more than 1 estimate in the list")
        }

        for (estimate in estimates) {
            if (highestEstimate == null || estimate.estimate > highestEstimate.estimate) {
                highestEstimate = estimate
            }

            if (lowestEstimate == null || estimate.estimate < lowestEstimate.estimate) {
                lowestEstimate = estimate
            }
        }

        if (lowestEstimate?.equals(highestEstimate) == true) {
            return Collections.emptyList()
        }

        return listOf(
            lowestEstimate?.developer,
            highestEstimate?.developer,
        )
    }
}
