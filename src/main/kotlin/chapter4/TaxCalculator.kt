package chapter4

class TaxCalculator {
    fun calculateTax(value: Double): Double {
        // AssertionError를 사용할 수도 있다.
        assert(value >= 0) { "Value cannot be negative" }

        if (value < 0) {
            throw RuntimeException("Value cannot be negative")
        }

        val taxValue: Double = 0.0

        // 여기에 복잡한 비즈니스 규칙을 추가.
        // 최종 계산 결과가 taxValue에 담겨있다
        assert(taxValue >= 0) { "Calculated tax value cannot be negative" }

        if (taxValue < 0) {
            throw RuntimeException("Calculated tax value cannot be negative")
        }

        return taxValue
    }
}
