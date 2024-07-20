package chapter7.domain

import java.time.LocalDate

data class Installment(
    val date: LocalDate,
    val value: Double,
)
