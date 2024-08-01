package chapter8

object RomanNumeralConverter {
    private val table =
        mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000,
        )

    fun convert(numberInRoman: String): Int {
        var finalNumber: Int = 0
        var lastNeighbor: Int = 0
        for (i in numberInRoman.length - 1 downTo 0) {
            val current = table[numberInRoman[i]] ?: error("Not found numberInRoman: ${numberInRoman[i]}")

            var multiplier = 1
            if (current < lastNeighbor) multiplier = -1

            finalNumber += current * multiplier

            // update neighbor at right
            lastNeighbor = current
        }

        return finalNumber
    }

    fun convert1(romanNumber: String): Int {
        if (romanNumber == "I") {
            return 1
        } else if (romanNumber == "V") {
            return 5
        }

        return 0
    }
}
