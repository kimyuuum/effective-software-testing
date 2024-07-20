package chapter7.domain

interface InstallmentRepository {
    fun persist(installment: Installment)
}
