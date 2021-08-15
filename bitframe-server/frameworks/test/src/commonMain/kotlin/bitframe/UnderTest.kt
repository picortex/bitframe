package bitframe

import bitframe.server.BitframeApplication
import bitframe.server.actions.Action
import bitframe.server.http.HttpRoute
import bitframe.server.modules.Module

sealed class UnderTest
class ApplicationUnderTest<A : BitframeApplication>(val application: A) : UnderTest()
class ModuleUnderTest<M : Module>(val module: M) : UnderTest()
class RouteUnderTest<R : HttpRoute>(val route: R) : UnderTest()
class ActionUnderTest(val action: Action) : UnderTest()