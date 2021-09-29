import org.jetbrains.dokka.gradle.DokkaMultiModuleTask

plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
    id("dev.petuska.npm.publish") version vers.npmPublish
    id("com.bmuschko.docker-java-application") version vers.docker apply false
    id("com.google.devtools.ksp") version vers.ksp apply false
    id("org.jetbrains.dokka") version vers.dokka
}

subprojects {
    repositories {
        publicRepos()
    }
    group = "com.picortex"
    version = vers.bitframe.current
}

val dokkaHtmlMultiModule by tasks.getting(DokkaMultiModuleTask::class) {
    moduleName.set("Bitframe Docs")
}