@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client

import bitframe.client.BitframeAppScope
import bitframe.client.panel.PanelScope
import bitframe.client.signin.SignInScope
import pimonitor.client.businesses.BusinessesScope
import pimonitor.client.businesses.CreateBusinessScope
import pimonitor.client.contacts.ContactsScope
import pimonitor.client.portfolio.PortfolioScope
import pimonitor.client.signup.SignUpScope
import kotlin.js.JsExport

@JsExport
open class PiMonitorAppScope(
    override val config: PiMonitorAppScopeConfig,
) : BitframeAppScope {
    override val signIn by lazy { SignInScope(of(api.signIn)) }
    open val signUp by lazy { SignUpScope(of(api.register)) }
    override val panel by lazy { PanelScope(of(api.signIn)) }
    open val businesses by lazy { BusinessesScope(of(api)) }
    open val createBusiness by lazy { CreateBusinessScope(of(api)) }
    open val contacts by lazy { ContactsScope(of(api)) }
    open val portfolio by lazy { PortfolioScope(of(api)) }

    protected val api get() = config.api
    protected fun <S : Any> of(service: S) = config.toConfig(service)
}
