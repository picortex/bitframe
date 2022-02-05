plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    target {
        library()
    }
    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeDaoCore)
                api(kmongo.coroutines.serialization)
                api(asoft.later.ktx)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}