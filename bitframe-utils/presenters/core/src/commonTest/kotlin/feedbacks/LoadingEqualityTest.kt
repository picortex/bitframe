package feedbacks

import expect.expect
import presenters.feedbacks.Feedback
import kotlin.test.Test

class LoadingEqualityTest {
    @Test
    fun two_instances_with_equal_message_should_be_equal() {
        expect(Feedback.Loading("Loading")).toBe(Feedback.Loading("Loading"))
    }
}