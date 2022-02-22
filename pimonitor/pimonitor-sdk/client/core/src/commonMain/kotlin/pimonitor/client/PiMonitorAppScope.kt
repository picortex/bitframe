@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScope
import bitframe.client.BitframeAppScopeConfig
import bitframe.client.SessionAware
import bitframe.client.SessionAwareImpl
import bitframe.client.panel.PanelScope
import bitframe.client.password.ChangePasswordScope
import bitframe.client.signin.SignInScope
import pimonitor.client.businesses.BusinessesScope
import pimonitor.client.businesses.CreateBusinessScope
import pimonitor.client.contacts.ContactsScope
import pimonitor.client.portfolio.PortfolioScope
import pimonitor.client.signup.SignUpScope
import kotlin.js.JsExport

open class PiMonitorAppScope(
    override val config: BitframeAppScopeConfig<PiMonitorApi>,
) : BitframeAppScope<PiMonitorApi> {

    open val api get() = config.api
    protected fun <S : Any> of(service: S) = config.toConfig(service)

    override val session: SessionAware get() = config.api.session

    override val signIn by lazy { SignInScope(config { api.signIn }) }
    open val signUp by lazy { SignUpScope(config()) }
    override val panel by lazy { PanelScope(config { api.signIn }) }
    open val businesses by lazy { BusinessesScope(config()) }
    open val createBusiness by lazy { CreateBusinessScope(config()) }
    open val contacts by lazy { ContactsScope(config()) }
    open val portfolio by lazy { PortfolioScope(config()) }
    open val password by lazy { ChangePasswordScope(config { api.profile }) }
}
