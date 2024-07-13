package chapter5

import net.jqwik.api.*
import net.jqwik.api.constraints.FloatRange
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PassingGradesPBTest {
    private val pg = PassingGrade()

    // jquwik이 값을 생성하는 매개변수는 ForAll이 있어야 함.
    // 무작위 float형 숫자 생성
    @Property
    fun fail(
        @ForAll
        @FloatRange(min = 1f, max = 5f, maxIncluded = false)
        grade: Float,
    ) {
        assertFalse { pg.passed(grade) }
        assertThat(pg.passed(grade)).isFalse()
    }

    @Property
    fun pass(
        @ForAll
        @FloatRange(min = 5f, max = 10f, maxIncluded = false)
        grade: Float,
    ) {
        assertTrue { pg.passed(grade) }
        assertThat(pg.passed(grade)).isTrue()
    }

    @Property
    fun invalid(
        @ForAll("invalidGrades") grade: Float,
    ) {
        // 좀 더 코틀린스럽게 쓸 수 있는 방법 ...
        assertThrows<IllegalArgumentException> {
            pg.passed(grade)
        }

        assertThatThrownBy {
            pg.passed(grade)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Provide
    fun invalidGrades(): Arbitrary<Float> {
        return Arbitraries.oneOf(
            Arbitraries.floats().lessThan(1f),
            Arbitraries.floats().greaterThan(10f),
        )
    }
}
