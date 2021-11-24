package bitframe.evaluation.businesses

import bitframe.components.TextInput
import kotlinx.css.*
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import bitframe.evaluation.businesses.forms.CreateBusinessState
import bitframe.monitored.CreateMonitoredBusinessParams
import react.RBuilder
import reakt.ContainedButton
import reakt.FlexBox
import reakt.Form
import reakt.centerContent
import styled.css
import styled.styledDiv
import styled.styledH2
import theme.clazz

internal fun RBuilder.CreateBusinessForm(
    state: CreateBusinessState.Form,
    onSubmit: (params: CreateMonitoredBusinessParams) -> Unit
) = FlexBox { theme ->
    css {
        centerContent()
        onMobile { padding(horizontal = 1.em) }
        onDesktop { padding(horizontal = 20.pct) }
    }

    val fields = state.fields
    Form {
        fields.inviterIntroduction?.let {
            styledDiv { +it }
        }
        styledH2 {
            css { +theme.text.h2.clazz }
            +fields.title
        }
        TextInput("businessName", fields.businessName)
        TextInput("contactName", fields.contactName)
        TextInput("contactEmail", fields.contactEmail)
        ContainedButton(fields.submitButton.text)
    } onSubmit {
        val businessName by text()
        val contactName by text()
        val contactEmail by text()

        val params = CreateMonitoredBusinessParams(businessName, contactName, contactEmail)
        onSubmit(params)
    }
}