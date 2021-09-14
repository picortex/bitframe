# PiMonitor Full Client SDK

An SDK Library that one would use to access the PiMonitor server for different resources

## Setup

1. Add a `.npmrc` file to your project
1. type `npm install @picortex/pi-monitor-client-full`

## Samples

```javascript
import SDK from '@picortex/pi-monitor-client-core"

const config = {
    appId: "<app-id>",
    simulationTime: 3000,
    disableViewModelLogs: false
}

const service = SDK.service(config)

const { viewModel, submit } = SDK.signIn(service);

export function SignInComponent(props: any) {
  const state = useViewModelState(viewmodel)
  if (state instanceof State.Form) {
    const data = { email, password };
    return <FormView onSubmit={({username,password})->submit({username,password})}>
  } else if (state instanceof State.Loading) {
    return LoadingView(state.message);
  } else if (state instanceof State.Success) {
    return SuccessView(state.message);
  } else {
    return ErrorView("Bad error");
  }
}
```