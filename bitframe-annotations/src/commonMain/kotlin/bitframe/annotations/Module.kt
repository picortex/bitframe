package bitframe.annotations

@Target(AnnotationTarget.CLASS)
annotation class Module(
    val name: String = ""
)
