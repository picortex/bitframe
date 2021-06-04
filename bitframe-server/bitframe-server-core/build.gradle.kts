plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

//tasks.withType(JavaCompile::class.java).all {
//    options.compilerArgs = listOf("--enable-preview")
//}

tasks.withType(Test::class.java).all {
    jvmArgs = listOf("--enable-preview")
}

kotlin {
    jvm {
        library("15")
        withJava()
        compilations.all {
            compileJavaTaskProvider?.get()?.apply {
                options.compilerArgs = listOf("--enable-preview")
                targetCompatibility = "15"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.ktor:ktor-http:${vers.ktor}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":bitframe-server-test"))
                implementation(kotlinx("serialization-core", vers.kotlinx.serialization))
                implementation(kotlin("test"))
//                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
    }
}