package forms

import koncurrent.Later
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import presenters.forms.*
import presenters.forms.fields.number
import presenters.forms.fields.text
import viewmodel.expect
import expect.expect
import koncurrent.SynchronousExecutor
import kotlin.test.Test

class FormViewModelTest {

    @Serializable
    data class TestParams(val name: String, val age: Int)

    class TestFields : Fields() {
        val name by text()
        val age by number()
    }

    fun sendToNetwork(params: TestParams) = Later<Int>(SynchronousExecutor) { resolve, reject ->
        println("Sending $params to the network")
        resolve(params.name.length)
//        reject(RuntimeException("Invalid parameters"))
    }

    var canceled = false
    val form = Form(
        heading = "Add Test Data", details = "These are just for testing purposes", fields = TestFields()
    ) {
        onCancel {
            println("Cancelling")
            canceled = true
            println("Cancelled")
        }
        onSubmit { p: TestParams -> sendToNetwork(p) }
    }.apply {
        fields.name.value = "Anderson"
        fields.age.value = 12.toString()
    }

    val config = FormViewModelConfig(
        form = form,
        codec = Json { },
        serializer = TestParams.serializer()
    )
    val vm = FormViewModel(config)

    @Test
    fun form_view_model_should_auto_submit_their_values() {
        vm.expect {
            submit()
        }.toGoThrough(
            FormState.Submitting(form),
            FormState.Submitted(form),
        )
    }

    @Test
    fun form_view_model_can_call_cancel() {
        vm.cancel()
        expect(canceled).toBe(true, "Expected viewmodel to be cancelled")
    }
}