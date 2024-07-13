package chapter5

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange
import net.jqwik.api.constraints.Size
import org.assertj.core.api.AssertionsForClassTypes.assertThat

class MathArrayPBTest {
    private val mathArrays = MathArrays()

    @Property
    fun unique(
        @ForAll
        @Size(value = 100)
        numbers: List<
            @IntRange(min = 1, max = 20)
            Int,
        >,
    ) {
        val doubles = convertListToArray(numbers)
        val result = mathArrays.unique(doubles)

        assertThat(result)
            .contains(*doubles)
            .doesNotHaveDuplicates()
            .isSortedAccordingTo(reverseOrder())
    }

    private fun convertListToArray(numbers: List<Int>): IntArray {
        return numbers.toIntArray()
    }
}
