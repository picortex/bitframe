# PiMonitor React SDK

This is a full software development kit for building PiMonitor Applications with react. It Extends the [Bitframe SDK](../../../bitframe/sdk/ReadMe.md) and intrinsically uses
the [PiMonitor API](../api/ReadMe.md)
to communicate with the PiMonitor Server

## Getting Started

- #### Step 1: Installation

  Run `npm install @picortex/pimonitor-client-full-sdk`

- #### Step 2: Configuration
  To use the sdk, one has to configure the sdk. We have a sample for the simplest configurations possible, to a more granular one

  ##### Simple Configuration

  Create a file `lib/sdk.js`

    ```typescript
    import SDK from "@picortex/pimonitor-client-full-sdk"
    
    const config = {
        appId: "<app-id>",
        url: "https://dev.picortex.com",
        serviceLoggers: undefined,
        viewModel: undefined
    }
    
    export const scope = SDK.scope(config)
    ```

  The exported scope is of type [PiMonitorReactScope](#pimonitorreactscope) which in itself, and it is what you'll need to easily develop a PiMonitor Applications with react.

  ##### Granular Configurations

  If you need more control over how the sdk behaves, here is a snippet of all configurable properties

  ```typescript
  const config = {
      appId: "<app-id>",
      url: "https://dev.picortex.com",
      serviceLoggers: { // configure logging of the underlying api
        console: true,   // [true|false] - enables/disables logs from the api to the console
        sentry: false // [true|false] - enables/disables logs from the api to sentry  
      },
      viewModel: {
        recoveryTime: 3000, // Time (in milliseconds) a viewmodel should take to recover itself from an error state
        transitionTime: 3000, //  Time (in milliseconds) a viewmodel should take to transtion from a success state to visible data
        logging: {
          console: true,   // [true|false] - enables/disables logs from the api to the console
          sentry: false // [true|false] - enables/disables logs from the api to sentry  
        }
      }
  }
  ```

  This configuration block is not final and can be refined/updated. So you can suggest a better syntax if you happen to have one

### [PiMonitorReactScope](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt)

The [PiMonitorReactScope](../../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt) basically exposes the underlying micro scopes of the entire PiMonitor
application, that are already wrapped and ready to be used in react.

This guid will assume that you already have created the file `lib/sdk.js` and because you have exported `scope` successfully from said file, one can easily import the scope object anywhere in the
application like so

```typescript
import { scope } from "libs/sdk"
```

Now, lets go over how you can use this scope to cater for you needs by covering all the provided UIScopes below.

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
