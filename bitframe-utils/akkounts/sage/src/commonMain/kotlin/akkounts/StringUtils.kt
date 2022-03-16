package akkounts

fun String.containsAny(strings: Collection<String>): Boolean {
    var contains = false
    for (s in strings) {
        if (contains(s, ignoreCase = true)) {
            contains = true
            break
        }
    }
    return contains
}