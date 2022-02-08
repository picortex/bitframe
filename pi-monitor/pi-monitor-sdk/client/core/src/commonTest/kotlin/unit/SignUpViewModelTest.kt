package unit

import kotlinx.coroutines.test.runTest
import logging.Logger
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.api.PiMonitorServiceMock
import pimonitor.authentication.signup.*
import presenters.feedbacks.FormFeedback
import viewmodel.expect
import kotlin.test.Test

class SignUpViewModelTest {
    val service: PiMonitorService = PiMonitorServiceMock()
    val config = PiMonitorViewModelConfig(
        service = service,
        logger = Logger()
    )
    val viewModel = SignUpViewModel(config)

    @Test
    fun should_register_successfully() = runTest {
        val params = SignUpParams(
            name = "John Doe",
            email = "john@doe.com",
            password = "john@doe.com"
        )
        val initialFields = IndividualFormFields()
        val initialState = SignUpState.IndividualForm(initialFields, null)
        expect(viewModel).toBeIn(initialState)
        val intent = SignUpIntent.Submit.IndividualForm(params)
        viewModel.expect(intent)
        val finalState = initialState.copy(
            fields = initialFields.copy(intent),
            status = FormFeedback.Success("Successfully signed in")
        )
        expect(viewModel).toBeIn(finalState)
    }
}