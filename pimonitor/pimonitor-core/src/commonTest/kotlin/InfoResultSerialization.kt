import bitframe.core.Space
import bitframe.core.User
import expect.expect
import expect.toBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.invites.InfoResults
import kotlin.test.Test

class InfoResultSerialization {
    @Test
    fun should_serialize_primitives() {
        val ir = InfoResults.Shared("Three")
        println(Json.encodeToString<InfoResults<String>>(ir))
    }

    @Test
    fun should_serialize_classes() {
        val ir: InfoResults<Space> = InfoResults.Shared(Space("Test", type = "Test"))
        println(Json.encodeToString(ir))
    }

    @Test
    fun should_decode_json_values() {
        val obj = Json.decodeFromString<InfoResults<Space>>("""{"name":"Test","type":"Test"}""")
        val shared = obj as InfoResults.Shared
        expect(shared.data.name).toBe("Test")
    }
}