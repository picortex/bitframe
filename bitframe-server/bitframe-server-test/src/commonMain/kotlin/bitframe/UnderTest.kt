package bitframe

import bitframe.http.HttpRoute

sealed class UnderTest
class ApplicationUnderTest<A : Application>(val application: A) : UnderTest()
class ModuleUnderTest<M : Module>(val module: M) : UnderTest()
class RouteUnderTest<R : HttpRoute>(val route: R) : UnderTest()