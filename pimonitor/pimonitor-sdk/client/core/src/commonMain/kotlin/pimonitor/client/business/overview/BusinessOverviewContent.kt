@file:JsExport

package pimonitor.client.business.overview

import kotlin.js.JsExport

sealed class BusinessOverviewContent {
    data class FinancialAndOperational(val data: Any) : BusinessOverviewContent()
    data class FinancialOnly(val data: Any) : BusinessOverviewContent()
    data class OperationalOnly(val data: Any) : BusinessOverviewContent()
    data class NoIntegrationWithInvite(val data: Any) : BusinessOverviewContent()
    data class NoIntegrationWithoutInvite(val data: Any) : BusinessOverviewContent()

    val isFinancialAndOperational by lazy { this is FinancialAndOperational }
    val asFinancialAndOperational by lazy { this as OperationalOnly }

    val isFinancialOnly by lazy { this is FinancialOnly }
    val asFinancialOnly by lazy { this as FinancialOnly }

    val isOperationalOnly by lazy { this is OperationalOnly }
    val asOperationalOnly by lazy { this as OperationalOnly }

    val isNoIntegrationWithInvite by lazy { this is NoIntegrationWithInvite }
    val asNoIntegrationWithInvite by lazy { this as NoIntegrationWithInvite }

    val isNoIntegrationWithoutInvite by lazy { this is NoIntegrationWithoutInvite }
    val asNoIntegrationWithoutInvite by lazy { this as NoIntegrationWithoutInvite }
}