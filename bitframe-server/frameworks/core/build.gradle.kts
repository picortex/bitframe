plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm {
        library()
        withJava()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.ktor:ktor-http:${vers.ktor}")
                api(asoft("kotlinx-serialization-mapper", vers.asoft.mapper))
                api(asoft("logging-console", vers.asoft.logging))
                api(asoft("later-ktx", vers.asoft.later))
                api(project(":users-server-dao-core"))
                api(project(":users-server-services-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":bitframe-server-framework-test"))
                implementation(kotlinx("serialization-core", vers.kotlinx.serialization))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
    }
}