plugins {
    kotlin("multiplatform") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
    id("com.bmuschko.docker-java-application") version vers.docker apply false
//    id("docker-compose") version vers.dockerCompose apply false
}

subprojects {
    group = "com.picortex"
    version = vers.bitframe.current
}