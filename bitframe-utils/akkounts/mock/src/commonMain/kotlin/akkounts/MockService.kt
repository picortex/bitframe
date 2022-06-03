package akkounts

import akkounts.provider.AccountingPackager

class MockService {
    fun offeredTo(consumer: MockConsumer): AccountingPackager = MockPackager(consumer)
}