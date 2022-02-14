package bitframe.client

import LoggingConfiguration

expect interface ViewModelConfiguration {
    var logging: LoggingConfiguration?
    var recoveryTime: Int?
    var transitionTime: Int?
}