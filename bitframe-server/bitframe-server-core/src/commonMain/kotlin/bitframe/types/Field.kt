package bitframe.types

sealed class Field(open val name: String) {
    sealed class Primitive(name: String) : Field(name) {
        data class StringField(override val name: String, val default: String? = null) : Field(name)
        data class IntField(override val name: String, val default: Int? = null) : Field(name)
    }
}