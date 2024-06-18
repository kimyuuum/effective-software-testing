package chapter3

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LefPadTest {
    private val leftPad = LeftPad()

    @Test
    fun sameInstance() {
        val str = "sometext"
        assertThat(leftPad.leftPad(str, 5, "-")).isSameAs(str)
    }

    @ParameterizedTest
    @MethodSource("generator")
    fun test(
        originalStr: String?,
        size: Int,
        padString: String?,
        expectedStr: String?,
    ) {
        assertThat(leftPad.leftPad(originalStr, size, padString)).isEqualTo(expectedStr)
    }

    private fun generator(): Stream<Arguments> {
        return Stream.of(
            of(null, 10, "-", null), // T1
            of("", 5, "-", "-----"), // T2
            of("abc", -1, "-", "abc"), // T3
            of("abc", 5, null, "  abc"), // T4
            of("abc", 5, "", "  abc"), // T5
            of("abc", 5, "-", "--abc"), // T6
            of("abc", 3, "-", "abc"), // T7
            of("abc", 0, "-", "abc"), // T8
            of("abc", 2, "-", "abc"), // T9
            of("abc", 5, "--", "--abc"), // T10
            of("abc", 5, "---", "--abc"), // T11
            of("abc", 5, "-", "--abc"), // T12
        )
    }
}
