@file:JsExport

package bitframe.authentication.spaces

import kotlin.js.JsExport

fun CreateSpaceParams.toSpace(uid: String) = Space(
    uid = uid,
    name = name,
    scope = "",
    type = ""
)