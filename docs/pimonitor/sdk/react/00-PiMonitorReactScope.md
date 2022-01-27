# [PiMonitorReactScope](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt)

## Prerequisites

For you to be able to follow through, this guide will operate under the following assumptions.

1. You have already created the file `lib/sdk.js`, exported the scope object as instructed in the [get started](./ReadMe.md) section, and because of that, one can easily import the scope object
   anywhere in the application like so

    ```typescript
    import { scope } from "libs/sdk"
    ```
1. You have read about the [BitframeReactScope](../../../bitframe/sdk/react/00-BitframeReactScope.md) and can use the [sign in](../../../bitframe/sdk/react/01-SignInReactScope.md) and [panel] scopes
   as well

Now, lets go over how you can use this scope to cater for your needs by covering all the provided UIScopes as they can be
seen [here](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt):-

## UI Scopes

- ### [Sign In](../../../bitframe/sdk/react/01-SignInReactScope.md)
  This scope can be obtained by calling `scope.signIn` which returns a [SignInReactScope](../../../bitframe/sdk/react/01-SignInReactScope.md) provided by
  the [Bitframe React SDK](../../../bitframe/sdk/ReadMe.md), I suggest you get your acquainted by the sign in react scope [here](../../../bitframe/sdk/react/01-SignInReactScope.md)

- ### Sign Up
  TODO: To be documented

- ### Businesses
  TODO: To be documented

- ### [Portfolio](./04-PortfolioReactScope.md)
  Obtained by calling `scope.portfolio`, which then returns a [PortfolioReactScope](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/portfolio/PortfolioReactScope.kt).
  Click [here](./04-PortfolioReactScope.md) to read more about this scope 
  