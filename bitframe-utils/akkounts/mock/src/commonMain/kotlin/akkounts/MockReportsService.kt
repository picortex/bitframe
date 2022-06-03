package akkounts

import akkounts.provider.Owner
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.services.ReportsService
import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.FinancialReportHeader
import akkounts.utils.unset
import datetime.toDate
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import later.Later

class MockReportsService(
    val consumer: MockConsumer
) : ReportsService {

    fun MockConsumer.toOwner() = Owner(
        uid = unset,
        name = name,
        foreignName = name,
        foreignID = uid
    )

    val balanceSheetGenerator = BalanceSheetGenerator
    val incomeStatementGenerator = IncomeStatementGenerator

    override fun balanceSheet(at: LocalDate): Later<BalanceSheet> = Later.resolve(
        BalanceSheet(
            uid = unset,
            header = FinancialReportHeader.Snapshot(
                vendor = MockPackager.VENDOR,
                owner = consumer.toOwner(),
                currency = consumer.currency,
                endOf = at.toDate()
            ),
            body = balanceSheetGenerator.balanceSheetBody(
                currency = consumer.currency,
                randomizer = consumer.hashCode() + at.year + at.dayOfMonth + at.dayOfYear
            )
        )
    )

    override fun incomeStatement(start: LocalDate, end: LocalDate): Later<IncomeStatement> = Later.resolve(
        IncomeStatement(
            uid = unset,
            header = FinancialReportHeader.Durational(
                vendor = MockPackager.VENDOR,
                owner = consumer.toOwner(),
                currency = consumer.currency,
                start = start.toDate(),
                end = end.toDate()
            ),
            body = incomeStatementGenerator.incomeStatementBody(
                currency = consumer.currency,
                randomizer = consumer.hashCode() + start.year + start.dayOfMonth + start.dayOfYear + (end - start).days
            )
        )
    )

    override fun cashFlow(start: LocalDate, end: LocalDate): Later<CashFlow> {
        TODO("Not yet implemented")
    }
}