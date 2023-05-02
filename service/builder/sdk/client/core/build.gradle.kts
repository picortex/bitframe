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
                api(projects.symphonyTable)
                api(projects.symphonyList)
                // api(projects.symphonyCollectionsCore)
                // api(projects.symphonyInputsCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeServiceBuilderApiCore)
                implementation(projects.kommanderCoroutines)
                implementation(projects.cinematicLiveTest)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)