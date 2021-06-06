package bitframe

import bitframe.actions.RawAction
import bitframe.http.HttpRoute

sealed class UnderTest
class ApplicationUnderTest<A : BitframeApplication>(val application: A) : UnderTest()
class ModuleUnderTest<M : Module>(val module: M) : UnderTest()
class RouteUnderTest<R : HttpRoute>(val route: R) : UnderTest()
class ActionUnderTest(val action: RawAction) : UnderTest()