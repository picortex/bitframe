package pimonitor.server

import bitframe.core.MockDaoFactory
import bitframe.server.ServiceConfig
import bitframe.server.bitframeApplication
import mailer.SmtpMailer
import mailer.SmtpMailerConfig
import pimonitor.server.business.financials.BusinessFinancialsController
import pimonitor.server.business.financials.BusinessFinancialsModule
import pimonitor.server.business.interventions.BusinessInterventionsController
import pimonitor.server.business.interventions.BusinessInterventionsModule
import pimonitor.server.business.investments.BusinessInvestmentsController
import pimonitor.server.business.investments.BusinessInvestmentsModule
import pimonitor.server.business.operations.BusinessOperationsController
import pimonitor.server.business.operations.BusinessOperationsModule
import pimonitor.server.business.overview.BusinessOverviewController
import pimonitor.server.business.overview.BusinessOverviewModule
import pimonitor.server.businesses.BusinessesController
import pimonitor.server.businesses.BusinessesModule
import pimonitor.server.contacts.ContactsController
import pimonitor.server.contacts.ContactsModule
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

fun main(args: Array<String>) {
    bitframeApplication<PiMonitorService> {
        public = File(args.getOrNull(0) ?: "/default")
        database {
            MockDaoFactory()
//            MongoDaoFactory(
//                config = MongoDaoFactoryConfig(
////                    host = "127.0.0.1:27017",
//                    host = "database:27017",
//                    username = "root",
//                    password = "example",
//                    database = "pi"
//                )
//            )
        }

        service { factory ->
            val config = ServiceConfig(
                daoFactory = factory,
                mailer = SmtpMailer(SmtpMailerConfig.fromProperties("sendgrid.properties"))
            )
            PiMonitorService(config)
        }

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
            BusinessInvestmentsModule(BusinessInvestmentsController(ser.businessInvestments))
        }

        install { ser ->
            BusinessInterventionsModule(BusinessInterventionsController(ser.businessInterventions))
        }

        install { ser ->
            BusinessOverviewModule(BusinessOverviewController(ser.businessOverview))
        }

        install { ser ->
            ContactsModule(ContactsController(ser.contacts))
        }

        install { ser ->
            InvestmentsModule(InvestmentsController(ser.investments))
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