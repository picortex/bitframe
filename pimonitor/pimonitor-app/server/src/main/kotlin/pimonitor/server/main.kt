package pimonitor.server

import bitframe.core.MockDaoFactory
import bitframe.server.MongoDaoFactory
import bitframe.server.MongoDaoFactoryConfig
import bitframe.server.ServiceConfig
import bitframe.server.bitframeApplication
import mailer.MockMailer
import mailer.SmtpMailer
import mailer.SmtpMailerConfig
import pimonitor.server.businesses.BusinessController
import pimonitor.server.businesses.BusinessModule
import pimonitor.server.contacts.ContactsController
import pimonitor.server.contacts.ContactsModule
import pimonitor.server.portfolio.PortfolioController
import pimonitor.server.portfolio.PortfolioModule
import pimonitor.server.profile.ProfileController
import pimonitor.server.profile.ProfileModule
import pimonitor.server.search.SearchController
import pimonitor.server.search.SearchModule
import pimonitor.server.signup.SignUpController
import pimonitor.server.signup.SignUpModule
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    bitframeApplication<PiMonitorService> {
        public = File(args.getOrNull(0) ?: "/default")
        database {
//            MockDaoFactory()
            MongoDaoFactory(
                config = MongoDaoFactoryConfig(
                    host = "127.0.0.1:27017",
//                    host = "database:27017",
                    username = "root",
                    password = "example",
                    database = "pi"
                )
            )
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
            BusinessModule(BusinessController(ser.businesses))
        }
        install { ser ->
            ContactsModule(ContactsController(ser.contacts))
        }
        install { ser ->
            ProfileModule(ProfileController(ser.profile))
        }

        install { ser ->
            PortfolioModule(PortfolioController(ser.portfolio))
        }

        install { ser ->
            SearchModule(SearchController(ser.search))
        }

        onStart { populateTestEntities() }
    }.start()
}