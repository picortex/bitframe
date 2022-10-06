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

rootProject.name = "asoft"

includeSubs("identifier", "../identifier", "core")

includeSubs("functions", "../functions", "core")
includeSubs("expect", "../expect", "core", "coroutines")
includeSubs("koncurrent-primitives", "../koncurrent/primitives", "core", "coroutines", "mock")
includeSubs("koncurrent-later", "../koncurrent/later", "core", "coroutines", "test")
includeSubs("cache", "../cache", "api", "browser", "file", "mock", "react-native", "test")
includeSubs("response", "../response", "core")

includeSubs("formatter", "../formatter", "core")

includeSubs("live", "../live", "core", "coroutines", "react", "test", "compose")

includeSubs("viewmodel", "../viewmodel", "core")
includeBuild("../kash/kash-generator")

includeSubs("kash", "../kash", "currency", "money")
includeSubs("krono", "../krono", "api")
includeSubs(base = "events", path = "../events", "core", "inmemory", "react")

includeSubs(base = "presenters", path = "../presenters", "actions", "core", "mock")
includeSubs(base = "mailer", "../mailer", "api", "mock", "smtp")
// submodules
// <BitframeUtils>
includeRoot("templater", path = "utils/templater")
includeRoot(name = "properties", path = "utils/properties")
includeRoot(name = "validation", path = "utils/validation")

includeSubs(base = "bitframe-actor", path = "actors", "core", "app", "user", "space")
includeSubs(base = "bitframe-dao", path = "daos", "core", "mock", "mongo", "file")
includeRoot(name = "bitframe-dao", path = "daos/universal")

// Bitframe Service
includeSubs(base = "bitframe-service-builder", path = "service/builder", "core", "daod", "rest")
includeSubs(base = "bitframe-service-builder-api", path = "service/builder/api", "core", "ktor", "mock")
includeSubs(base = "bitframe-service-builder-sdk-client", path = "service/builder/sdk/client", "core", "react" /* "mock",*/)
includeSubs(base = "bitframe-service-builder-sdk-server", path = "service/builder/sdk/server", "core")

includeSubs(base = "bitframe-api", "api", "core" /* "ktor", "mock" */)
includeSubs(base = "bitframe-sdk-server", path = "sdk/server", "core", "ktor", "test")