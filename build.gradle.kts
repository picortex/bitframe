import org.jetbrains.dokka.gradle.DokkaMultiModuleTask

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(kotlinz.plugins.root.multiplatform) apply false
    alias(kotlinz.plugins.root.serialization) apply false
    alias(asoft.plugins.root.library) apply false
    alias(kotlinz.plugins.dokka)
}

allprojects {
    repositories {
        publicRepos()
    }
    beforeEvaluate {
        group = "com.picortex"
        version = bitframe.versions.current.get()
    }
}

val dokkaHtmlMultiModule by tasks.getting(DokkaMultiModuleTask::class) {
    moduleName.set("Bitframe Docs")
    outputDirectory.set(file("reference/${bitframe.versions.current.get()}"))
}