package intergration;

import akkounts.AccountingPackage;
import akkounts.QuickBooksCompany;
import akkounts.quickbooks.QuickBooksPackager;
import akkounts.quickbooks.QuickBooksService;
import kotlinx.datetime.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static expect.ExpectBuilders.*;
import static intergration.auth.CredentialsKt.*;

//public class ReportsJavaTest {
//    TokenStorage storage = new TokenStorage();
//    QuickBooksService qbs = new QuickBooksService(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, storage, QuickBooksService.Environment.TEST);
//    /*
//    QuickBooksCompany qbc = new QuickBooksCompany(
//            "<test-company-au-id>",
//            "4620816365164575210",
//            "AB116340737787zB8XT8mby0sXtJMFU2Dtq2dX1XK76gpl1J7V",
//            "<access-token>"
//    );
//     */
//    QuickBooksCompany qbc = COMPANY_AU;
//
//    AccountingPackage ap = new AccountingPackage(new QuickBooksPackager(qbs, qbc)); // or qbs.packageFor(qbc);
//
//    @Test
//    public void should_fetch_balance_sheet_reports_well() {
//        var date = new LocalDate(2021, 7, 1);
//        var sheet = ap.getReports().balanceSheet(date).wait();
//        System.out.println(sheet);
//        expect(sheet).toBeNonNull();
//    }
//
//    @Test
//    public void should_fetch_income_statement_report() {
//        var start = new LocalDate(2021, 1, 1);
//        var end = new LocalDate(2021, 7, 1);
//        var statement = ap.getReports().incomeStatement(start, end).wait();
//        System.out.println(statement);
//        expect(statement).toBeNonNull();
//    }
//
//    @Test
//    public void should_fetch_cash_flow_statement_report() {
//        var start = new LocalDate(2021, 1, 1);
//        var end = new LocalDate(2021, 7, 1);
//        var statement = ap.getReports().cashFlow(start, end).wait();
//        System.out.println(statement);
//        expect(statement).toBeNonNull();
//    }
//}
