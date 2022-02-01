@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor

import bitframe.BitframeScope
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.exports.SignInScope
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.panel.PanelScope
import live.Live
import pimonitor.authentication.signup.exports.SignUpScope
import pimonitor.evaluation.businesses.exports.BusinessesScope
import pimonitor.evaluation.businesses.exports.CreateBusinessScope
import pimonitor.evaluation.contacts.exports.ContactsScope
import pimonitor.portfolio.PortfolioScope

open class PiMonitorScope(
    override val config: PiMonitorViewModelConfig,
    override val signIn: SignInScope,
    open val signUp: SignUpScope,
    override val panel: PanelScope,
    open val businesses: BusinessesScope,
    open val createBusiness: CreateBusinessScope,
    open val contacts: ContactsScope,
    open val portfolio: PortfolioScope
) : BitframeScope {

    override val userLiveSession: Live<Session> get() = config.service.signIn.session
    override val currentUser: User? get() = userSession?.user
    override val currentSpace: Space? get() = userSession?.space
    override val signOut: () -> Unit = { config.service.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { config.service.signIn.switchToSpace(it) }
}