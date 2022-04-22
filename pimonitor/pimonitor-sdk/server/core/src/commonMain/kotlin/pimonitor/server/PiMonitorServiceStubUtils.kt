package pimonitor.server

import bitframe.core.RequestBody
import later.await
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams

private val TEST_PARAMS = listOf(
    SignUpIndividualParams(
        name = "Steven Sajja",
        email = "ssajja@gmail.com",
        password = "ssajja@gmail.com"
    ),
    SignUpBusinessParams(
        businessName = "PiCortex LLC",
        individualName = "Mohamed Majapa",
        individualEmail = "mmajapa@gmail.com",
        password = "mmajapa@gmail.com"
    ),
    SignUpIndividualParams(
        name = "George Sechu",
        email = "jojipoji@gmail.com",
        password = "jojipoji@gmail.com"
    )
)

suspend fun PiMonitorService.populateTestEntities() {
    println(">>>> Populating test data")
    val res = TEST_PARAMS.map { signup.signUp(RequestBody.UnAuthorized("test", it)).await() }.last()
//    listOf("PiCortex", "Mitikaz").map {
//        val ref = res.user.ref()
//        println("Setting up $it for ${ref.name}")
//        spaces.register(RegisterSpaceParams(it, ref)).await()
//        println("Finished Setting up $it for ${ref.name}")
//    }
    println(">>>> Finished populating test data")
}