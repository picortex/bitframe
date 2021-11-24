package validation

fun test() {
    val v: Validation<Int> = validate { 1 }
    val v1 = v.getOrDefault(1)
}