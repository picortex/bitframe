package bitframe.testing.annotations

actual enum class Lifecycle {
    /**
     * When using this mode, a new test instance will be created once per test class.
     *
     * @see .PER_METHOD
     */
    PER_CLASS,

    /**
     * When using this mode, a new test instance will be created for each test method,
     * test factory method, or test template method.
     *
     *
     * This mode is analogous to the behavior found in JUnit versions 1 through 4.
     *
     * @see .PER_CLASS
     */
    PER_METHOD

}