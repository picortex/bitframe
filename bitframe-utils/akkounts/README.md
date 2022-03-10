# Akkounts

![badge][badge-mpp] ![badge][badge-android] ![badge][badge-js] ![badge][badge-jvm]

A universal multiplatform accounting system

# Artifacts

## akkount-core

Provide generic definitions of accounting components i.e. Ledgers, Transactions and Accounts

### Setup: gradle

```
dependencies {
    implementation("com.picortex:akkounts-core:0.0.60")
}
```

## [akkounts-reports](akkounts-reports)

Provides a generic definition of what the accounting reports should be structured and across all vendors

### Setup: gradle

```
dependencies {
    implementation("com.picortex:akkounts-reports:0.0.60")
}
```

## [akkounts-sage](sage)

Sage version 2.0.0 specific wrappers that are integrated with `akkounts-reports`

### Setup: gradle

```
dependencies {
    implementation("com.picortex:akkounts-sage:0.0.60")
}
```

## [akkounts-quickbooks](quickbooks)

Quickbooks version 3 wrappers for akkounts

### Setup: gradle

```
dependencies {
    implementation("com.picortex:akkounts-quickbooks:0.0.60")
}
```

[badge-mpp]: https://img.shields.io/badge/kotlin-multiplatform-blue?style=flat

[badge-android]: http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat

[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat

[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat
