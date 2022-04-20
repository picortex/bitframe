import bitframe.core.Space
import expect.expect
import expect.toBe
import kash.Money
import kash.TZS
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.invites.InfoResults
import response.Response
import response.decodeResponseFromString
import response.encodeResponseToString
import response.responseOf
import kotlin.test.Test

class InfoResultSerializationTest {
    @Test
    fun should_serialize_primitives() {
        val ir: InfoResults<String> = InfoResults.Shared("Three")
        println(Json.encodeToString(ir))
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
//        val money = Json.decodeFromString<Money>("""{"amount":400000,"currency":"TZS"}""")
        val obj = Json.decodeFromString(InfoResults.serializer(Money.serializer()), """{"amount":400000,"currency":"TZS"}""")
        val shared = obj as InfoResults.Shared
        expect(shared.data.amount).toBe(400000)
    }

    @Test
    fun should_encode_a_non_shared_info_to_json() {
        val irs: InfoResults<Space> = InfoResults.NotShared("Jeez")
        expect(Json.encodeToString(irs)).toBe(""""Jeez"""")
    }

    @Test
    fun should_decode_a_non_shared_info_from_json() {
        val irs: InfoResults<Space> = Json.decodeFromString(""""Jeez"""")
        expect(irs).toBe<InfoResults.NotShared>()
        expect(irs.asNotShared.message).toBe("Jeez")
    }

    @Test
    fun should_decode_a_response_of_an_info_result() {
        val irs: InfoResults<Space> = InfoResults.NotShared("Jeez")
        val resp = responseOf(irs)
        val json = Json.encodeResponseToString(InfoResults.serializer(Space.serializer()), resp)
        val data = Json.decodeResponseFromString(InfoResults.serializer(Space.serializer()), json).response()
        expect(data).toBe<InfoResults.NotShared>()
    }
}