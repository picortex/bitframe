package bitframe

import applikation.konfig
import bitframe.client.*
import bitframe.authentication.client.signin.SignInService
import bitframe.client.signin.SignInServiceKtor
import bitframe.client.spaces.SpacesServiceKtor
import bitframe.client.users.UsersServiceKtor
import bitframe.core.spaces.SpacesService
import bitframe.core.users.UsersService
import bitframe.authentication.client.signout.SignOutService
import bitframe.client.BitframeScopeConfig
import bitframe.client.panel.PanelReactScope
import cache.BrowserCache
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.extensions.By
import kotlinx.extensions.get
import org.w3c.dom.HTMLDivElement
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val konfig = konfig()

    val config = BitframeApiKtorConfig(
        appId = "test-client",
        url = konfig["url"]?.toString() ?: window.location.origin,
        cache = BrowserCache()
    )

    val service = object : BitframeApi {
        override val config: BitframeApiConfig = config
        override val spaces: SpacesService = SpacesServiceKtor(config)
        override val users: UsersService = UsersServiceKtor(config)
        override val signIn: SignInService = SignInServiceKtor(config)
        override val signOut: SignOutService = SignOutService(config)
    }

    val vmConfig = BitframeScopeConfig(service)

    val scope = object : BitframeReactAppScope, SessionAware by SessionAwareImpl(service) {
        override val signIn = SignInReactScope(vmConfig)
        override val config: BitframeScopeConfig = vmConfig
        override val panel = PanelReactScope(vmConfig)
    }

    val version: String by konfig()

    Bitframe(
        scope = scope,
        pages = mapOf(),
        sections = mapOf(),
        version = version
    )
}