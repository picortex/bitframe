### SignInScope

As the name suggests, this is a [UIScope](./UIScope.md) used in sign in screens

Just like any other [UIScope](./UIScope.md), it has a viewModel. which consumes the following specific intents and spits out the relative state respectively

<pre class="highlight highlight-source-ts position-relative overflow-auto">
import { scope } from ". . ."
const {
  viewModel,
  /** intents */
  <a href="#initform">initForm</a>,
  <a href="#submit">submit</a>,
  <a href="#resolve">resolve</a>,
  /** hooks */
  <a href="#usesigninevent">useSignInEvent</a>
} = scope.signIn
</pre>

```typescript
import { scope } from ". . ."

const {
  viewModel,
  /** intents */
  initForm,
  submit,
  resolve,
  /** hooks */
  useSignInEvent
} = scope.signIn
```

#### Intents

- ##### initForm

  This is an intent that helps initiate the form fields.
  ```typescript
  const { initForm } = signInScope;
  
  export function SignInPage() {
    useEffect(()=>{
      initForm()
    },[])
  }
  ```

  It is advised to call `initForm` at the start of the rendering cycle
- #### submit

  Submit intent should be invoked when the user has finished entering the data on the form and submitted it. The intent should be called with the email and password fields as show in
  the [SignInCredentials](../../../bitframe-authentication/services/client/core/src/jsMain/kotlin/bitframe/authentication/signin/exports/SignInCredentials.kt) interface
  ```typescript
  const { submit } = signInScope;
  
  export function SignInPage() {
    return <Form onSubmit={(e)=>{
      submit({
        email: e.target.email.value,
        password: e.target.password.value
      })
    }}>
  }
  ```

- #### resolve

  This intent should be invoked while attempting to resolve a known conundrum.

  ```typescript
  const { resolve } = signInScope
  ```

### [States](../../../bitframe-sdk/client/core/src/commonMain/kotlin/bitframe/authentication/signin/SignInState.kt)

```typescript
import SDK, { useViewModelState } from ". . ."
const State = SDK.bitframe.authentication.signin.SignInState
const state = useViewModelState(viewmodel)
```

- #### Form

  This is the default state that the scope begins in, As it can be seen in [SignInState.Form](../../../bitframe-sdk/client/core/src/commonMain/kotlin/bitframe/authentication/signin/SignInState.kt), it
  has the following properties

    - [fields](../../../bitframe-sdk/client/core/src/commonMain/kotlin/bitframe/authentication/signin/SignInFormFields.kt)

      The fields carry information on what should be displayed on the form

    - [status](../../../bitframe-utils/presenters/core/src/commonMain/kotlin/presenters/feedbacks/FormFeedback.kt)

      The status property presents gives you access to get feedback from the viewmdoel. The feedback can be due to an error, success, or a background work that is being done by the viewmodel

      ```typescript      
      if(state instanceof State.Form) {
        const { 
            title, // string
            email, // TextInputField,
            password, // TextInputField, 
        } = state.fields;
        return (
          <div>
            <p>{title}</p>
            <span>{state.status ? state.status.message : ""}</span>
            <input placeholder={email.hint}/>
            <input placeholder={password.hint}/>
          </div>
        );
      }
      ```
- #### Conundrum
  A conundrum state is when a user with multiple users spaces attempts to log in, and the sdk can't choose which space to log the user into. The state offers the user a choice to select which space
  they need to log into. And the spaces can be retrieved from the spaces property

  ```typescript      
  if(state instanceof State.Conundrum) {
    return (
      <div>
        <p>Choose your space</p>
        {state.spaces.toArray().map((space)=>{
            return <p onClick={()=>resolve(space)}>{space.name}</p>
        })}
      </div>
    );
  }
  ```

### Hooks

#### useSignInEvent

A hook