package bitframe

import bitframe.http.HttpRoute

sealed class ComponentUnderTest
class ApplicationUnderTest<S, A : Application<S>>(val application: A) : ComponentUnderTest()
class ModuleUnderTest<M : Module>(val module: M) : ComponentUnderTest()
class RouteUnderTest(val route: HttpRoute) : ComponentUnderTest()
class ActionUnderTest(val action: Action) : ComponentUnderTest()