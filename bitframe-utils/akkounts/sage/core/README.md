# Akkounts Sage

This is the integration port of [Sage](https://resellers.accounting.sageone.co.za/Landing/Default.aspx) version 2.0.0

## Setup: Gradle

```
dependencies {
    implementation("com.picortex:akkounts-sage:0.0.10")
}
```

## Getting Started

To get started, make sure you are registered with Sage, and obtain the information

```java
class GettingStarted {
    static void main() {
        String username = "sageemail@test.com";
        String password = "<secure-sage-account-password>";
        String apiKey = "{SAGE-API-KEY-HERE}";
        String companyId = "<CompanyId>";

        Sage sage = new Sage(username, password, apiKey, companyId);

        BalanceSheet bs = sage.incomeStatement(/*from*/"2021-02-20", /*to*/"2021-02-25");
    }
}
```