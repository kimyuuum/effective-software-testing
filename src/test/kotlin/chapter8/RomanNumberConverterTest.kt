package chapter8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RomanNumberConverterTest {
    // 이미 object 면 static에 올라와 있으니까 RomanNumeralConverter.function() 호출해서 써도 됨
    private val roman = RomanNumeralConverter

    @Test
    fun shouldUnderstandSymbolI() {
        val number = roman.convert("I")
        assertThat(number).isEqualTo(1)
    }

    @Test
    fun shouldUnderstandSymbolV() {
        val number = roman.convert("V")
        assertThat(number).isEqualTo(5)
    }

    @ParameterizedTest
    @CsvSource("I,1", "V,5", "X,10", "L,50", "C, 100", "D, 500", "M, 1000")
    fun shouldUnderstandOneCharNumbers(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        val convertedNumber = roman.convert(romanNumeral)
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion)
    }

    @Test
    fun shouldUnderstandMultipleCharNumbers() {
        val convertedNumber = roman.convert("II")
        assertThat(convertedNumber).isEqualTo(2)
    }

    @ParameterizedTest
    @CsvSource("II,2", "III,3", "VI, 6", "XVIII, 18", "XXIII, 23", "DCCLXVI, 766")
    fun shouldUnderstandMultipleCharNumbers(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        val convertedNumber: Int = roman.convert(romanNumeral)
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion)
    }

    @ParameterizedTest
    @CsvSource("IV,4", "XIV,14", "XL, 40", "XLI,41", "CCXCIV, 294")
    fun shouldUnderstandSubtractiveNotation(
        romanNumeral: String,
        expectedNumberAfterConversion: Int,
    ) {
        val convertedNumber: Int = roman.convert(romanNumeral)
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion)
    }
}
