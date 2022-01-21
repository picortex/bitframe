package bitframe

import applikation.konfig
import bitframe.api.*
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.authentication.client.signout.SignOutService
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.client.BitframeViewModelConfig
import bitframe.panel.PanelReactScope
import cache.BrowserCache
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.extensions.By
import kotlinx.extensions.get
import live.Live
import org.w3c.dom.HTMLDivElement
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val konfig = konfig()

    val config = BitframeServiceKtorConfig(
        appId = "test-client",
        url = konfig["url"]?.toString() ?: window.location.origin,
        cache = BrowserCache()
    )

    val service = object : BitframeService {
        override val config: BitframeServiceConfig = config
        override val spaces: SpacesService = SpacesServiceKtor(config)
        override val users: UsersService = UsersServiceKtor(config)
        override val signIn: SignInService = SignInServiceKtor(config)
        override val signOut: SignOutService = SignOutService(config)
    }

    val vmConfig = BitframeViewModelConfig(service)

    val scope = object : BitframeReactScope, SessionAware by SessionAwareImpl(service) {
        override val signIn = SignInReactScope(vmConfig)
        override val config: BitframeViewModelConfig = vmConfig
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