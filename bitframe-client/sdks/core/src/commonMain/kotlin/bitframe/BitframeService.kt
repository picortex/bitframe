package bitframe

import bitframe.authentication.signin.SignInService

interface BitframeService : MiniService {
    val signIn: SignInService
}