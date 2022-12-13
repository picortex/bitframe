plugins {
    kotlin("multiplatform")
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
                api(projects.bitframeServiceBuilderApiCore)
                api(projects.presentersCollectionsCore)
                api(projects.presentersInputsCore)
            }
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