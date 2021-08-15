package bitframe.types

import bitframe.server.types.Field
import bitframe.server.types.TypeInfo
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.starProjectedType

actual inline fun <reified T> processTypes(): TypeInfo {
    return processKotlinClass<T>()
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> processKotlinClass(): TypeInfo {
    val clazz = T::class
    val exception = Exception("Failed to process name for ${T::class.java.canonicalName}")
    val singular = clazz.simpleName?.lowercase() ?: throw exception
    val plural = "${singular}s"
    val fields = buildList {
        (clazz.members - clazz.memberFunctions).forEach {
            when (it.returnType) {
                String::class.starProjectedType -> add(Field.Primitive.StringField(it.name))
                Int::class.starProjectedType -> add(Field.Primitive.IntField(it.name))
            }
        }
    }
    return TypeInfo(singular, plural, fields)
}

@OptIn(ExperimentalStdlibApi::class)
fun processJavaClass(clazz: Class<*>): TypeInfo {
    val singular = clazz.simpleName.lowercase()
    val plural = "${singular}s"
    val fields = buildList {
        clazz.declaredFields.forEach {
            when (it.type) {
                String::class.java -> add(Field.Primitive.StringField(it.name))
                Int::class.java -> add(Field.Primitive.IntField(it.name))
            }
        }
    }
    return TypeInfo(singular, plural, fields)
}