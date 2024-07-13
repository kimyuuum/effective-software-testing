package chapter5

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange
import net.jqwik.api.constraints.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// class 자체를 instance화 해서 사용하는 것.
// companion object로 변환해서 사용해보자.
// @JvmStatic

// decompile 방법
// Tools > Kotlin > show bytecode > decompile 버튼
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArrayUtilsTest {
    @ParameterizedTest
    @MethodSource("testCases")
    fun testIndexOf(
        array: IntArray?,
        valueToFind: Int,
        startIndex: Int,
        expectedResult: Int,
    ) {
        // Util일 때 object로 만들면 싱글톤으로 생성되어서 바로바로 참조 가능함.
        val result = ArrayUtils.indexOf(array, valueToFind, startIndex)
        assertThat(result).isEqualTo(expectedResult)
    }

    // Test Failed
    @Property
    fun indexOfShouldFindFirstValue(
        @ForAll
        @Size(value = 100)
        numbers: MutableList<
            @IntRange(min = -1000, max = 1000)
            Int,
        >,
        @ForAll
        @IntRange(min = 1001, max = 2000)
        value: Int,
        @ForAll
        @IntRange(max = 99)
        indexToAddElement: Int,
        @ForAll
        @IntRange(max = 99)
        startIndex: Int,
    ) {
        numbers.add(indexToAddElement, value)
        val array = convertListToArray(numbers)
        val expectedIndex = if (indexToAddElement >= startIndex) indexToAddElement else -1
        assertThat(ArrayUtils.indexOf(array, value, startIndex)).isEqualTo(expectedIndex)
    }

    private fun convertListToArray(numbers: List<Int>): IntArray {
        return numbers.toIntArray()
    }

    companion object {
        /*
            jvm에서 static으로 인식을 할 수 있도록 붙여주는 어노테이션?

            @JvmStatic
            private static final Stream testCases() {
                return Companion.testCases();
            }

            어노테이션을 붙여줘야 위에같은 코드로 Companion object 바깥에 생성이 되어서 Companion.~~ 으로 호출하지 않아도 됨. JVM에서 인식해야되니까.
         */
        @JvmStatic
        private fun testCases(): Stream<Arguments?>? {
            val array = intArrayOf(1, 2, 3, 4, 5, 4, 6, 7)

            return Stream.of(
                Arguments.of(null, 1, 1, -1),
                Arguments.of(intArrayOf(1), 1, 0, 0),
                Arguments.of(intArrayOf(1), 2, 0, -1),
                Arguments.of(array, 1, 10, -1),
                Arguments.of(array, 2, -1, 1),
                Arguments.of(array, 4, 6, -1),
                Arguments.of(array, 4, 1, 3),
                Arguments.of(array, 4, 3, 3),
                Arguments.of(array, 4, 1, 3),
                Arguments.of(array, 4, 4, 5),
                Arguments.of(array, 8, 0, -1),
            )
        }
    }
}
