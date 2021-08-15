package unit

import bitframe.server.data.DataSource
import bitframe.InMemoryDataSource
import bitframe.modules.Customer
import expect.expect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class InMemoryDatasourceTest {
    val db: DataSource = InMemoryDataSource()

    @Test
    fun should_create_things_in_memory() {
        val json = Json.encodeToString(Customer.JOHN_DOE)
        val map = Mapper.decodeFromString(json)
        val res = db.create(map)
        expect(res["name"]).toBe("John Doe")
    }
}