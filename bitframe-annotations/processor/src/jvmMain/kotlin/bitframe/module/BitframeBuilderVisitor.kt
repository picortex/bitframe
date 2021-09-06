package bitframe.module

import bitframe.utils.appendText
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class BitframeBuilderVisitor(
    private val codeGenerator: CodeGenerator
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        classDeclaration.primaryConstructor?.accept(this, data)
    }

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        val parentClass = function.parentDeclaration as KSClassDeclaration
        val packageName = parentClass.containingFile!!.packageName.asString()
        val newClassName = "${parentClass.simpleName.asString()}Builder"
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
                class $newClassName {
                    
                }
            """.trimIndent()
        )

        file.close()
    }
}