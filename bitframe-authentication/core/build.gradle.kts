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
                api(kotlinx("datetime", vers.kotlinx.datetime))
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api(asoft("later-core", vers.asoft.later))
                api(asoft("phone-core", vers.asoft.contacts))
                api(asoft("email-core", vers.asoft.contacts))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx("serialization-json", vers.kotlinx.serialization))
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}
