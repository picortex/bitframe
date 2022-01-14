## SignInScope

As the name suggests, this is a [UIScope](./UIScope.md) used in sign in screens

Just like any other [UIScope](./UIScope.md), it has a viewModel. which consumes the following specific intents and spits out the relative state respectively

<pre>
import { SDK, scope, useViewModelState } from ". . ."

const {
  viewModel,
  /** intents */
  <a href="#initform">initForm</a>,
  <a href="#submit">submit</a>,
  <a href="#resolveConundrum">resolveConundrum</a>,
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
  resolveConundrum,
  /** hooks */
  useSignInEvent
} = scope.signIn
```

### Intents

- #### initForm

  This is an intent that helps initiate the form fields.
  ```typescript
  export function SignInPage() {
    useEffect(()=>{
      initForm()
    },[])
  }
  ```

  `initForm` should be invoked only once at the beginning of the rendering cycle
- #### submit

  Submit intent should be invoked when the user has finished entering the data on the form and submitted it. The intent should be called with the email and password fields as show in
  the [SignInCredentials](../../../bitframe-authentication/services/client/core/src/jsMain/kotlin/bitframe/authentication/signin/exports/SignInCredentials.kt) interface
  ```typescript
  export function SignInPage() {
    return <Form onSubmit={(e)=>{
      submit({
        email: e.target.email.value,
        password: e.target.password.value
      })
    }}>
  }
  ```

- #### resolveConundrum

  This intent should be invoked while attempting to resolve a known [conundrum](#conundrum).

### [States](../../../bitframe-sdk/client/core/src/commonMain/kotlin/bitframe/authentication/signin/SignInState.kt)

To make it easier to get a hold of the SignInScope's state, we will get to the top level namespace and call it `State```

```typescript
const State = SDK.bitframe.authentication.signin.SignInState

function SingInPage() {
  const state = useViewModelState(viewmodel)
  // . . .
}
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
            return <p onClick={()=>resolveConundrum(space)}>{space.name}</p>
        })}
      </div>
    );
  }
  ```

### Hooks

#### useSignInEvent

This is a react hook, that gets called when a user successfuly signs in. It should be used to listen to the sign in events and navigate the user to the respective dashboard. The callback that is
passed in, will be invoked with a [session](../../../bitframe-authentication/core/src/commonMain/kotlin/bitframe/authentication/signin/Session.kt) which is an instance of
class [Session.SignedIn](../../../bitframe-authentication/core/src/commonMain/kotlin/bitframe/authentication/signin/Session.kt), and that can give you further information of the user that is currently
logged in

```typescript
import { useNavigate } from "react-router"

function SignInPage() {
  const navigate = useNavigate()
  useSignInEvent((session)=>{
    console.log(`Welcome ${session.user.name}`)
    navigate("/panel")
  })
  // . . .
}
```