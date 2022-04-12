package pimonitor.core.configs

import akkounts.sage.SageOneZAService
import bitframe.core.ServiceConfigDaod

private val ServiceConfigDaod.SAGE_API_KEY get() = "{C7542EBF-4657-484C-B79E-E3D90DB0F0D1}"

val ServiceConfigDaod.sageService get() = SageOneZAService(SAGE_API_KEY)