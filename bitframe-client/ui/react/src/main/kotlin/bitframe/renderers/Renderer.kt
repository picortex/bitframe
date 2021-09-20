package bitframe.renderers

import react.RBuilder
import react.router.dom.RouteResultProps

typealias Renderer = RBuilder.(props: RouteResultProps) -> Unit