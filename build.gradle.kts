plugins {
    kotlin("multiplatform") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
    id("com.bmuschko.docker-java-application") version vers.docker apply false
}

subprojects {
    group = "com.picortex"
    version = vers.picortex.bitframe
}