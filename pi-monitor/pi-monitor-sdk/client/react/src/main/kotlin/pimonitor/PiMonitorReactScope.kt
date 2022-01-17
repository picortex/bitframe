@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.BitframeReactScope
import bitframe.api.SessionAware
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.panel.PanelReactScope
import live.Live
import pimonitor.authentication.signup.exports.SignUpReactScope
import pimonitor.evaluation.businesses.exports.BusinessesReactScope
import pimonitor.evaluation.businesses.exports.CreateBusinessReactScope
import pimonitor.evaluation.contacts.exports.ContactsReactScope

class PiMonitorReactScope private constructor(
    override val signIn: SignInReactScope,
    override val signUp: SignUpReactScope,
    override val panel: PanelReactScope,
    override val businesses: BusinessesReactScope,
    override val createBusiness: CreateBusinessReactScope,
    override val contacts: ContactsReactScope
) : PiMonitorScope(signIn, signUp, panel, businesses, createBusiness, contacts),
    BitframeReactScope, SessionAware {
    @JsName("fromViewModelConfig")
    constructor(config: PiMonitorViewModelConfig) : this(
        SignInReactScope(config),
        SignUpReactScope(config),
        PanelReactScope(config),
        BusinessesReactScope(config),
        CreateBusinessReactScope(config),
        ContactsReactScope(config)
    )

    override val userLiveSession: Live<Session> = signIn.service.signIn.session
    override val currentUser: User? get() = userSession?.user
    override val currentSpace: Space? get() = userSession?.space
}