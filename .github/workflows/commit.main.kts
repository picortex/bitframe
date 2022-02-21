#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.8.0")

import it.krzeminski.githubactions.actions.actions.CheckoutV2
import it.krzeminski.githubactions.actions.actions.SetupJavaV2
import it.krzeminski.githubactions.domain.RunnerType.UbuntuLatest
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.dsl.JobBuilder
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.toYaml
import java.nio.file.Paths

fun gradlew(
    command: String,
    vararg env: Pair<String, String>
) = buildString {
    for (ex in env) append("""${ex.first}="${ex.second}" """)
    append("./gradlew $command")
}

fun JobBuilder.setupRepo() {
    uses(name = "Check out", action = CheckoutV2())
    uses(
        name = "Set up JDK 17",
        action = SetupJavaV2(
            distribution = SetupJavaV2.Distribution.Zulu,
            javaVersion = "17",
            cache = SetupJavaV2.BuildPlatform.Gradle
        )
    )
    run(
        name = "Make gradle executable",
        command = "chmod +x ./gradlew"
    )
    run(
        name = "Downloading gradle",
        command = gradlew("--version")
    )
}

val commit = workflow(
    name = "Commit Test",
    on = listOf(Push(branches = listOf("master-dev-*"))),
    sourceFile = Paths.get(".github/workflows/commit.main.kts"),
    targetFile = Paths.get(".github/workflows/commit.yml"),
) {
    job(name = "Testing", runsOn = UbuntuLatest) {
        setupRepo()
        run(
            name = "Running jvm tests",
            command = """API_MODE="MOCK" ./gradlew jvmTest"""
        )
        run(
            name = "Run pimonitor-app-server tests",
            command = """API_MODE="MOCK" ./gradlew :pimonitor-app-server:test"""
        )
        run(
            name = "Running js tests",
            command = """API_MODE="MOCK" ./gradlew jsTest"""
        )
        run(
            name = "Build project",
            command = """API_MODE="MOCK" ./gradlew build"""
        )
    }
}

println(commit.toYaml(addConsistencyCheck = false))
