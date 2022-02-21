## Bitframe React Scope

The bitframe react scope is a base scope to many applications written with bitframe.

This scope comprises several UI Scopes and a few simple utility functions.

```typescript
import { scope } from "lib/sdk.js" 
```

### Utility Functions

- #### signOut
  Invoke this function if you need to sign out of the sdk
  ```typescript
  scope.signOut()
  ```

- #### switchSpace
  Invoke this function if you need to switch from one space to another space
  ```typescript
  const space = getNewSpace();
  scope.switchSpace(space)
  ```
