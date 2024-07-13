package chapter5

class Triangle {
    fun isTriangle(
        a: Int,
        b: Int,
        c: Int,
    ): Boolean {
        val hasABadSide: Boolean = a >= (b + c) || c >= (b + a) || b >= (a + c)
        return !hasABadSide
    }
}
