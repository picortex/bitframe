plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeServiceBuilderDaod)
                api(projects.responseCore)
                api(squareup.okio.core)
                api(kotlinx.serialization.yaml) // Because we need to read configurations in yaml
                api(ktor.http)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(), description = "A kotlin multiplatform library"
)