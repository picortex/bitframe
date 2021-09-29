plugins {
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
                api(project(":bitframe-authentication-dao-core"))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}
