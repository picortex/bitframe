package bitframe.server

import bitframe.core.DaoFactory

@Deprecated("In favour of bitframe.MongoDaoFactoryConfig")
expect class MongoDaoFactory(config: MongoDaoFactoryConfig) : DaoFactory