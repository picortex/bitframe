@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.BitframeScope
import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.authentication.signin.SignInScope
import bitframe.panel.PanelScope
import bitframe.service.Session
import live.Live
import pimonitor.authentication.signup.SignUpScope
import pimonitor.evaluation.businesses.BusinessesScope
import pimonitor.evaluation.businesses.CreateBusinessScope
import pimonitor.evaluation.contacts.ContactsScope
import pimonitor.portfolio.PortfolioScope
import kotlin.js.JsExport

open class PiMonitorScope(
    override val config: PiMonitorScopeConfig,
    override val signIn: SignInScope = SignInScope(config),
    open val signUp: SignUpScope = SignUpScope(config),
    override val panel: PanelScope = PanelScope(config),
    open val businesses: BusinessesScope = BusinessesScope(config),
    open val createBusiness: CreateBusinessScope = CreateBusinessScope(config),
    open val contacts: ContactsScope = ContactsScope(config),
    open val portfolio: PortfolioScope = PortfolioScope(config)
) : BitframeScope {

    override val userLiveSession: Live<Session> get() = config.service.signIn.session
    override val currentUser: User? get() = userSession?.user
    override val currentSpace: Space? get() = userSession?.space
    override val signOut: () -> Unit = { config.service.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { config.service.signIn.switchToSpace(it) }
}