plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm {
        library()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-server-core"))
                api(asoft("test-coroutines", vers.asoft.test))
                api(asoft("expect-core", vers.asoft.expect))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
            }
        }
    }
}