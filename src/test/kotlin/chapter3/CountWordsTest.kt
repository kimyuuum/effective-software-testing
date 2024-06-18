package chapter3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountWordsTest {
    private val countWords = CountWords()

    @Test
    fun twoWordsEndingWithS() {
        val words = countWords.count("dogs cats")
        assertThat(words).isEqualTo(2)
    }

    @Test
    fun twoWordsEndingWithR() {
        val words = countWords.count("car bar")
        assertThat(words).isEqualTo(2)
    }

    @Test
    fun noWordsAtAll() {
        val words = countWords.count("dog cat")
        assertThat(words).isEqualTo(0)
    }
}
