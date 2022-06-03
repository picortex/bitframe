package pimonitor.core.business.info.params

import kotlinx.serialization.Serializable

@Serializable
data class BusinessInfoParams(
    override val businessId: String,
    override val name: String,
    override val industry: String,
    override val address: String,
    override val phone: String,
    override val email: String,
    override val website: String,
    override val about: String
) : BusinessInfoRawParams