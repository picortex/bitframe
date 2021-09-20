# PiMonitor Core Client SDK

An SDK Library that one would use to access the PiMonitor server for different resources

## Setup

1. type `npm install @picortex/pi-monitor-client-core`

## Samples

```javascript
import SDK from '@picortex/pi-monitor-client-core"

const config = {
    appId: "<app-id>",
    simulationTime: 3000,
    disableViewModelLogs: false
}

const service = SDK.service(config)
```