package bitframe

interface ConfigLoader<C : ServerConfiguration> {
    fun load(): C
}