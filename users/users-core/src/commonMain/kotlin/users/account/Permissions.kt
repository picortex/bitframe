package users.account

import users.ISystemPermission

enum class Permissions(
    override val title: String,
    override val details: String,
    override val needs: List<String> = listOf(),
) : ISystemPermission {
    Read(
        title = "authentication.accounts.read",
        details = "Grants access to view/edit accounts in the system"
    ),
    Create(
        title = "authentication.accounts.create",
        details = "Grants access to create different accounts for the system",
        needs = listOf(Read.title)
    ),
    Update(
        title = "authentication.accounts.update",
        details = "Grants access to update account information",
        needs = listOf(Read.title)
    ),
    Delete(
        title = "authentication.accounts.delete",
        details = "Grants access to delete accounts from the system",
        needs = listOf(Read.title)
    ),
    Wipe(
        title = "authentication.accounts.wipe",
        details = "Grants access to permanently wipe accounts from the system",
        needs = listOf(Read.title)
    )
}