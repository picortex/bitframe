package bitframe.client.profile

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import presenters.feedbacks.Feedback
import viewmodel.ViewModel

class ProfileViewModel(
    private val config: UIScopeConfig<ProfileService>
) : ViewModel<ProfileIntent, ProfileState>(ProfileState.INITIAL, config.viewModel) {

    private val service get() = config.service

    override fun CoroutineScope.execute(i: ProfileIntent) = when (i) {
        is ProfileIntent.Init -> initialize()
    }

    private fun CoroutineScope.initialize() = launch {
        flow<ProfileState> {
            emit(ProfileState.INITIAL)
            val profile = service.currentUserProfile().await()
        }.catch {
            emit(ProfileState(status = Feedback.Failure(it)))
        }.collect {
            ui.value = it
        }
    }
}