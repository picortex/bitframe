package events

class SubscriptionException(message: String, cause: ClassCastException) : RuntimeException(message, cause)