package pimonitor.core.utils.disbursables

import bitframe.core.RestPath

open class DisbursableEndpoint(root: String) : RestPath(root) {
//    val create = "$root/create"
//    val load = "$root/load"
//    val update = "$root/update"
//    val delete = "$root/delete"
//    val all = "$root/all"

    private val disbursementRoot = "$root/disbursements"
    val disbursements = RestPath(disbursementRoot)
    val disbursementCreate = "$disbursementRoot/create"
    val disbursementUpdate = "$disbursementRoot/update"
    val disbursementDelete = "$disbursementRoot/delete"
}