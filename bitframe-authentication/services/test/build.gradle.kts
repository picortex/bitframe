plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

repositories {
    publicRepos()
    mavenCentral()
}

kotlin {
    jvm { library() }

    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":cache-test"))
                api(project(":bitframe-authentication-service-ktor"))
                api(asoft("expect-coroutines", vers.asoft.expect))
                api(project(":bitframe-authentication-dao-inmemory"))
                api(project(":pi-monitor-test-testing"))
                api(project(":bitframe-events-inmemory"))
            }
        }
    }
}
