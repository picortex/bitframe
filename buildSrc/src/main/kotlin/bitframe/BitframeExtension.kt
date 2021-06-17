package bitframe

import org.gradle.api.Project
import java.io.File
import java.io.Serializable

open class BitframeExtension @JvmOverloads constructor(
    val project: Project,
    var url: String = "http://localhost:8080",
    var namespace: String = "bitframe",
    var destination: File = project.file("generated/bitframe"),
    var language: Language = Language.Kotlin
) : Serializable