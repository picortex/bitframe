package bitframe.annotations

@Target(AnnotationTarget.CLASS)
annotation class Model(
    val name: String = ""
)
