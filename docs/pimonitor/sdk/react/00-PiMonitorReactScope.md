# [PiMonitorReactScope](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt)

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

- #### SignInReactScope
  This scope can be obtained by calling
  ```typescript
  const signInScope = scope.signIn
  ```
  Since it is the exact same [SignInReactScope](../../../bitframe/sdk/react/SignInReactScope.md) provided by the [Bitframe React SDK](../../../bitframe/sdk/ReadMe.md), I suggest you get your
  acquainted by it [here](../../../bitframe/sdk/react/SignInReactScope.md)

- #### SignUpScope
  TODO: To be documented

- ### BusinessesScope
  Coming soon