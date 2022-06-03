import bitframe.core.Space
import expect.expect
import expect.toBe
import kash.Money
import kash.TZS
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.DashboardOperational
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import pimonitor.core.search.SearchResult
import response.Response
import response.decodeResponseFromString
import response.encodeResponseToString
import response.responseOf
import kotlin.test.Ignore
import kotlin.test.Test

class InfoResultSerializationTest {

    val business = MonitoredBusinessBasicInfo(
        name = "PiCortex LLC",
        owningSpaceId = "space-89",
        operationalBoard = DASHBOARD_OPERATIONAL.NONE,
        financialBoard = DASHBOARD_FINANCIAL.NONE,
        email = "",
        phone = "",
        address = "",
        industry = "",
        website = "",
        about = "",
        logoUrl = null,
        uid = "monitoredbusinessbasicinfo-41",
        deleted = false
    )

    @Test
    fun should_serialize_primitives() {
        val ir: InfoResults<String> = InfoResults.Shared(business, "Three")
        println(Json.encodeToString(ir))
    }

    @Test
    fun should_serialize_classes() {
        val irm: InfoResults<Money> = InfoResults.Shared(business, 3000.TZS)
        println(Json.encodeToString(irm))

        val irs: InfoResults<Space> = InfoResults.Shared(business, Space("Test", type = "Test"))
//        val ir = InfoResults.Shared(4000.TZS)
        println(Json.encodeToString(irs))
    }

    @Test
    fun should_encode_a_non_shared_info_to_json() {
        val irs: InfoResults<Space> = InfoResults.NotShared(business, "Jeez")
        expect(Json.encodeToString(irs)).toBe("""{"business":{"name":"PiCortex LLC","owningSpaceId":"space-89","uid":"monitoredbusinessbasicinfo-41"},"message":"Jeez"}""")
    }

    @Test
    @Ignore
    fun should_decode_a_non_shared_info_from_json() {
        val irs: InfoResults<Space> = Json.decodeFromString(""""Jeez"""")
        expect(irs).toBe<InfoResults.NotShared>()
        expect(irs.asNotShared.message).toBe("Jeez")
    }

    @Test
    fun should_decode_a_response_of_an_info_result() {
        val irs: InfoResults<Space> = InfoResults.NotShared(business, "Jeez")
        val resp = responseOf(irs)
        val json = Json.encodeResponseToString(InfoResults.serializer(Space.serializer()), resp)
        val data = Json.decodeResponseFromString(InfoResults.serializer(Space.serializer()), json).response()
        expect(data).toBe<InfoResults.NotShared>()
    }

    @Test
    fun should_deserialize_an_operational_difference_board() {
        val json =
            """{"status":{"code":200,"message":"OK"},"payload":{"data":{"business":{"name":"PiCortex LLC","owningSpaceId":"space-89","operationalBoard":"NONE","financialBoard":"NONE","email":"","phone":"","address":"","industry":"","website":"","about":"","logoUrl":null,"uid":"monitoredbusinessbasicinfo-41","deleted":false},"message":"PiCortex LLC has not shared their reports with any dashboard"}}}"""
        val map = Mapper.decodeFromString(json)
        println(map)
        expect(map["status"]).toBe<Map<*, *>>()
        val data = Json.decodeResponseFromString(InfoResults.serializer(OperationalDifferenceBoard.serializer()), json).response()
        expect(data.business.name).toBe("PiCortex LLC")
    }
}