package bitframe.module

import bitframe.annotations.Module
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

class ModuleProcessor(
    codeGenerator: CodeGenerator
) : SymbolProcessor {

    val paramVisitor = BitframeParamsVisitor(codeGenerator)
    val builderVisitor = BitframeBuilderVisitor(codeGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Module::class.qualifiedName!!)
        val ret = symbols.filter { !it.validate() }.toList()
        symbols.filter {
            it is KSClassDeclaration && it.validate()
        }.forEach {
            it.accept(paramVisitor, Unit)
            it.accept(builderVisitor, Unit)
        }
        return ret
    }
}