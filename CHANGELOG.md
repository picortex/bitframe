# 0.0.30

## PiMonitor Client SDK Core

- [[PM121]](https://github.com/picortex/bitframe/issues/121) Persist the currently logged in Monitor
- [[PM142]](https://github.com/picortex/bitframe/issues/142) Refactor SignUpService to just use one method for signing up
- [[PM123]](https://github.com/picortex/bitframe/issues/123) Moved the sing in/up event into the event bus
- [[PM151]](https://github.com/picortex/bitframe/issues/151) Automatically sign in user after registration

## Bitframe

- [[BF143]](https://github.com/picortex/bitframe/issues/143) Response is not properly returning the cause of an error

## Bitframe Server

- [[BF144]](https://github.com/picortex/bitframe/issues/144) Fix Cross Origin Resource Sharing Issue

# 0.0.29

## PiMonitor Client SDK Core

- [[PM127]](https://github.com/picortex/bitframe/issues/127) Re authored the SignUpService to accommodate the new designs
- [[PM103]](https://github.com/picortex/bitframe/issues/103) Moved SignUpTest to commonMain

## PiMonitor Client Browser Web

- [[PM105]](https://github.com/picortex/bitframe/issues/105) Fix UI Crash when user goes to businesses tab

## Bitframe Authentication Service

### Bug Fixes

- [[BF098]](https://github.com/picortex/bitframe/issues/98) Fixed flakiness in authentication tests
- [[BF122]](https://github.com/picortex/bitframe/issues/122) Can now get the currently signed in User, Space and App

### Enhancements

- [[BF94]](https://github.com/picortex/bitframe/issues/94) Lifted authentication ktor service away from bitframe client

## Bitframe Client Sdk

### Bug Fixes

- [[BF92]](https://github.com/picortex/bitframe/issues/92) Fixed sign in validation bug for empty sign in credentials
- [[BF93]](https://github.com/picortex/bitframe/issues/93) Attach Universal testing to cover future bugs

## Bitframe Presenters

- [[BF133]](https://github.com/picortex/bitframe/issues/133) Made the FormFeedback States easily reusable. Doesn't have to be consumed directly from the SDK

## Bitframe Test Containers

- [[BF96]](https://github.com/picortex/bitframe/issues/96) Make PiMonitor bitframe containers multiplatform

## Bitframe Service

- [[BF125]](https://github.com/picortex/bitframe/issues/125) Restructured ServiceConfig and its subclasses

## CI & CD

- [[BF100]](https://github.com/picortex/bitframe/issues/100) Cache gradle for faster CI builds

## Libs/Tools Dependency Changes

| Lib/Tool | Prev version | Current version|
|----------|--------------|----------------|
| Kotlin   | 1.5.30       | 1.5.31         |
| KSP      | 1.5.30-1.0.0 | 1.5.31-1.0.0   |
| asoft foundation | 1.4.0| 1.4.11         |

# 0.0.28

## CI & CD

- Removing other targets to make deployment faster
- Updated workflows for better naming
- Setup integration testing to run directly on containers (this pulls our testing environment closer to a production environment)
- Setup acceptance testing to run directly on docker our docker containers
- [[BF80]](https://github.com/picortex/bitframe/issues/80) Published all meaning artifacts to be used

## Bitframe

### Bitframe Authentication

- [[BF71]](https://github.com/picortex/bitframe/issues/71) Pulled out of bitframe core effectively making bitframe much thinner

### Bitframe Nomenclature

- Renamed renderers into pages and modules

### Bitframe Dao Core

- Moved the core dao tools out of bitframe core

### Bitframe Core

- Added a standard bitframe response to all our responses including failures and errors

### Bitframe events

- [x] [[BF82]](https://github.com/picortex/bitframe/issues/82) Created a working event bus
- [ ] Find out how to propagate errors (i.e., casting errors, serialization errors, e.t.c) down

## Pimonitor

### Pimonitor client full

- [[BF79]](https://github.com/picortex/bitframe/issues/79) Matched content according to the designs

## Cleaned up

- Removed the users module
- Removed access module

# 0.0.25 : 2021.09.20

## PiMonitor

### Client core sdk

- [x] Updated sample section in readme to ease readability
- [x] Multi signing paths for individuals and organisations

### Client full sdk

- [x] Updated sample section in readme to ease readability

### Client mobile & web

- [x] Introduction of renderers

## Bitframe

### Bitframe Presenters

- [x] Introduction of presenters

# 0.0.24 : 2021.09.13

## Bitframe

### Servers

- [ ] Provide a common UI Module abstraction for dashboard modules
- [ ] Provide a common UI Module abstraction for regular crud modules
- [ ] Unify aggregate modules for data

### Client Core SDK

- [x] Updated readme to reflect on full client configuration
- [ ] Add samples to show how the core SDK can be used

### Client Full SDK

- [x] Introduced UI Scopes which resulted in a much more idiomatic syntax
- [ ] Provide a clear documentation on how to use these scopes
- [ ] Add samples on the common UIScopes

## PiMonitor

### Client SDKs

- [x] Published the new version on 0.0.24
- [x] Fixed the hook bug which was caused by bundling

### Acceptance Testing DSL

- [x] Settled on a much cleaner acceptance DLS
- [x] Tested the sample client with it 