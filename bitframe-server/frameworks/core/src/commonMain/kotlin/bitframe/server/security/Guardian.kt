package bitframe.server.security

interface Guardian {
    fun can(appId: String)
}