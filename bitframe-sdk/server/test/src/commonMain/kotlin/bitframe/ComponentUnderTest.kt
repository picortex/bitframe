package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.BitframeService
import bitframe.server.Action
import bitframe.server.http.HttpRoute
import bitframe.server.Module

sealed class ComponentUnderTest
class ApplicationUnderTest<S : BitframeService, A : BitframeApplication<S>>(val application: A) : ComponentUnderTest()
class ModuleUnderTest<M : Module>(val module: M) : ComponentUnderTest()
class RouteUnderTest(val route: HttpRoute) : ComponentUnderTest()
class ActionUnderTest(val action: Action) : ComponentUnderTest()