package mailer

class Template(val content: String, val compiler: TemplateCompiler) {
    fun compile(): String = compiler.compile(content)
}