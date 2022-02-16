package bitframe.client.password

import bitframe.client.UIScopeConfig
import bitframe.client.profile.ProfileService
import kotlinx.coroutines.CoroutineScope
import viewmodel.ViewModel
import bitframe.client.password.ChangePasswordIntent as Intent
import bitframe.client.password.ChangePasswordState as State

class ChangePasswordViewModel(
    private val config: UIScopeConfig<ProfileService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    override fun CoroutineScope.execute(i: bitframe.client.password.ChangePasswordIntent): Any {
        TODO("Not yet implemented")
    }
}