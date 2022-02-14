package bitframe.client

import LoggingConfiguration

expect interface SDKConfiguration {
    var appId: String
    var url: String?
    var serviceLoggers: LoggingConfiguration?
    var viewModel: ViewModelConfiguration?
}