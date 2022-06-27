plugins {
    id("tz.co.asoft.library")
}

afterEvaluate {
    configurePublishing {
        repositories {
            maven {
                name = "andylamax"
                url = uri("http://localhost:1050/repository/internal/")
                isAllowInsecureProtocol = true
                credentials {
                    username = "admin"
                    password = "admin@123"
                }
            }

            maven {
                name = "piCortex"
                url = uri("http://${vars.dev.server.ip}:1050/repository/internal/")
                isAllowInsecureProtocol = true
                credentials {
                    username = "admin"
                    password = "admin@123"
                }
            }
        }
    }
}