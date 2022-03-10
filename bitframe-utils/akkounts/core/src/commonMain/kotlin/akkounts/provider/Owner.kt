@file:JsExport

package akkounts.provider

import akkounts.utils.unset
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField

/**
 * @param uid ID as stored in our databases
 * @param name Name as stored in our databases
 * @param foreignID ID as stored by the vendor
 * @param foreignName Name as stored by the vendor
 */
@Serializable
data class Owner(
    val uid: String,
    val name: String,
    val foreignID: String,
    val foreignName: String
) {
    companion object {
        @JvmField
        @Deprecated("This should be removed")
        val UNSET = Owner(uid = unset, name = unset, foreignID = unset, foreignName = unset)
    }
}