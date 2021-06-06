package bitframe.modules

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val name: String,
    val email: String
) {
    companion object {
        val JOHN_DOE = Customer("John Doe", "john@doe.com")
        val JANE_DOE = Customer("Jane Doe", "jane@doe.com")
    }
}