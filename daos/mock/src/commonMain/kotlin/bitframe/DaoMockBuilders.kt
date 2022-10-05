package bitframe

import bitframe.actor.Savable

inline fun <reified D : Savable> DaoMock(
    config: DaoMockConfig<D>? = null
): DaoMock<D> = DaoMock(config ?: DaoMockConfig(D::class))