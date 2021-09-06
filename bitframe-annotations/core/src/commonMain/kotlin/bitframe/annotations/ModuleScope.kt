package bitframe.annotations

@Target(AnnotationTarget.CLASS)
annotation class ModuleScope(
    val name: String = ""
)
