package bitframe.testing

actual open class ContainerTest {
    actual companion object {
        actual val mode: TestMode = TestMode.DEV
        actual val urlUnderTest: String
            get() = error("Accessing url in js integration is currently not supported yet")
    }
}