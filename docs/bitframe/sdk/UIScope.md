## UIScopes

A UIScope is very synonymous to a screen, but it has much more going on than just a screen. A Scope has an underlying viewmodel which is responsible for managing state and handling errors.

Normally a UIScope gives access to that ViewModel, also makes it easy to invoke the necessary intents exposed as functions.

For react, these scopes also come with hooks which can be directly invoked in functional components as well 