package intergration;

import akkounts.AccountingPackage;
import akkounts.quickbooks.QuickBooksService;
import kotlinx.datetime.LocalDate;
import org.junit.jupiter.api.Test;

import static expect.Builders.expect;
import static intergration.auth.CredentialsKt.*;

public class AccountingPackageQuickBooksAPITest {
    QuickBooksService qbService = new QuickBooksService(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, new TokenStorage(), QuickBooksService.Environment.TEST);
    AccountingPackage ap = new AccountingPackage(qbService.offeredTo(COMPANY_AU));

    @Test
    public void should_get_reports() {
        var sheet = ap.getReports().balanceSheet(new LocalDate(2021, 7, 3)).wait();
    }

    @Test
    public void should_fetch_balance_sheet_reports_well() {
        var date = new LocalDate(2021, 7, 5);
        var sheet = ap.getReports().balanceSheet(date).wait();
        System.out.println(sheet);
        expect(sheet).toBeNonNull();
    }

    @Test
    public void should_fetch_income_statement_report() {
        var start = new LocalDate(2020, 7, 1);
        var end = new LocalDate(2021, 7, 5);
        var statement = ap.getReports().incomeStatement(start, end).wait();
        System.out.println(statement);
        expect(statement).toBeNonNull();
    }

    @Test
    public void should_fetch_cash_flow_statement_report() {
        var start = new LocalDate(2021, 1, 1);
        var end = new LocalDate(2021, 7, 5);
        var statement = ap.getReports().cashFlow(start, end).wait();
        System.out.println(statement);
        expect(statement).toBeNonNull();
    }
}
