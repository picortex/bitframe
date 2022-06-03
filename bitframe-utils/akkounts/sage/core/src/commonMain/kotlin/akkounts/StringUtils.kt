package akkounts

fun String.containsAny(strings: Collection<String>, ignoreCase: Boolean = true): Boolean {
    var contains = false
    for (s in strings) {
        if (contains(s, ignoreCase)) {
            contains = true
            break
        }
    }
    return contains
}