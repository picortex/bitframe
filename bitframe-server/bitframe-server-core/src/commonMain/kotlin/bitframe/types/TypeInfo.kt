package bitframe.types

data class TypeInfo(
    val singular: String,
    val plural: String,
    val fields: List<Field>
)