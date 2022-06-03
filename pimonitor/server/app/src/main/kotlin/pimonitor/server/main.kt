package pimonitor.server

import bitframe.server.bitframeApplication
import pimonitor.server.business.financials.BusinessFinancialsController
import pimonitor.server.business.financials.BusinessFinancialsModule
import pimonitor.server.business.operations.BusinessOperationsController
import pimonitor.server.business.operations.BusinessOperationsModule
import pimonitor.server.business.overview.BusinessOverviewController
import pimonitor.server.business.overview.BusinessOverviewModule
import pimonitor.server.businesses.BusinessesController
import pimonitor.server.businesses.BusinessesModule
import pimonitor.server.contacts.ContactsController
import pimonitor.server.contacts.ContactsModule
import pimonitor.server.interventions.InterventionsController
import pimonitor.server.interventions.InterventionsModule
import pimonitor.server.investments.InvestmentsController
import pimonitor.server.investments.InvestmentsModule
import pimonitor.server.invites.InvitesController
import pimonitor.server.invites.InvitesModule
import pimonitor.server.portfolio.PortfolioController
import pimonitor.server.portfolio.PortfolioModule
import pimonitor.server.search.SearchController
import pimonitor.server.search.SearchModule
import pimonitor.server.signup.SignUpController
import pimonitor.server.signup.SignUpModule
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    val config = appRoot().readConfig()
    println("Running with $config")
    bitframeApplication<MonitorService> {
        public = File(args.getOrNull(0) ?: "/default")
        database { config.database }

        service { MonitorService(config) }

        install { ser ->
            SignUpModule(SignUpController(ser.signup))
        }

        install { ser ->
            BusinessesModule(BusinessesController(ser.businesses))
        }

        install { ser ->
            BusinessFinancialsModule(BusinessFinancialsController(ser.businessFinancials))
        }

        install { ser ->
            BusinessOperationsModule(BusinessOperationsController(ser.businessOperations))
        }

        install { ser ->
            InvestmentsModule(InvestmentsController(ser.investments))
        }

        install { ser ->
            InterventionsModule(InterventionsController(ser.interventions))
        }
        install { ser ->
            BusinessOverviewModule(BusinessOverviewController(ser.businessOverview))
        }

        install { ser ->
            ContactsModule(ContactsController(ser.contacts))
        }

        install { ser ->
            PortfolioModule(PortfolioController(ser.portfolio))
        }

        install { ser ->
            SearchModule(SearchController(ser.search))
        }

        install { ser ->
            InvitesModule(InvitesController(ser))
        }

        onStart {
//            populateTestEntities()
        }
    }.start()
}