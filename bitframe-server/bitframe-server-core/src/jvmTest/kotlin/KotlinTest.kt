import bitframe.Lie
//import expect.expect
//import expect.toBe
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.reflect.full.allSupertypes
import kotlin.reflect.full.starProjectedType
import kotlin.test.Test

class KotlinTest {

    @Lie("Just a lie")
    @Serializable
    class Customer(val name: String)

    @Test
    fun should_pass() {
//        expect(1 + 1).toBe(2)
    }

    @Test
    fun observer_serializers() {
        val serializer = Customer.serializer()
    }

    @Test
    fun customer_has_a_lie_annotation() {
        val c1 = Customer("Juma")
//        expect(c1.javaClass.isAnnotationPresent(Lie::class.java)).toBe(true)
        val lie = c1.javaClass.getAnnotation(Lie::class.java)
//        expect(lie.message).toBe("Just a lie")
        val serializable = c1.javaClass.getAnnotation(Serializable::class.java)
        val clazz = Customer::class.java
        println(clazz.declaredFields.map { it.name to it.type.simpleName })
        println(clazz.fields.map { it.name to it.type.simpleName })
    }
}