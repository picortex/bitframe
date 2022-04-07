import org.jetbrains.dokka.gradle.DokkaMultiModuleTask

plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    id("tz.co.asoft.library") apply false
    id("dev.petuska.npm.publish") version vers.npmPublish
    id("com.bmuschko.docker-java-application") version vers.docker apply false
    id("org.jetbrains.dokka") version vers.dokka
}

allprojects {
    repositories {
        publicRepos()
    }
    group = "com.picortex"
    version = vers.bitframe.stagingCurrent
}

val dokkaHtmlMultiModule by tasks.getting(DokkaMultiModuleTask::class) {
    moduleName.set("Bitframe Docs")
}