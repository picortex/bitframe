# PiMonitor Client SDK

An SDK Library that one would use to access the PiMonitor server for different resources

## Setup
1. Add a `.npmrc` file to your project
1. type `npm install @picortex/pi-monitor-client-sdk`

## Samples

```javascript
import SDK from '@picortex/pi-monitor-client-sdk"

const service = SDK.service({appId: "<your-app-id>"});

const viewModel = SDK.loginViewModel(service);
```


