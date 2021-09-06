plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                configurations["ksp"].dependencies.add(project(":bitframe-processor"))
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api(project(":bitframe-annotations"))
            }
        }
    }
}

tasks.withType<com.google.devtools.ksp.gradle.KspTaskJS> {
//    enabled = false
}

tasks.withType<com.google.devtools.ksp.gradle.KspTaskJvm> {
//    enabled = false
}

tasks.withType<com.google.devtools.ksp.gradle.KspTaskNative> {
//    enabled = false
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon> {
////    dependsOn(tasks.withType<com.google.devtools.ksp.gradle.KspTaskMetadata>())
//}