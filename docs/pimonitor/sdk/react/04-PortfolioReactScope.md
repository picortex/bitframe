## Portfolio React Scope

### Acquisitions

You can acquire different elements of your scope on the file level as shown bellow:-

```typescript
const State = SDK.pimonitor.portfolio.PortfolioState 

const {
    /* intents */
    loadPortfolio,
    
    /* hooks */
    useStateFromViewModel
} = scope.portfolio
```

### Intents

- #### Load Portfolio
  This intent asks the api to fetch portfolio data of the current logged in monitor. It should be invoked only once during the render cycle as described bellow
  ```typescript
  // . . .
  function PortfolioView() {
    useEffect(() => {
      loadPortfolio()
    },[])
  }
  ```

### States

There are only three states in this scope. Assuming you already have your state as covered earlier,

```typescript
const state = useStateFromViewModel()
```

- #### Loading

  Gets information on what is being loaded through the message property

  ```typescript
  if(state instanceof State.Loading) {
    return <p>{state.message}</p>
  }
  ```

- #### Portfolio Data
  If data has successfully been fetched and can be displayed, the viewmodel will have this state. All available properties in the state object can be seen
  in [PortfolioState.Portfolio](../../../../pi-monitor/pi-monitor-sdk/client/core/src/commonMain/kotlin/pimonitor/portfolio/PortfolioState.kt)
  ```typescript
  if(state instanceof State.Porfolio) {
    return (<ul>
      {state.cards.toArray().map(card => {
        return (<p>{`${card.title}: ${card.value}`});
      })}
    <ul/>);
  }
  ```
- #### Failure
  If there is any type of failure, the viewmodel will result into this state.
  ```typescript
  if(state instanceof State.Failure) {
    return <p>{`${state.message}`}</p>
  }
  ```