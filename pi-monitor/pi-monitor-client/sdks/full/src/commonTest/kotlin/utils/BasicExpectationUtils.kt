package utils

import expect.BasicExpectation
import kotlin.test.assertTrue

fun BasicExpectation<String?>.toContain(msg: String?) = assertTrue(
    """
        
    Expected    : $value
    To Contain  : $msg
    But was not contained
    =================================
    
    """.trimIndent()
) {
    msg != null && value?.indexOf(msg) != -1
}