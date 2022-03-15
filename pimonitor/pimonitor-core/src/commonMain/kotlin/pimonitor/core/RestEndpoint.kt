package pimonitor.core

sealed class RestEndpoint(private val root: String) {
    class Client(val url: String) : RestEndpoint("$url/api")
    object Server : RestEndpoint("/api")

    //businesses
    private val businesses = "$root/businesses"
    val businessesAll = "$businesses/all"
    val businessesCreate = "$businesses/create"
    val businessesDelete = "$businesses/delete"
    private val businessesDashboard = "$businesses/dashboard"
    val businessesDashboardOperational = "$businessesDashboard/operational"

    // invites
    val invitesAcceptSage = "$root/invites/accept/sage"
    val invitesAcceptPicortex = "$root/invites/accept/picortex"
    val invitesLoad = "$root/invites/load"
    val invitesDefaultMessage = "$root/invites/message-default"
    val invitesSend = "$root/invites/send"
}