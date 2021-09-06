plugins {
    id("com.google.devtools.ksp")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }
    js { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            val ksp by configurations
            dependencies {
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                ksp.dependencies.add(project(":bitframe-annotations-processor"))
                api(project(":bitframe-annotations-core"))
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }

        val jvmMain by getting {
            val ksp by configurations
            dependencies {
                ksp.dependencies.add(project(":bitframe-annotations-processor"))
            }
        }
    }
}

//configurations.get("ksp").dependencies.add(project(":bitframe-annotations-processor"))

//dependencies {
//    ksp()
//}