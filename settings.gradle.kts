pluginManagement {
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
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

includeBuild("bitframe-plugins")

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

// <BitframeUtils>
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

includeSubs("bitframe-actor", "bitframe-actor", "core", "app", "user", "space")
includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core", "mock", "mongo")

// Bitframe Service
includeSubs(base = "bitframe-service-builder", path = "bitframe-service/builder", "core", "daod")
includeSubs(base = "bitframe-service-builder-api", path = "bitframe-service/builder/api", "core", "ktor", "mock")
includeSubs(
    base = "bitframe-service-builder-sdk-client",
    path = "bitframe-service/builder/sdk/client",
    "core",
    "mock",
    "react"
)
includeSubs(base = "bitframe-service-builder-sdk-server", path = "bitframe-service/builder/sdk/server", "core")

includeSubs(base = "bitframe-service-generic", path = "bitframe-service/generic", "core", "daod")
includeSubs(base = "bitframe-service-generic-api", path = "bitframe-service/generic/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-service-generic-sdk-server", path = "bitframe-service/generic/sdk/server", "core")

includeSubs(base = "bitframe-authentication", path = "bitframe-features/authentication", "core", "daod")
includeSubs(base = "bitframe-authentication-api", path = "bitframe-features/authentication/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-authentication-sdk-client", path = "bitframe-features/authentication/sdk/client", "core")
includeSubs(base = "bitframe-authentication-sdk-server", path = "bitframe-features/authentication/sdk/server", "core")

includeSubs(base = "bitframe-api", "bitframe-api", "core", "ktor", "mock")

includeSubs(base = "bitframe-sdk-client", path = "bitframe-sdk/client", "core")
includeSubs(base = "bitframe-sdk-server", path = "bitframe-sdk/server", "core", "ktor", "test")