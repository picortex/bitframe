package bitframe.panel

import kotlinx.collections.interoperable.listOf

val sections = listOf(
    UIModuleGroup(
        "Evaluation", listOf(
            UIModule("Business", "")
        )
    ),
    UIModuleGroup(
        "Administrator", listOf(
            UIModule("Users", "")
        )
    ),
    UIModuleGroup(
        "Personal", listOf(
            UIModule("Setting", ""),
            UIModule("My Profile", ""),
            UIModule("Test Module", ""),
            UIModule("George Module", "")
        )
    )
)