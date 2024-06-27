package chapter4

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Product(
    val productName: String,
    val productDescription: String,
    val price: Double,
) {
    @Id
    val id: String? = null
}
