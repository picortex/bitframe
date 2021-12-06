package templater

data class Parameter(
    val raw: String
) {
    val name by lazy { raw.removeSurrounding(prefix = "{{", suffix = "}}").trim() }
}