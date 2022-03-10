package akkounts

import akkounts.provider.AccountingPackager
import akkounts.regulation.QueryRegulator
import kotlin.jvm.JvmOverloads

class AccountingPackage @JvmOverloads constructor(
    val packager: AccountingPackager,
    val regulator: QueryRegulator = QueryRegulator.FORGIVING
) : AccountingPackager by packager