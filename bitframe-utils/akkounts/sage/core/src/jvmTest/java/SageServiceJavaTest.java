public class SageServiceJavaTest {
//    private final String apiKey = "{C7542EBF-4657-484C-B79E-E3D90DB0F0D1}";
//    //    private final String apiKey = "{391BC618-64AF-472B-B8C6-76A4EDE2A4A1}";
//    private final String companyId = "13956";
//    private final Owner owner = new Owner("<customer-id>", "<customer-name>",);
//    private final Credentials credentials = new Credentials(
//            "support@picortex.com",
//            "daub4foc!BIRN.teab",
//            apiKey,
//            companyId
//    );
//    private final Policy policy = new Policy(2, new Period(2, TimeUnit.MINUTES));
//    private final QueryCountStore store = new StubStore();
//    private final QueryRegulator regulator = new QueryRegulator(store, policy);
//    private final SageOneZAReportsService service = new SageOneZAReportsService(
//            owner, credentials, regulator
//    );
//
//    @Test
//    public void should_successfully_get_a_valid_balance_sheet() {
//        service.balanceSheet(new LocalDate(2021, 3, 31)).then(sheet -> {
//            System.out.println(sheet);
//            return null;
//        }, err -> {
//            System.out.println("Error here");
//            System.out.println(err.getMessage());
//            return null;
//        }).wait();
//    }
//
//    @Test
//    public void should_successfuly_get_a_valid_income_statement() {
//        LocalDate from = new LocalDate(2021, 1, 1);
//        LocalDate to = new LocalDate(2021, 1, 31);
//        service.incomeStatement(from, to).then(statement -> {
//            System.out.println(statement);
//            return null;
//        }, err -> {
//            System.out.println(err.getMessage());
//            return null;
//        }).wait();
//    }
}
