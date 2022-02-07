plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.core)
                api(kotlinx.datetime)
                api(asoft.kotlinx.collections.interoperable)
                api(asoft.identifier.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}
