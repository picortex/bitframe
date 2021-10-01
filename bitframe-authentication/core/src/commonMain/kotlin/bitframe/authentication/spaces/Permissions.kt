package bitframe.authentication.spaces

import bitframe.authentication.ISystemPermission
import kotlin.js.JsExport

enum class Permissions(
    override val title: String,
    override val details: String,
    override val needs: List<String> = listOf(),
) : ISystemPermission {
    Read(
        title = "acceptance.authentication.accounts.read",
        details = "Grants access to view/edit accounts in the system"
    ),
    Create(
        title = "acceptance.authentication.accounts.create",
        details = "Grants access to create different accounts for the system",
        needs = listOf(Read.title)
    ),
    Update(
        title = "acceptance.authentication.accounts.update",
        details = "Grants access to update account information",
        needs = listOf(Read.title)
    ),
    Delete(
        title = "acceptance.authentication.accounts.delete",
        details = "Grants access to delete accounts from the system",
        needs = listOf(Read.title)
    ),
    Wipe(
        title = "acceptance.authentication.accounts.wipe",
        details = "Grants access to permanently wipe accounts from the system",
        needs = listOf(Read.title)
    )
}