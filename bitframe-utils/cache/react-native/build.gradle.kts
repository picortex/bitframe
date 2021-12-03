plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

project.plugins.apply(org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin::class.java)

kotlin {
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":cache-api"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
                api(npm("@react-native-async-storage/async-storage", vers.npm.asyncStorage))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
