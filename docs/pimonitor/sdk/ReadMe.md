# PiMonitor SDK

This is a full software development kit for building PiMonitor Applications. It Extends the [Bitframe SDK](../../bitframe/sdk/ReadeMe.md) and intrinsically uses the [PiMonitor API](../api/ReadMe.md)
to communicate with the PiMonitor Server

## Getting Started [Typescript/Javascript]

- #### Step 1: Installation

  Run `npm install @picortex/pimonitor-client-full-sdk`

- #### Step 2

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

the exported scope is of type [PiMonitorReactScope](../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt)
and it is what you'll need while developing a PiMonitor Application.

## Granular Configurations

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

### [PiMonitorReactScope](../../../pi-monitor/pi-monitor-sdk/client/react/src/main/kotlin/pimonitor/PiMonitorReactScope.kt)

The PiMonitorReactScope is basically a wrapper of different [UIScope](../../../bitframe-sdk/client/core/src/commonMain/kotlin/bitframe/client/UIScope.kt)s which are meant to be used in a react
applications. These scopes (that are specifically targeting react) also happen to implement [ReactUIScope](../../../bitframe-sdk/client/react/src/main/kotlin/bitframe/client/ReactUIScope.kt) to make
it easy to use from react.

Bellow is a list of scopes available the PiMonitorReactScope

- #### SignInScope

PiMonitor's uses Bitframe's SignInScope. Click [here](../../bitframe/sdk/SignInScope.md) to learn more about Bitframe's SignInScope
