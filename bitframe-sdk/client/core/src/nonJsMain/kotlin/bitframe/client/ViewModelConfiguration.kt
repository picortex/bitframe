package bitframe.client

import LoggingConfiguration

actual interface ViewModelConfiguration {
    actual var logging: LoggingConfiguration?
    actual var recoveryTime: Int?
    actual var transitionTime: Int?
}