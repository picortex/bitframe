@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.client.BitframeAppScope
import bitframe.core.actors.spaces.Space
import bitframe.core.User
import bitframe.authentication.signin.SignInScope
import bitframe.client.panel.PanelScope
import bitframe.core.service.Session
import live.Live
import pimonitor.authentication.signup.SignUpScope
import pimonitor.evaluation.businesses.BusinessesScope
import pimonitor.evaluation.businesses.CreateBusinessScope
import pimonitor.evaluation.contacts.ContactsScope
import pimonitor.portfolio.PortfolioScope
import kotlin.js.JsExport

open class PiMonitorAppScope(
    override val config: PiMonitorScopeConfig,
    override val signIn: SignInScope = SignInScope(config),
    open val signUp: SignUpScope = SignUpScope(config),
    override val panel: PanelScope = PanelScope(config),
    open val businesses: BusinessesScope = BusinessesScope(config),
    open val createBusiness: CreateBusinessScope = CreateBusinessScope(config),
    open val contacts: ContactsScope = ContactsScope(config),
    open val portfolio: PortfolioScope = PortfolioScope(config)
) : BitframeAppScope {

    override val userLiveSession: Live<Session> get() = config.api.signIn.session
    override val currentUser: User? get() = userSession?.user
    override val currentSpace: Space? get() = userSession?.space
    override val signOut: () -> Unit = { config.api.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { config.api.signIn.switchToSpace(it) }
}