package bitframe.core.profile

import bitframe.core.ServiceConfig
import later.Later

open class ProfileService(
    private val config: ServiceConfig
) {
    fun changePassword(params: Any): Later<Any> {
        TODO()
    }
}