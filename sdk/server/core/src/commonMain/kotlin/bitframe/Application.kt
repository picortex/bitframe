package bitframe

open class Application<S>(
    open val config: ApplicationConfig<S>
) {
    open val modules: List<Module> get() = config.modules

    open suspend fun onStart(service: S) {}

    open suspend fun onExit() {}
}