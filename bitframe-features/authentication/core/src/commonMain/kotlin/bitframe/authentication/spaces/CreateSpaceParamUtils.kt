package bitframe.authentication.spaces

fun CreateSpaceParams.toSpace(uid: String) = Space(
    uid = uid,
    name = name,
    scope = "",
    type = ""
)