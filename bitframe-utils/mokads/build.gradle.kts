plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
}

val tmp = 1

kotlin {
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = listOf(linuxX64()) //linuxTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.json)
                api(kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }

//        val nonJsMain by creating {
//            dependsOn(commonMain)
//        }
//
//        val nonJsTest by creating {
//            dependsOn(commonTest)
//            dependsOn(nonJsMain)
//        }

//        val jvmMain by getting {
//            dependsOn(nonJsMain)
//        }
//
//        val jvmTest by getting {
//            dependsOn(nonJsTest)
//        }

//        val nativeMain by creating {
//            dependsOn(commonMain)
//            dependsOn(nonJsMain)
//        }

//        val nativeTest by creating {
//            dependsOn(nonJsTest)
//        }

//        for (target in nativeTargets) {
//            val main by target.compilations
//            main.defaultSourceSet { dependsOn(nativeMain) }
//
//            val test by target.compilations
//            test.defaultSourceSet { dependsOn(nativeTest) }
//        }
    }
}
