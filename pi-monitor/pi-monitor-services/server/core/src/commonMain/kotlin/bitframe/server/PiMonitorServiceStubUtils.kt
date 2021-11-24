package bitframe.server

import bitframe.authentication.spaces.RegisterSpaceParams
import later.await
import bitframe.authentication.signup.SignUpParams

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
    val last = TEST_PARAMS.map { signUp.signUp(it) }.last()
    val res = last.await()
    listOf("PiCortex", "Mitikaz").map {
        val ref = res.user.ref()
        println("Setting up $it for ${ref.name}")
        spaces.register(RegisterSpaceParams(it, ref)).await()
        println("Finished Setting up $it for ${ref.name}")
    }
    println(">>>> Finished populating test data")
}