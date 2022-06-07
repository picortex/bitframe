import versioning.DualVersion

object versions {
    val docker = "7.1.0"

    val dokka = "1.5.0"

    val npmPublish = "2.1.2"


    object bitframe {
        private val latest = "0.10.45"
        val staging = DualVersion(
            name = "staging",
            current = latest,
            previous = "0.10.44"
        )
        val stagingCurrent = staging.current.raw
        val stagingPrevious = staging.previous.raw

        val production = DualVersion(
            name = "production",
            current = latest,
            previous = "0.10.44"
        )
        val productionCurrent = production.current.raw
        val productionPrevious = production.previous.raw
    }
}