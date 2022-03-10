package akkounts.sage

enum class Environment(val path: String) {
    PROD("https://accounting.sageone.co.za/api/2.00"),
    TEST("https://resellers.accounting.sageone.co.za/api/2.0.0")
}