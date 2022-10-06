plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeServiceBuilderApiCore)
                api(projects.presentersCore)
            }
        }

        val nonJsMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(nonJsMain)
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeServiceBuilderApiCore)
                implementation(projects.expectCoroutines)
                implementation(projects.liveTest)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)