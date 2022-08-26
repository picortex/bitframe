@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields


import presenters.fields.internal.NumberBasedValueField
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

class LongInputField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override val defaultValue: Long? = ValuedField.DEFAULT_VALUE,
    override val isReadonly: Boolean = ValuedField.DEFAULT_IS_READONLY,
    override val isRequired: Boolean = ValuedField.DEFAULT_IS_REQUIRED,
    override val max: Long? = DEFAULT_MAX,
    override val min: Long? = DEFAULT_MIN,
    override val step: Long = DEFAULT_STEP,
    override val validator: ((Long?) -> Unit)? = ValuedField.DEFAULT_VALIDATOR
) : NumberBasedValueField<Long>(name, label, hint, defaultValue, isReadonly, isRequired, max, min, step, validator) {
    companion object {
        val DEFAULT_STEP = 1L
    }

    @JsName("_ignore_fromPropery")
    constructor(
        name: KProperty<*>,
        label: String = name.name,
        hint: String = label,
        defaultValue: Long? = ValuedField.DEFAULT_VALUE,
        isReadonly: Boolean = ValuedField.DEFAULT_IS_READONLY,
        isRequired: Boolean = ValuedField.DEFAULT_IS_REQUIRED,
        max: Long? = DEFAULT_MAX,
        min: Long? = DEFAULT_MIN,
        step: Long = DEFAULT_STEP,
        validator: ((Long?) -> Unit)? = ValuedField.DEFAULT_VALIDATOR
    ) : this(name.name, label, hint, defaultValue, isReadonly, isRequired, max, min, step, validator)

    override var stringValue: String
        get() = value.toString()
        set(v) = try {
            value = v.toLong()
        } finally {

        }

    val valueAsDouble get() = this.value?.toDouble()
    val maxAsDouble get() = this.max?.toDouble()
    val minAsDouble get() = this.min?.toDouble()
    val stepAsDouble get() = this.step.toDouble()

    override fun increment(step: Long?) {
        value = (value ?: 0) + (step ?: DEFAULT_STEP)
    }

    override fun decrement(step: Long?) {
        value = (value ?: 0) - (step ?: DEFAULT_STEP)
    }
}