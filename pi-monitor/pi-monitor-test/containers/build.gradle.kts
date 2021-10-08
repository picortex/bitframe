plugins {
    kotlin("jvm")
    `picortex-publish`
}

kotlin {
    target {
        library()
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-service-ktor"))
//                api(project(":pi-monitor-test-testing"))
                api("org.junit.jupiter:junit-jupiter-params:5.7.0")
                api("org.testcontainers:testcontainers:${vers.testContainers}")
                api("org.testcontainers:junit-jupiter:${vers.testContainers}")
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}