plugins {
//    id("com.google.devtools.ksp")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
//                val ksp by configurations
//                ksp.dependencies.add(project(":bitframe-annotations-processor"))
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api(project(":bitframe-annotations-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}

//dependencies {
//    ksp()
//}