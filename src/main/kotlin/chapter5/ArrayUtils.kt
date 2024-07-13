package chapter5

object ArrayUtils {
    fun indexOf(
        array: IntArray?,
        valueToFind: Int,
        startIndexParam: Int,
    ): Int {
        if (array == null) {
            return -1
        }

        var startIndex = startIndexParam
        if (startIndex < 0) {
            startIndex = 0
        }

        // 삼항연산자처럼 쓸 수 있다. if-else 말고도 쓸 수 있는 방법
        startIndex = startIndexParam.takeIf { startIndexParam >= 0 } ?: 0

        for (i in startIndex until array.size) {
            if (valueToFind == array[i]) {
                return i
            }
        }

        return -1
    }
}
