//public class IStatementStorage implements IncomeStatementStorage {
//    private final Map<String, IncomeStatement> sheets = new HashMap<>();
//
//    @NotNull
//    @Override
//    public Later<IncomeStatement> save(@NotNull Owner owner, @NotNull Vendor vendor, @NotNull IncomeStatement.Data data) {
//        return new Later<>((res, rej) -> {
//            var o = new Owner("uid", "SAGE");
//            var start = new LocalDate(2021, 6, 1);
//            var end = new LocalDate(2021, 6, 30);
//            var header = new IncomeStatement.Header(SageOneZAService.VENDOR, Currency.TZS, akkounts.provider.Owner.UNSET, start, end);
//            IncomeStatement sheet = new IncomeStatement(sheets.size() + "", header, data);
//            sheets.put(owner.getUid(), sheet);
//            res.apply(sheet);
//        });
//    }
//
//    @NotNull
//    @Override
//    public Later<IncomeStatement> load(@NotNull String ownerId, @NotNull LocalDate start, @NotNull LocalDate end) {
//        return new Later<>((res, rej) -> {
//            res.apply(sheets.get(ownerId));
//        });
//    }
//
//    @NotNull
//    @Override
//    public Later<IncomeStatement> load(@NotNull String ownerId, long endOf) {
//        return new Later<>((res, rej) -> {
//            res.apply(sheets.get(ownerId));
//        });
//    }
//}
