package presenters.cases

import presenters.actions.SimpleActionsBuilder

fun <C, D> MissionState<C, D>.copy(
    message: String
): MissionState<C, D> = MissionState.Loading(
    message = message,
    context = context,
    data = data
)

fun <C, D> MissionState<C, D>.copy(
    cause: Throwable,
    builder: (SimpleActionsBuilder.() -> Unit)? = null
): MissionState<C, D> = MissionState.Failure(
    cause = cause,
    context = context,
    data = data,
    builder = builder
)

fun <C, D> MissionState<C, D>.copy(
    context: C,
    data: D
): MissionState<C, D> = MissionState.Success(context, data)