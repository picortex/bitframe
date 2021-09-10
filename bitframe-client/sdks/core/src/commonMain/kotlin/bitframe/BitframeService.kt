package bitframe

import bitframe.authentication.SignInService

interface BitframeService : MiniService {
    val signIn: SignInService
}