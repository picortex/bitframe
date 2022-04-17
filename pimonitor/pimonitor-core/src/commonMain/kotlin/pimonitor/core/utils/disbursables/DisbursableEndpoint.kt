package pimonitor.core.utils.disbursables

open class DisbursableEndpoint(root: String) {
    val create = "$root/create"
    val load = "$root/load"
    val update = "$root/update"
    val delete = "$root/delete"
    val all = "$root/all"

    private val disbursement = "$root/disbursements"
    val disbursementCreate = "$disbursement/create"
    val disbursementUpdate = "$disbursement/update"
    val disbursementDelete = "$disbursement/delete"
}