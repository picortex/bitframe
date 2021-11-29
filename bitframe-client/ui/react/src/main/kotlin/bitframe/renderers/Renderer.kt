package bitframe.renderers

import react.RBuilder
import react.router.dom.RouteComponentProps

typealias Renderer = RBuilder.(props: RouteComponentProps) -> Unit