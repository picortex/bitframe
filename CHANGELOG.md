# 0.0.27

- Removing other targets to make deployment faster

# 0.0.26

## CI & CD

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