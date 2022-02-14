package bitframe.client

import LoggingConfiguration

actual interface SDKConfiguration {
    actual var appId: String
    actual var url: String?
    actual var serviceLoggers: LoggingConfiguration?
    actual var viewModel: ViewModelConfiguration?
}