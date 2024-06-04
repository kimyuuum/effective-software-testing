package chapter2

import java.util.*
import kotlin.math.max

class NumberUtils {
    fun add(
        left: MutableList<Int>,
        right: MutableList<Int>,
    ): List<Int>? {
        if (left == null || right == null) {
            return null
        }

        left.reverse()
        right.reverse()

        val result = LinkedList<Int>()

        var carry = 0
        for (i in 0..max(left.size, right.size)) {
            val leftDigit = if (left.size > i) left[i] else 0
            val rightDigit = if (right.size > i) right[i] else 0

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw IllegalArgumentException()
            }

            val sum = leftDigit + rightDigit + carry

            result.addFirst(sum % 10)

            carry = sum / 10
        }

        if (carry > 0) {
            result.addFirst(carry)
        }

        while (result.size > 1 && result[0] == 0) {
            result.remove(0)
        }

        return result
    }
}
