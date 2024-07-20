package chapter7.domain

import java.time.LocalDate

class InstallmentGenerator(
    private val installmentRepository: InstallmentRepository?,
) {
    fun generateInstallments(
        cart: ShoppingCart,
        numberOfInstallments: Int,
    ) {
        var nextInstallmentDueDate = LocalDate.now()

        val amountPerInstallment = cart.value / numberOfInstallments

        for (i in 1..numberOfInstallments) {
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1)

            val newInstallment =
                Installment(
                    nextInstallmentDueDate,
                    amountPerInstallment,
                )

            installmentRepository?.persist(newInstallment)
        }
    }

    fun generateInstallmentLists(
        cart: ShoppingCart,
        numberOfInstallments: Int,
    ): List<Installment> {
        val generatedInstallments = mutableListOf<Installment>()

        var nextInstallmentDueDate = LocalDate.now()
        val amountPerInstallment = cart.value / numberOfInstallments

        for (i in 1..numberOfInstallments) {
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1)

            val newInstallment =
                Installment(
                    nextInstallmentDueDate,
                    amountPerInstallment,
                )

            installmentRepository?.persist(newInstallment)
            generatedInstallments.add(newInstallment)
        }

        return generatedInstallments
    }
}
