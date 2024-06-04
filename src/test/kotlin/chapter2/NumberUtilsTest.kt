package chapter2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumberUtilsTest {
    private val numberUtils = NumberUtils()

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    fun shouldThrowExceptionWhenDigitsAreOutOfRange(
        left: MutableList<Int>,
        right: MutableList<Int>,
    ) {
        assertThatThrownBy {
            numberUtils.add(left, right)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @MethodSource("testCases") // 입력을 제공헤주는 메서드
    fun shouldReturnCorrectResult(
        left: MutableList<Int>,
        right: MutableList<Int>,
        expected: MutableList<Int>,
    ) {
        assertThat(numberUtils.add(left, right))
            .isEqualTo(expected)
    }

    private fun testCases(): Stream<Arguments> {
        return Stream.of(
            of(null, numbers(arrayOf(7, 2)), null),
            of(numbers(arrayOf()), numbers(arrayOf(7, 2)), numbers(arrayOf(7, 2))), // T2
            of(numbers(arrayOf(9, 8)), null, null), // T3
            of(numbers(arrayOf(9, 8)), numbers(arrayOf()), numbers(arrayOf(9, 8))), // T4
            // single digits
            of(numbers(arrayOf(1)), numbers(arrayOf(2)), numbers(arrayOf(3))), // T5
            of(numbers(arrayOf(9)), numbers(arrayOf(2)), numbers(arrayOf(1, 1))), // T6
            // multiple digits
            of(numbers(arrayOf(2, 2)), numbers(arrayOf(3, 3)), numbers(arrayOf(5, 5))), // T7
            of(numbers(arrayOf(2, 9)), numbers(arrayOf(2, 3)), numbers(arrayOf(5, 2))), // T8
            of(numbers(arrayOf(2, 9, 3)), numbers(arrayOf(1, 8, 3)), numbers(arrayOf(4, 7, 6))), // T9
            of(numbers(arrayOf(1, 7, 9)), numbers(arrayOf(2, 6, 8)), numbers(arrayOf(4, 4, 7))), // T10
            of(
                numbers(arrayOf(1, 9, 1, 7, 1)),
                numbers(arrayOf(1, 8, 1, 6, 1)),
                numbers(arrayOf(3, 7, 3, 3, 2)),
            ), // T11
            of(numbers(arrayOf(9, 9, 8)), numbers(arrayOf(1, 7, 2)), numbers(arrayOf(1, 1, 7, 0))), // T12
            // multiple digits, different length, with and without carry
            // (from both sides)
            of(numbers(arrayOf(2, 2)), numbers(arrayOf(3)), numbers(arrayOf(2, 5))), // T13.1
            of(numbers(arrayOf(3)), numbers(arrayOf(2, 2)), numbers(arrayOf(2, 5))), // T13.2
            of(numbers(arrayOf(2, 2)), numbers(arrayOf(9)), numbers(arrayOf(3, 1))), // T14.1
            of(numbers(arrayOf(9)), numbers(arrayOf(2, 2)), numbers(arrayOf(3, 1))), // T14.2
            of(numbers(arrayOf(1, 7, 3)), numbers(arrayOf(9, 2)), numbers(arrayOf(2, 6, 5))), // T15.1
            of(numbers(arrayOf(9, 2)), numbers(arrayOf(1, 7, 3)), numbers(arrayOf(2, 6, 5))), // T15.2
            of(numbers(arrayOf(3, 1, 7, 9)), numbers(arrayOf(2, 6, 8)), numbers(arrayOf(3, 4, 4, 7))), // T16.1
            of(numbers(arrayOf(2, 6, 8)), numbers(arrayOf(3, 1, 7, 9)), numbers(arrayOf(3, 4, 4, 7))), // T16.2
            of(
                numbers(arrayOf(1, 9, 1, 7, 1)),
                numbers(arrayOf(2, 1, 8, 1, 6, 1)),
                numbers(arrayOf(2, 3, 7, 3, 3, 2)),
            ), // T17.1
            of(
                numbers(arrayOf(2, 1, 8, 1, 6, 1)),
                numbers(arrayOf(1, 9, 1, 7, 1)),
                numbers(arrayOf(2, 3, 7, 3, 3, 2)),
            ), // T17.2
            of(numbers(arrayOf(9, 9, 8)), numbers(arrayOf(9, 1, 7, 2)), numbers(arrayOf(1, 0, 1, 7, 0))), // T18.1
            of(numbers(arrayOf(9, 1, 7, 2)), numbers(arrayOf(9, 9, 8)), numbers(arrayOf(1, 0, 1, 7, 0))), // T18.2
            // zeroes on the left
            of(numbers(arrayOf(0, 0, 0, 1, 2)), numbers(arrayOf(0, 2, 3)), numbers(arrayOf(3, 5))), // T19
            of(numbers(arrayOf(0, 0, 0, 1, 2)), numbers(arrayOf(0, 2, 9)), numbers(arrayOf(4, 1))), // T20,
            // boundary
            of(numbers(arrayOf(9, 9)), numbers(arrayOf(1)), numbers(arrayOf(1, 0, 0))), // T21
        )
    }

    private fun digitsOutOfRange(): Stream<Arguments> {
        return Stream.of(
            of(numbers(arrayOf(1, -1, 1)), numbers(arrayOf(1))),
            of(numbers(arrayOf(1)), numbers(arrayOf(1, -1, 1))),
            of(numbers(arrayOf(1, 10, 1)), numbers(arrayOf(1))),
            of(numbers(arrayOf(1)), numbers(arrayOf(1, 11, 1))),
        )
    }

    private fun numbers(nums: Array<Int>): MutableList<Int> {
        val list = mutableListOf<Int>()

        for (n in nums) {
            list.add(n)
        }

        return list
    }
}
