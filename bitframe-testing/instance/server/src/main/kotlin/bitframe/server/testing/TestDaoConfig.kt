package bitframe.server.testing

import bitframe.actors.spaces.Space

private val space = Space(
    uid = "space-1",
    name = "Steven's Workspace",
    type = "",
    scope = ""
)
/*
fun TestSpacesDaoConfig() = SpacesDaoInMemoryConfig(
    spaces = mutableMapOf("space-1" to space)
)

fun TestUsersDaoConfig() = UsersDaoInMemoryConfig(
    users = mutableMapOf(
        "user-01" to User(
            uid = "user-01",
            name = "Steven Sajja",
            tag = "ssajja",
            contacts = Contacts("ssajja@gmail.com"),
            spaces = listOf(space)
        )
    )
)
*/