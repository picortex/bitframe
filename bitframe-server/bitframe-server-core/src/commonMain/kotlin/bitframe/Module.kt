package bitframe

import bitframe.actions.Action
import bitframe.http.HttpRoute

interface Module {
    val name: String
    val actions: List<Action>
}