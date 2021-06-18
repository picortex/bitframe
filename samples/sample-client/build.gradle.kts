import bitframe.BitframeExtension

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
    bitframe
}

applikation {
    common(
        "Main-Class" to "com.sample.MainKt"
    )
    debug()
}

configure<BitframeExtension> {
    url = "http://localhost:8080"
    namespace = "com.sample"
}

afterEvaluate {
    tasks.getByName("fatJarJvmDebug", Zip::class) {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
kotlin {
    jvm { application() }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs(file("generated/bitframe"))
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}