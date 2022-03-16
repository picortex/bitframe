//public class BSheetStorage implements BalanceSheetStorage {
//    private final Map<String, BalanceSheet> sheets = new HashMap<>();
//
//    @NotNull
//    @Override
//    public Later<BalanceSheet> load(@NotNull String ownerId, long endOf) {
//        return new Later<>((res, rej) -> {
//            res.apply(sheets.get(ownerId));
//        });
//    }
//
//    @NotNull
//    @Override
//    public Later<BalanceSheet> save(@NotNull Owner owner, @NotNull Vendor vendor, @NotNull BalanceSheet.Data data) {
//        return new Later<>((res, rej) -> {
//            var o = new Owner("uid", "SAGE");
//            var on = new LocalDate(2022, 6, 30);
//            var header = new BalanceSheet.Header(SageOneZAService.VENDOR, akkounts.provider.Owner.UNSET, Currency.TZS, on);
//            BalanceSheet sheet = new BalanceSheet(sheets.size() + "", header, data);
//            sheets.put(owner.getUid(), sheet);
//            res.apply(sheet);
//        });
//    }
//
//    @NotNull
//    @Override
//    public Later<BalanceSheet> load(@NotNull String ownerId, @NotNull LocalDate endOf) {
//        return new Later<>((res, rej) -> {
//            res.apply(sheets.get(ownerId));
//        });
//    }
//}
