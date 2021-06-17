import bitframe.BitframeExtension

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
    bitframe
}

applikation {
    debug()
}

configure<BitframeExtension> {
    url = "http://localhost:8080"
    namespace = "com.sample"
}

kotlin {
    jvm { application() }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs(file("generated/bitframe"))
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}