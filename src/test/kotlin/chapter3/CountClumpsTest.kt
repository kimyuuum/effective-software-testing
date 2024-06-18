package chapter3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountClumpsTest {
    private val clumps = CountClumps()

    @ParameterizedTest
    @MethodSource("generator")
    fun testClumps(
        nums: IntArray?,
        expectedNoOfClumps: Int,
    ) {
        assertThat(clumps.countClumps(nums)).isEqualTo(expectedNoOfClumps)
    }

    private fun generator(): Stream<Arguments> {
        return Stream.of(
            of(intArrayOf(), 0), // empty
            of(null, 0), // null
            of(intArrayOf(1, 2, 2, 2, 1), 1), // one clump
            of(intArrayOf(1), 0), // one element
            // an example of a missing test case!! Structural testing is not enough!
            of(intArrayOf(2, 2), 1),
        )
    }
}
