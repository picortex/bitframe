package akkounts.sage

import akkounts.MockConsumer
import akkounts.MockPackager
import akkounts.provider.AccountingPackager
import akkounts.regulation.QueryRegulator

class SageOneZAServiceMock : SageOneZAService {
    override fun offeredTo(
        company: SageOneZAUserCompany,
        regulator: QueryRegulator
    ): AccountingPackager = MockPackager(MockConsumer(company.uid, company.name))

    override fun toString(): String = "SageOneZAServiceMock"
}