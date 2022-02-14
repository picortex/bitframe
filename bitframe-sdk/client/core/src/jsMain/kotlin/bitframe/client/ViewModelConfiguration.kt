package bitframe.client

import LoggingConfiguration

actual external interface ViewModelConfiguration {
    actual var logging: LoggingConfiguration?
    actual var recoveryTime: Int?
    actual var transitionTime: Int?
}