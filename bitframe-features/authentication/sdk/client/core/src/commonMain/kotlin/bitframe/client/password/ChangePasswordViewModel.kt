package bitframe.client.password

import bitframe.client.UIScopeConfig
import bitframe.client.profile.ProfileService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import bitframe.client.password.ChangePasswordIntent as Intent
import bitframe.client.password.ChangePasswordState as State

class ChangePasswordViewModel(
    private val config: UIScopeConfig<ProfileService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    private val service get() = config.service
    override fun CoroutineScope.execute(i: Intent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Changing your password, please wait . . .")))
            service.changePassword(i.params).await()
            delay(config.viewModel.transitionTime)
            emit(state.copy(status = Feedback.Success("Password changed successfully")))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it)))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }
}