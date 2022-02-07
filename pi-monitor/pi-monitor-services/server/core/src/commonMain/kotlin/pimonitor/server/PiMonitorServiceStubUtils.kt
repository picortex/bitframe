package pimonitor.server

import bitframe.actors.spaces.RegisterSpaceParams
import later.await
import pimonitor.authentication.signup.SignUpParams

private val TEST_PARAMS = listOf(
    SignUpParams.Individual(
        name = "Steven Sajja",
        email = "ssajja@gmail.com",
        password = "ssajja@gmail.com"
    ),
    SignUpParams.Business(
        businessName = "PiCortex LLC",
        individualName = "Mohamed Majapa",
        individualEmail = "mmajapa@gmail.com",
        password = "mmajapa@gmail.com"
    ),
    SignUpParams.Individual(
        name = "George Sechu",
        email = "jojipoji@gmail.com",
        password = "jojipoji@gmail.com"
    )
)

suspend fun PiMonitorService.populateTestEntities() {
    println(">>>> Populating test data")
    val res = TEST_PARAMS.map { signUp.signUp(it).await() }.last()
    listOf("PiCortex", "Mitikaz").map {
        val ref = res.user.ref()
        println("Setting up $it for ${ref.name}")
        spaces.register(RegisterSpaceParams(it, ref)).await()
        println("Finished Setting up $it for ${ref.name}")
    }
    println(">>>> Finished populating test data")
}