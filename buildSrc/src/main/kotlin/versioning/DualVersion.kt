package versioning

class DualVersion(
    val name: String,
    val current: Version,
    val previous: Version
) {
    constructor(
        name: String,
        current: String,
        previous: String
    ) : this(name, Version(current), Version(previous))
}