package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module

sealed class ComponentUnderTest
class ApplicationUnderTest<A : BitframeApplication>(val application: A) : ComponentUnderTest()
class ModuleUnderTest<M : Module>(val module: M) : ComponentUnderTest()
class RouteUnderTest(val route: HttpRoute) : ComponentUnderTest()
class ActionUnderTest(val action: Action) : ComponentUnderTest()