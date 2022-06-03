package versioning

class Version(val raw: String) {
    val safe get() = raw.replace(".", "_")
    override fun toString() = raw
}