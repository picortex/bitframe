import akkounts.AccountingPackage;
import akkounts.sage.SageOneZAService;
import akkounts.sage.SageOneZAUserCompany;
import kotlinx.datetime.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static expect.Builders.expect;

@Disabled
public class AccountingPackageSageAPIJavaTest {
    SageOneZAService sageService = SageOneZAService.create(Credentials.API_KEY);
    SageOneZAUserCompany company = Credentials.PRODUCTION_ACCOUNT;
    AccountingPackage ap = new AccountingPackage(sageService.offeredTo(company));

    @Test
    public void should_fetch_balance_sheet_reports_well() {
        var date = new LocalDate(2021, 7, 5);
        var sheet = ap.getReports().balanceSheet(date).wait();
        System.out.println(sheet);
        expect(sheet).toBeNonNull();
    }

    @Test
    public void should_fetch_income_statement_report() {
        var start = new LocalDate(2021, 7, 1);
        var end = new LocalDate(2021, 7, 5);
        var statement = ap.getReports().incomeStatement(start, end).wait();
        System.out.println(statement);
        expect(statement).toBeNonNull();
    }

    @Test
    public void should_fetch_cash_flow_statement_report() {
        var err = expect(() -> {
            var start = new LocalDate(2021, 1, 1);
            var end = new LocalDate(2021, 7, 1);
            var statement = ap.getReports().cashFlow(start, end).wait();
            System.out.println(statement);
            expect(statement).toBeNonNull();
        }).toFail();
        expect(err.getMessage()).toBeNonNull();
    }
}
