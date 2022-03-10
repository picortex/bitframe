package pimonitor.core

sealed class RestEndpoint(private val root: String) {
    class Client(val url: String) : RestEndpoint("$url/api")
    class Server() : RestEndpoint("/api")

    // invites
    val invitesAcceptSage = "$root/invites/accept/sage"
    val invitesAcceptPicortex = "$root/invites/accept/picortex"
    val invitesLoad = "$root/invites/load"
    val invitesDefaultMessage = "$root/invites/message-default"
    val invitesSend = "$root/invites/send"
}