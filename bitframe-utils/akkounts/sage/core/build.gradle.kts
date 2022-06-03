plugins {
    kotlin("plugin.serialization")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm {
        library();
        withJava()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.akkountsCore)
                api(asoft.kotlinx.serialization.mapper)
                api(ktor.client.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(ktor.client.cio)
            }
        }
    }
}