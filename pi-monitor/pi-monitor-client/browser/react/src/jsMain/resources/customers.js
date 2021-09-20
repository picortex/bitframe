import SDK from "@picortex/pimonitor..."

// SDK.js

export function SDK() {
    const get = (entity)=> {
        return 1;
    }
    const getBeneficiary = ()=> get("Beneficiary")
}

export function Customer({id}) {

    const customerService = SDK.getCustomerService({appId: "dddd"})
    // What I would like
    SDK.getBeneficiary({id})

    // What I wouldn't like, but I would settle with
    SDK.get("customer")
    SDK.post("customer",{ id: 1221, name: "dd", balance: 4355 })
    SDK.get("food")
    SDK.getFood("")
}

// UIModule -> UIModule
// Dashboard ->
// Transaction ->