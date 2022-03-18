import bitframe.core.Space
import expect.expect
import kash.Money
import kash.TZS
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.invites.InfoResults
import kotlin.test.Test

class InfoResultSerialization {
    @Test
    fun should_serialize_primitives() {
        val ir: InfoResults<String> = InfoResults.Shared("Three")
        println(Json.encodeToString<InfoResults<String>>(ir))
    }

    @Test
    fun should_serialize_classes() {
        val irm: InfoResults<Money> = InfoResults.Shared(3000.TZS)
        println(Json.encodeToString(irm))

        val irs: InfoResults<Space> = InfoResults.Shared(Space("Test", type = "Test"))
//        val ir = InfoResults.Shared(4000.TZS)
        println(Json.encodeToString(irs))
    }

    @Test
    fun should_decode_json_values() {
        val obj = Json.decodeFromString<InfoResults<Money>>("""{"amount":400000,"currency":"TZS"}""")
        val shared = obj as InfoResults.Shared
        expect(shared.data.amount).toBe(400000)
    }
}