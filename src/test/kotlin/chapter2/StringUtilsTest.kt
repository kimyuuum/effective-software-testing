package chapter2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringUtilsTest {
    private val stringUtils = StringUtils()

    @Test
    fun simpleCase() {
        assertThat(
            stringUtils.substringsBetween("abcd", "a", "d"),
        ).isEqualTo(arrayOf("bc"))
    }

    @Test
    fun manySubstrings() {
        assertThat(
            stringUtils.substringsBetween("abcdabcdab", "a", "d"),
        ).isEqualTo(arrayOf("bc", "bc"))
    }

    @Test
    fun openAndCloseTagsThatAreLongerThat1Char() {
        assertThat(
            stringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd"),
        ).isEqualTo(arrayOf("bc", "bf"))
    }

    @Test
    fun strIsNullOrEmpty() {
        assertThat(stringUtils.substringsBetween(null, "a", "b")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("", "a", "b")).isEqualTo(emptyArray<String>())
    }

    @Test
    fun openIsNullOrEmpty() {
        assertThat(stringUtils.substringsBetween("abc", null, "b")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("abc", "", "b")).isEqualTo(null)
    }

    @Test
    fun closeIsNullOrEmpty() {
        assertThat(stringUtils.substringsBetween("abc", "a", null)).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("abc", "a", "")).isEqualTo(null)
    }

    @Test
    fun strOfLength1() {
        assertThat(stringUtils.substringsBetween("a", "a", "b")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("a", "b", "a")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("a", "b", "b")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("a", "a", "a")).isEqualTo(null)
    }

    @Test
    fun openAndCloseOfLength1() {
        assertThat(stringUtils.substringsBetween("abc", "x", "y")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("abc", "a", "y")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("abc", "x", "c")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("abc", "a", "c")).isEqualTo(arrayOf("b"))
        assertThat(stringUtils.substringsBetween("abcabc", "a", "c")).isEqualTo(arrayOf("b", "b"))

        assertThat(stringUtils.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(arrayOf("b", "byt byr"))
    }

    @Test
    fun openAndCloseTagsOfDifferentSizes() {
        assertThat(stringUtils.substringsBetween("aabcc", "xx", "yy")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("aabcc", "aa", "yy")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("aabcc", "xx", "cc")).isEqualTo(null)
        assertThat(stringUtils.substringsBetween("aabbcc", "aa", "cc")).isEqualTo(arrayOf("bb"))
        assertThat(stringUtils.substringsBetween("aabbccaaeecc", "aa", "cc")).isEqualTo(arrayOf("bb", "ee"))
        assertThat(
            stringUtils.substringsBetween(
                "a abb ddc ca abbcc",
                "a a",
                "c c",
            ),
        ).isEqualTo(arrayOf("bb dd"))

        assertThat(stringUtils.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(arrayOf("bb dd"))
    }

    @Test
    fun noSubstringBetweenOpenAndCloseTags() {
        assertThat(stringUtils.substringsBetween("aabb", "aa", "bb")).isEqualTo(arrayOf(""))
    }
}
