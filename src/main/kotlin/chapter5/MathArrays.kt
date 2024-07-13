package chapter5

import java.util.*

class MathArrays {
    fun unique(data: IntArray): IntArray {
        // 반복된 요소를 걸러내기 위해 treeSet을 사용한다.
        val values = TreeSet<Int>()

        if (data == null || data.isEmpty() == true) {
            throw NullPointerException()
        }

        /*
        indices와 size - 둘 다 배열 관련 속성이지만 서로 다른 목적과 용도를 가지고 있음.
        size는 배열의 요소 개수를 반환.
        indices는 배열의 유효한 인덱스 범위를 반환한다. 0부터 size - 1까지 범위를 나타냄.
        반복문에서 주로 사용한다.
        size를 사용할거면 for(i in 0 until data.size)로 사용.
         */
        for (i in data.indices) {
            values.add(data[i])
        }

        val count = values.size
        /*
            intArrayOf는 지정 값으로 초기화된 IntArray를 생성하는 함수
            IntArray는 지정된 크기만큼의 배열을 생성하는 클래스
            val out = intArrayOf(count)
         */
        val out = IntArray(count)

        val iterator = values.iterator()
        var i = 0

        while (iterator.hasNext() == true) {
            out[count - ++i] = iterator.next()
        }
        return out
    }
}
