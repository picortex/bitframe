import testing.testing

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

repositories {
    publicRepos()
    mavenCentral()
}

kotlin {
    jvm {
        library()
        testing("unit", "integration")
    }

    js(IR) {
        library()
        testing("unit", "integration")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":bitframe-authentication-service-client-core"))
                api(project(":bitframe-authentication-dao-inmemory"))
                api(project(":bitframe-events-inmemory"))
                api(project(":cache-test"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(project(":pi-monitor-test-testing"))
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
