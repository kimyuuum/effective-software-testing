package chapter8

object Bowling {
    fun calculate(games: List<String>): Int {
        validateGame(games)

        var totalScore = 0
        var isStrike = 0
        var isSpare = 0
        var bonusScore = 0

        for (game in games) {
            val values = validateFrame(game)
            var frameScore = 0

            for (value in values) {
                when (value) {
                    "x" -> {
                        if (isStrike != 0) {
                            bonusScore += 10
                        } else if (isSpare != 0) {
                            bonusScore += 10
                            isSpare -= 1
                        }
                        isStrike = 2
                        frameScore += 10
                    }

                    "/" -> {
                        val spareScore = (10 - values[0].toInt())
                        if (isStrike != 0) {
                            isStrike -= 1
                            bonusScore += spareScore
                        }
                        isSpare = 1
                        frameScore += spareScore
                    }

                    else -> {
                        val score = value.toInt()
                        if (isStrike != 0) {
                            bonusScore += score
                            isStrike -= 1
                        } else if (isSpare != 0) {
                            bonusScore += score
                            isSpare -= 1
                        }
                        frameScore += score
                    }
                }
            }
            totalScore += frameScore
        }

        totalScore += bonusScore
        return totalScore
    }

    fun validateGame(games: List<String>) {
        require(games.size in 10..11) { "Invalid game size" }

        val lastFrame =
            games[9].substring(1 until games[9].length - 1)
                .split(" ")

        if (lastFrame[0] == "x") {
            val bonusFrame = games[10].substring(1 until games[10].length - 1).split(" ")
            assert(bonusFrame.size < 3) { "Strike bonus round size must be 2 or 1" }
        } else if (lastFrame[1] == "/") {
            val bonusFrame = games[10].substring(1 until games[10].length - 1).split(" ")
            assert(bonusFrame.size == 1) { "Spare bonus round size must be 1" }
        }
    }

    fun validateFrame(frame: String): List<String> {
        return frame.substring(1 until frame.length - 1).split(" ")
    }
}
