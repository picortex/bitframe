package pimonitor.client

interface TestDecider {
    suspend fun decide(): TestType
}