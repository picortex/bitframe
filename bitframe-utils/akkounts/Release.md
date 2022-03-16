# Akkounts Version 0.0.30

## Introduction

Since we have been faced with new challenges of regulating our API requests we had to find a way to regulate our API
from our side. This called for a change in approach for API designs.

Akkounts 0.0.30 has introduced new ways to configure and yet easily use this approach of ours

## Getting Started

### QueryRegulator

These queries are will be regulated by a class called a `QueryRegulator`.

1. Assuming you have added the Sage dependencies as instructed on the readme, before ay instantiation we need to
   implement a `QueryCountStore`. A `QueryCountStore` should be able to save and load a `QueryCount` which will be used
   by the `SageService` to manage the `QueryCounts` and enforce regulations as dictated by

```java
public class QueryCountStoreImpl implements QueryCountStore {
    public QueryCountStoreImpl(/*dependencies here*/) {

    }

    @NotNull
    @Override
    public Later<QueryCount> save(@NotNull QueryCount count) {
        return new Later<>((resolve, reject) -> {
            // save the count here and return it
            resolve.apply(count);
        });
    }

    @NotNull
    @Override
    public Later<QueryCount> load(@NotNull String requesterId) {
        return new Later<>((resolve, reject) -> {
            QueryCount qc = retrieveQueryCount(); // implement this
            resolve.apply(qc);
        });
    }
}
```

2. Create a `Policy` that will be enforced by a query regulator. For example, if we need to be able to allow a maximum
   of 2 requests per day, you can do this

```java
class Demo {
    Policy policy = new Policy(2, new Period(1, TimeUnit.DAYS));
    // . . .
}
```

3. Now you are ready to instantiate a `QueryRegulator`

```java
class Demo {
    // . . .
    QueryCountStore store = new QueryCountStoreImpl();
    QueryRegulator regulator = new QueryRegulator(store, policy);
}
```

## Getting Started With Sage

Sage's main entry point is `SageService`, in favor of Sage and can be shown bellow

```java
public class SageServiceJavaTest {
    private final String apiKey = "{391BC618-64AF-472B-B8C6-76A4EDE2A4A1}";
    private final String companyId = "13956";
    private final OwnerData owner = new OwnerData("<customer-id>", "<customer-name>");
    private final Credentials credentials = new Credentials(
            "support@picortex.com",
            "daub4foc!BIRN.teab",
            apiKey,
            companyId
    );
    private final Policy policy = new Policy(2, new Period(2, TimeUnit.MINUTES));
    private final QueryCountStore store = new StubStore();
    private final QueryRegulator regulator = new QueryRegulator(store, policy);
    private final SageService service = new SageService(
            Environment.TEST, owner, credentials, regulator
    );

    @Test
    @Ignore
    public void should_successfully_get_a_valid_balance_sheet() {
        service.balanceSheet(new LocalDate(2021, 3, 31)).then(sheet -> {
            System.out.println(sheet);
            return null;
        }, err -> {
            System.out.println("Error here");
            System.out.println(err.getMessage());
            return null;
        }).wait();
    }

    @Test
    @Ignore
    public void should_successfuly_get_a_valid_income_statement() {
        LocalDate from = new LocalDate(2021, 1, 1);
        LocalDate to = new LocalDate(2021, 1, 31);
        service.incomeStatement(from, to).then(statement -> {
            System.out.println(statement);
            return null;
        }, err -> {
            System.out.println(err.getMessage());
            return null;
        }).wait();
    }
}
```