# 0.0.61

- Updated from later from 0.0.60 to 0.0.62
- [[BB42]](https://github.com/picortex/b2b/issues/42) Updated Sage Parsing Errors

# 0.0.60 : 2021-07-22

- Added Invoices and Bills to the new Unified API

# 0.0.50 : 2021-07-05

- Unified the AccountingPackage API
- Made sure Sage is compliant to the new API

# 0.0.40 : 2021-06-18

- Added quickbooks support
- Breaking Changes even to sage

# 0.0.30 : 2021-04-23

## Build System

- Updated gradle from 6.8.1 to 7.0
- Reverted gradle back to 6.8.1 since JavaTests couldn't run
- Updated builders from 1.3.0 to 1.3.24

## Core

### New Stable Features

- Added `Policy` to create a query policy
- Added `QueryRegulator` to facilitate the policy

### New Experimental Features

- Added `QueryCountStore` to facilitate automatic saving and retrieval of query counts

## Sage

### Deprecations

- Deprecated `Sage` in favor of `SageService`

### New Features

- Added `SageService`

### New Experimental Features

- Added experimental support of `SageServiceWithStorage`

## Work in progress

### Artifacts

- Quickbooks

# 0.0.20

- Updated test to 1.1.21
- Updated expect to 0.0.20
- Updated mapper to 0.0.60

# 0.0.10

## Availability

- Published to github registry

## Build Src

- Updated to gradle version 6.8.1

## Documentation

- Update readme
