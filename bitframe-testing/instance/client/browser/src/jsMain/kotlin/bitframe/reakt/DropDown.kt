package bitframe.reakt

import presenters.fields.DropDownInputField
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.extensions.onOptionChanged
import react.Props
import react.RBuilder
import react.dom.attrs
import react.fc
import reakt.persist
import styled.css
import styled.styledDiv
import styled.styledOption
import styled.styledSelect
import theme.ThemeConsumer
import theme.dropdown_clazz
import theme.primaryColor

private class DropDownProps(
    val value: String?,
    val options: List<DropDownInputField.Option>,
    val isRequired: Boolean,
    val name: String,
    val data: Map<String, Any>?,
    val multipleSelect: Boolean,
    val onChange: ((String) -> Unit)?
) : Props

private val DropDown = fc<DropDownProps> { props ->
    ThemeConsumer { theme ->
        styledDiv {
            css {
                position = Position.relative
                padding(0.2.em)
                border(2.px, BorderStyle.solid, theme.primaryColor)
                borderRadius = 0.2.em

                hover {
                    boxShadow(theme.primaryColor, blurRadius = 10.px, spreadRadius = 2.px)
                }

                active {
                    boxShadow(theme.primaryColor, blurRadius = 10.px, spreadRadius = 2.px)
                }
                display = Display.flex
            }

            styledSelect {
                css {
                    border = "none"
                    flexBasis = FlexBasis("100%")
                    minWidth = 5.em
                    backgroundColor = Color.transparent
                    width = 100.pct
                    focus {
                        outline = Outline.none
                    }
                    +theme.dropdown_clazz
                }

                attrs["defaultValue"] = props.value ?: props.options.firstOrNull { it.selected }?.value ?: props.options.first().value
                props.data?.forEach { (k, v) -> attrs["data-$k"] = v }
                attrs {
                    required = props.isRequired
                    onOptionChanged {
                        it.persist()
                        props.onChange?.let {
                            it(value)
                        }
                    }
                    multiple = props.multipleSelect
                    name = props.name
                    props.options.forEach {
                        styledOption {
                            attrs {
                                key = it.label
                                value = it.value
                            }
                            +it.label
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.DropDown(
    value: String? = null,
    options: List<DropDownInputField.Option>,
    isRequired: Boolean = true,
    name: String,
    data: Map<String, Any>? = null,
    multipleSelect: Boolean = false,
    onChange: ((String) -> Unit)? = null
) = child(DropDown, DropDownProps(value, options, isRequired, name, data, multipleSelect, onChange))

fun RBuilder.DropDown(
    value: String? = null,
    options: List<String>,
    isRequired: Boolean = true,
    name: String,
    data: Map<String, Any>? = null,
    multipleSelect: Boolean = false,
    onChange: ((String) -> Unit)? = null
) = DropDown(
    value, options = options.mapIndexed { i, it ->
        DropDownInputField.Option(it, value = if (i == 0) "" else it)
    }, isRequired, name, data, multipleSelect, onChange
)