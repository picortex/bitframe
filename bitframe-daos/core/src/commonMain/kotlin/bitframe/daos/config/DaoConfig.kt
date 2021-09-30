package bitframe.daos.config

import kotlinx.coroutines.CoroutineScope

interface DaoConfig {
    val scope: CoroutineScope
}