package forms

import expect.expect
import koncurrent.Later
import koncurrent.later.catch
import koncurrent.later.then
//import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import live.expect
import live.toHaveGoneThrough1
import live.toHaveGoneThrough2
import presenters.forms.*
import presenters.forms.FormState.*
import presenters.forms.fields.email
import presenters.forms.fields.number
import presenters.forms.fields.text
import viewmodel.ScopeConfig
import viewmodel.ViewModel
import viewmodel.ViewModelConfig
import kotlin.test.Test

class FormTest {

    class PersonForm(
        config: FormConfig<Map<String, String>>,
        builder: FormActionsBuildingBlock<Map<String, String>>
    ) : Form<TestFormFields, Map<String, String>>(
        heading = "Person Form",
        details = "Add this form to fill a person",
        fields = TestFormFields(),
        config, builder
    )

    class TestFormFields : Fields() {
        val name by text()
        val email by email(
            isRequired = true,
            validator = {
                if (it == "andy@lamax.com") throw IllegalArgumentException("Wrong email")
                ""
            }
        )
    }

    @Test
    fun should_easily_interact_with_text_fields() {
        val fields = TestFormFields()
        val name = fields.name
        name.apply {
            value = "A"
            value = "An"
            value = "And"
            value = "Anderson"
        }
        expect(name.value).toBe("Anderson")
    }

    @Test
    fun person_form_should_be_able_to_recover_after_failure() = runTest {
        val form = PersonForm(ScopeConfig(Unit).toFormConfig()) {
            onSubmit {
                println(it)
                Later.resolve(Unit)
            }
        }

        form.fields.apply {
            name.value = "Anderson"
            email.value = "andy@lamax.com"
        }
        form.submit()
        val (_, s1) = expect(form.ui).toHaveGoneThrough2<Submitting, Failure>()
        expect(s1.message).toBe("Some values are invalid")

        form.ui.history.clear()

        form.fields.apply {
            name.value = "Anderson"
            email.value = "andy@lamax.me"
        }
        form.submit()
        expect(form.fields.email.value).toBe("andy@lamax.me")
        val (_, s2) = expect(form.ui).toHaveGoneThrough2<Submitting, Submitted>()
    }
}