package bitframe

import applikation.konfig
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.client.BitframeService
import bitframe.client.BitframeServiceKtorConfig
import cache.BrowserCache
import kotlinx.browser.document
import kotlinx.extensions.By
import kotlinx.extensions.get
import org.w3c.dom.HTMLDivElement
import reakt.setContent

fun main() = document.get<HTMLDivElement>(By.id("root")).setContent {
    val config = BitframeServiceKtorConfig(
        appId = "test-client",
        url = "http://localhost:8080",
        cache = BrowserCache()
    )
    val client = object : BitframeService {
        override val spaces: SpacesService = SpacesServiceKtor(config)
        override val users: UsersService = UsersServiceKtor(config)
        override val signIn: SignInService = SignInServiceKtor(config)
    }
    val version: String by konfig()
    Bitframe(
        client = client,
        pages = mapOf(),
        sections = mapOf(),
        version = version
    )
}