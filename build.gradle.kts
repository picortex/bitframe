plugins {
    kotlin("multiplatform") version vers.kotlin apply false
    kotlin("plugin.serialization") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
    id("dev.petuska.npm.publish") version vers.npmPublish
    id("com.bmuschko.docker-java-application") version vers.docker apply false
}

subprojects {
    group = "com.picortex"
    version = vers.bitframe.current
}
