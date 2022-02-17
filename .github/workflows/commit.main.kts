#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.6.0")

import it.krzeminski.githubactions.actions.Action
import it.krzeminski.githubactions.actions.actions.CheckoutV2
import it.krzeminski.githubactions.actions.actions.SetupJavaV2
import it.krzeminski.githubactions.domain.RunnerType.UbuntuLatest
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.toYaml
import java.nio.file.Paths

val commit = workflow(
    name = "Commit Test",
    on = listOf(Push(branches = listOf("master-dev-*"))),
    sourceFile = Paths.get(".github/workflows/commit.main.kts"),
    targetFile = Paths.get(".github/workflows/commit.yml")
) {
    job(name = "testing", runsOn = UbuntuLatest) {
        uses(name = "Check out", action = CheckoutV2())
        uses(
            name = "Set up JDk 17",
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
            command = "./gradle jvmTest"
        )
        run(
            name = "Running jvm tests",
            command = "./gradlew jvmTest"
        )
        run(
            name = "Running js tests",
            command = "./gradlew jsTest"
        )
        run(
            name = "Build project",
            command = "./gradlew build"
        )
    }
}

println(commit.toYaml())
