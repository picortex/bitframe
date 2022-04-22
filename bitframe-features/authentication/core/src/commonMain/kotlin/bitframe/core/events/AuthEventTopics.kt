package bitframe.core.events

import kotlin.jvm.JvmField

object AuthEventTopics {
    @JvmField
    val SIGNED_IN = "authentication.signed.in"

    @JvmField
    val SIGNED_OUT = "authentication.signed.out"

    @JvmField
    val SPACE_SWITCHED = "authentication.space.switched"
}