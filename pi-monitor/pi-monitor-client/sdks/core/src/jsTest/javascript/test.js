var sdk = require("../../../build/productionLibrary/bitframe-pi-monitor-client-sdk.js")

var service = sdk.service({appId: "1234"})
var loginViewModel = sdk.loginViewModel(service);
var loginIntent = sdk.loginIntent;

console.log(loginViewModel);
console.log(loginViewModel._ui);

loginViewModel._ui.watch((state)=>{
    console.log(state);
})

loginViewModel.post(loginIntent({username: "user1", password: "pass1" }))