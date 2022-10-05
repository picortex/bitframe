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

val tmp = 4

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

includeSubs("krono", "../krono", "api")
includeSubs(base = "presenters", path = "../presenters", "actions", "core", "mock")
includeSubs(base = "mailer", "../mailer", "api", "mock", "smtp")

// <BitframeUtils>
includeRoot("templater", "bitframe-utils/templater")
includeSubs(base = "events", path = "bitframe-utils/events", "core", "inmemory", "react")
includeRoot(name = "properties", path = "bitframe-utils/properties")
includeRoot(name = "validation", path = "bitframe-utils/validation")
includeRoot(name = "response", path = "bitframe-utils/response")

includeSubs("bitframe-actor", "bitframe-actor", "core", "app", "user", "space")
includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core", "mock", "mongo", "file")
includeRoot(name = "bitframe-dao", path = "bitframe-daos/universal")

// Bitframe Service
includeSubs(base = "bitframe-service-builder", path = "bitframe-service/builder", "core", "daod", "rest")
includeSubs(base = "bitframe-service-builder-api", path = "bitframe-service/builder/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-service-builder-sdk-client", path = "bitframe-service/builder/sdk/client", "core", "react" /* "mock",*/)
includeSubs(base = "bitframe-service-builder-sdk-server", path = "bitframe-service/builder/sdk/server", "core")

includeSubs(base = "bitframe-api", "bitframe-api", "core" /* "ktor", "mock" */)
includeSubs(base = "bitframe-sdk-server", path = "sdk/server", "core", "ktor", "test")