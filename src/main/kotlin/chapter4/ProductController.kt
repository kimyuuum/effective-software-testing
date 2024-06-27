package chapter4

class ProductController(
    private val database: DatabaseRepository,
) {
    private val errorMessages = mutableListOf<String>()

    fun add(
        productName: String,
        productDescription: String,
        price: Double,
    ) {
        val sanitizedProductName = sanitize(productName)
        val sanitizedProductDescription = sanitize(productDescription)

        if (isValidProductName(sanitizedProductName) == false) {
            errorMessages.add("Invalid product name")
        }

        if (isValidProductDescription(sanitizedProductDescription) == false) {
            errorMessages.add("Invalid price")
        }

        if (errorMessages.isEmpty() == true) {
            val newProduct =
                Product(
                    sanitizedProductName,
                    sanitizedProductDescription,
                    price,
                )

            database.save(newProduct)
            redirectTo("productPage", listOf(newProduct.id))
        } else {
            redirectTo("addProduct", getErrors())
        }
    }

    private fun getErrors(): MutableList<String> {
        return errorMessages
    }

    private fun sanitize(input: String): String {
        TODO("input 유효성 검사")
    }

    private fun isValidProductName(productName: String): Boolean {
        TODO("productName 유효성 검사")
    }

    private fun isValidProductDescription(productDescription: String): Boolean {
        TODO("productDescription 유효성 검사")
    }

    private fun redirectTo(
        pageName: String,
        infos: List<String?>,
    ) {
        TODO("redirect to target page")
    }
}
