package bitframe.module

import bitframe.annotations.Generated
import bitframe.utils.appendText
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.*
import java.lang.StringBuilder

class BitframeParamsVisitor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        classDeclaration.primaryConstructor?.accept(this, data)
    }

    fun KSValueParameter.getFullType(): String {
        val typeName = StringBuilder(type.resolve().declaration.qualifiedName!!.asString())
        val typeArgs = type.element!!.typeArguments
        if (typeArgs.isNotEmpty()) {
            typeName.append("<")
            typeName.append(typeArgs.map {
                val type = it.type?.resolve()
                "${it.variance.label} ${type?.declaration?.qualifiedName?.asString() ?: "ERROR"}" +
                        if (type?.nullability == Nullability.NULLABLE) "?" else ""
            }.joinToString(","))
            typeName.append(">")
        }
        return typeName.toString()
    }

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        logger.info("Processing ${function.qualifiedName?.asString()}")
        val parentClass = function.parentDeclaration as KSClassDeclaration
        val ogClassName = parentClass.simpleName.asString()
        val packageName = parentClass.containingFile!!.packageName.asString()
        val newClassName = "Create${ogClassName}Params"
        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(true, function.containingFile!!),
            packageName = packageName,
            fileName = newClassName
        )
        file.appendText(
            """
                package $packageName
                
                import kotlinx.serialization.Serializable   
                  
                @Serializable    
                class $newClassName(
                
            """.trimIndent()
        )

        val (finals, initials) = function.parameters.partition { param ->
            param.annotations.map {
                it.annotationType.resolve().declaration.qualifiedName!!.asString()
            }.contains(Generated::class.qualifiedName)
        }

        initials.forEach { param: KSValueParameter ->
            val name = param.name!!.asString()
            val typeName = param.getFullType()
            file.appendText("    val $name: $typeName,\n")
        }

        file.appendText(") {\n")
        file.appendText("    fun to$ogClassName(\n")
        finals.forEach { param ->
            val name = param.name!!.asString()
            val typeName = param.getFullType()
            file.appendText("        $name: $typeName,\n")
        }
        file.appendText("    ) = $ogClassName(\n")
        function.parameters.forEach { param ->
            val name = param.name!!.asString()
            file.appendText("        $name = $name,\n")
        }
        file.appendText("    )\n")
        file.appendText("}")
        file.close()
        logger.info("Finished processing ${function.qualifiedName?.asString()}")
    }
}