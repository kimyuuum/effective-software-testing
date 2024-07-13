package chapter5

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.IntRange

class TriangleTest {
    @Property
    fun triangleBadTest(
        @ForAll @IntRange(max = 100) a: Int,
        @ForAll @IntRange(max = 100) b: Int,
        @ForAll @IntRange(max = 100) c: Int,
    ) {
        TODO("테스트 구현. 유효하지 않은 삼각형이 될 확률이 높음.")
    }
}
