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

### [PiMonitorReactScope](./00-PiMonitorReactScope.md)

The [PiMonitorReactScope](./00-PiMonitorReactScope.md) exposes the underlying micro ui scopes of the entire PiMonitor application, that are already wrapped and ready to be used in react.
Click [here](./00-PiMonitorReactScope.md) to learn more how to fully utilize it