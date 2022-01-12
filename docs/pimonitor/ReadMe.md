# PiMonitor Documentation

PiMonitor tools have been split into two major parts

- [The API](./api/ReadMe.md) (previously called the sdk-core)
- [The SDK](./sdk/ReadMe.md) (previously called the sdk-full)

### [The API](./api/ReadMe.md)

The PiMonitor API is a library, and an architectural layer that handles the following

- Encoding user data to a format accepted by the server
- Decoding user data to a modal accepted by the application
- Authentication & Session handling
- Communicating with the server

It is not opinionated to any platform, framework or architecture which means it can be used anywhere and anyhow as the programmer needs.

Written in Kotlin Multiplatform, the API can be used in

- Kotlin [Android|Desktop|Server|Browser|Node|iOS]
- TypeScript [Node|Browser|JsCore]
- JavaScript [Node|Browser|JsCore]
- Swift [iOS|MacOs]
- Java [Android|Desktop|Server]
- Scala [Android|Desktop|Server]
- Groovy [Android|Desktop|Server]
- ObjectiveC [iOS|MacOs]

### [The SDK](./sdk/ReadMe.md)

While the API is a tool enough for itself, the SDK goes further to unify logic and some UI aspects. It is responsible for handling

- UI states
- UI transitions
- User Intents
- Error handling

Since all these are usually bundled in a specific scope, the PiMonitor SDK comes with a PiMonitorScope type that wraps all the available scopes supported by the SDK

To understand more on what a scope is, please read more about [UIScope](../bitframe/sdk/UIScope.md) here

## Bitframe Tools

These tools are all made on top of [Bitframe](../bitframe/ReadMe.md) tools

- [Bitframe API](../bitframe/api/ReadMe.md)
- [Bitframe SDK](../bitframe/sdk/ReadMe.md)