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
//
//val bitframePlugins = gradle.includedBuild("bitframe-plugins")
//
//
//val publishRelevantsToPicortex by tasks.creating {
//    doFirst {
//        println("publishing libraries and plugins to picortex")
//    }
//    dependsOn(
//        bitframePlugins.task(":publishPicortexPublishPluginMarkerMavenPublicationToPiCortexRepository"),
//        bitframePlugins.task(":publishDockerComposePluginMarkerMavenPublicationToPiCortexRepository"),
//    )
//
//    subprojects {
//        val task = tasks.findByName("publishAllPublicationsToPiCortexRepository")
//        if (task != null) dependsOn(task)
//    }
//
//    doLast {
//        println("Finished publishing to picortex")
//    }
//}
//
//val publishRelevantsToMavenLocal by tasks.creating {
//    doFirst {
//        println("publishing libraries and plugins to maven local")
//    }
//
//    dependsOn(
//        bitframePlugins.task(":publishPicortexPublishPluginMarkerMavenPublicationToMavenLocal"),
//        bitframePlugins.task(":publishDockerComposePluginMarkerMavenPublicationToMavenLocal")
//    )
//
//    subprojects {
//        val task = tasks.findByName("publishToMavenLocal")
//        if (task != null) dependsOn(task)
//    }
//
//    doLast {
//        println("Finished publishing to maven local")
//    }
//}