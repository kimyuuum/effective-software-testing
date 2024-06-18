package chapter3

class LeftPad {
    fun leftPad(
        str: String?,
        size: Int,
        padStrParam: String?,
    ): String? {
        var padStr = padStrParam

        if (str == null) {
            return null
        }

        if (padStr.isNullOrEmpty() == true) {
            padStr = " "
        }

        val padLen = padStr.length
        val strLen = str.length
        val pads = size - strLen

        if (pads <= 0) {
            return str // 가능하면 원본 문자열을 반환
        }

        if (pads == padLen) {
            return padStr + str
        } else if (pads < padLen) {
            return padStr.substring(0, pads) + str
        } else {
            val padding = CharArray(pads)
            val padChars = padStr.toCharArray()

            for (i in 0 until pads) {
                padding[i] = padChars[i % padLen]
            }

            return String(padding) + str
        }
    }
}
