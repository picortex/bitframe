pluginManagement {
    enableFeaturePreview("VERSION_CATALOGS")
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map { it.nameWithoutExtension }.forEach {
                create(it) { from(files("gradle/versions/$it.toml")) }
            }
        }
    }
}

rootDir.resolve("gradle.properties").copyTo(
    target = rootDir.resolve("buildSrc").resolve("gradle.properties"),
    overwrite = true
)

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "bitframe"

// <Bitframe Utils>
includeSubs(base = "mailer", path = "bitframe-utils/mailer", "api", "mock", "smtp")
includeRoot("templater", "bitframe-utils/templater")
includeSubs(base = "datetime", path = "bitframe-utils/datetime", "core")
includeSubs(base = "events", path = "bitframe-utils/events", "core", "inmemory", "react")
includeSubs(base = "presenters", path = "bitframe-utils/presenters", "core", "mock")
includeRoot(name = "properties", path = "bitframe-utils/properties")
includeRoot(name = "validation", path = "bitframe-utils/validation")
includeRoot(name = "response", path = "bitframe-utils/response")
// includeRoot(name = "mokads", path = "bitframe-utils/mokads")
includeSubs(base = "akkounts", path = "bitframe-utils/akkounts", "core", "mock", "quickbooks")
includeSubs(base = "akkounts-sage", path = "bitframe-utils/akkounts/sage", "core", "mock")

// Bitframe Core
includeSubs("bitframe-actor", "bitframe-actor", "core", "app", "user", "space")
includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core", "mock", "mongo")

// Bitframe Service
includeSubs(base = "bitframe-service-builder", path = "bitframe-service/builder", "core", "daod")
includeSubs(base = "bitframe-service-builder-api", path = "bitframe-service/builder/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-service-builder-sdk-client", path = "bitframe-service/builder/sdk/client", "core", "mock", "react")
includeSubs(base = "bitframe-service-builder-sdk-server", path = "bitframe-service/builder/sdk/server", "core")

includeSubs(base = "bitframe-service-generic", path = "bitframe-service/generic", "core", "daod")
includeSubs(base = "bitframe-service-generic-api", path = "bitframe-service/generic/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-service-generic-sdk-server", path = "bitframe-service/generic/sdk/server", "core")

includeSubs(base = "bitframe-authentication", path = "bitframe-features/authentication", "core", "daod")
includeSubs(base = "bitframe-authentication-api", path = "bitframe-features/authentication/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-authentication-sdk-client", path = "bitframe-features/authentication/sdk/client", "core", "react")
includeSubs(base = "bitframe-authentication-sdk-server", path = "bitframe-features/authentication/sdk/server", "core")

includeSubs(base = "bitframe-dashboard", path = "bitframe-features/dashboard", "core", "picortex")

includeSubs(base = "bitframe-api", "bitframe-api", "core", "ktor", "mock")

includeSubs(base = "bitframe-sdk-client", path = "bitframe-sdk/client", "core", "react")
includeSubs(base = "bitframe-sdk-server", path = "bitframe-sdk/server", "core", "ktor", "test")

includeRoot(name = "authenticator-core", "authenticator/core")
includeRoot(name = "authenticator-daod", "authenticator/daod")
includeSubs(base = "authenticator-api", "authenticator/api", "core", "ktor", "mock")
includeSubs(base = "authenticator-api-public", "authenticator/api/public", "core", "test")
includeSubs(base = "authenticator-sdk-client", "authenticator/sdk/client", "core", "react")
includeSubs(base = "authenticator-sdk-server", "authenticator/sdk/server", "core")
includeSubs(base = "authenticator-server", "authenticator/server", "app")

includeRoot(name = "pimonitor-common-core", "pimonitor/common/core")
includeSubs(base = "pimonitor-common-api", "pimonitor/common/api", "core")
includeRoot(name = "pimonitor-monitor-core", "pimonitor/monitor/core")
includeRoot(name = "pimonitor-monitor-daod", "pimonitor/monitor/daod")
includeSubs(base = "pimonitor-monitor-api", "pimonitor/monitor/api", "core", "ktor", "mock")
includeSubs(base = "pimonitor-monitor-app", "pimonitor/monitor/app", "populator")
includeSubs(base = "pimonitor-monitor-api-public", "pimonitor/monitor/api/public", "core", "test")
includeSubs(base = "pimonitor-monitor-sdk-client", "pimonitor/monitor/sdk/client", "core", "react")
includeSubs(base = "pimonitor-monitor-sdk-server", "pimonitor/monitor/sdk/server", "core")

//includeSubs(base = "pimonitor-server-sdk", "pimonitor/server/sdk", "core", "ktor")
includeSubs(base = "pimonitor-server", "pimonitor/server", "app")