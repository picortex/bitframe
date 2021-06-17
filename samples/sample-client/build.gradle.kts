import bitframe.BitframeExtension

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
    bitframe
}

configure<BitframeExtension> {
    url = "http://localhost:8080"
}

kotlin {
    jvm { application() }
}