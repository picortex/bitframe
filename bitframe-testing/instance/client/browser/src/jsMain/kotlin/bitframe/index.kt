package bitframe

import applikation.konfig
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.api.BitframeService
import bitframe.api.BitframeServiceConfig
import bitframe.api.BitframeServiceKtorConfig
import bitframe.authentication.signin.exports.SignInReactScope
import bitframe.authentication.signin.exports.SignInScope
import bitframe.client.BitframeViewModelConfig
import bitframe.panel.PanelScope
import cache.BrowserCache
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.extensions.By
import kotlinx.extensions.get
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
    }

    val vmConfig = BitframeViewModelConfig(service)

    val scope = object : BitframeReactScope {
        override val signIn = SignInReactScope(vmConfig)
        override val panel = PanelScope(vmConfig)
    }

    val version: String by konfig()

    Bitframe(
        scope = scope,
        pages = mapOf(),
        sections = mapOf(),
        version = version
    )
}