const sdk = require("../../../build/productionLibrary/pi-monitor-client-sdk-core.js")
const service = sdk.service({appId: "1234"})
console.log(sdk.signIn)