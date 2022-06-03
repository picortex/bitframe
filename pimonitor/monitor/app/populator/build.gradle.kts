import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import docker.DockerComposeFileTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("tz.co.asoft.applikation")
    `docker-compose`
}

application {
    mainClass.set("pimonitor.MainKt")
}

applikation {
    debug()
    release()
}

kotlin {
    target {
        application()
        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "16"
        }
        compilations.all {
            kotlinOptions.jvmTarget = "16"
        }
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(asoft.identifier.generators)
                api(projects.pimonitorMonitorSdkClientCore)
                api(projects.presentersMock)
            }
        }
    }
}