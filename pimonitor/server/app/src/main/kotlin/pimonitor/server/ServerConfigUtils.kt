package pimonitor.server

import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAServiceMock
import akkounts.sage.toSageOneZAServiceConfig
import bitframe.core.IllegalConfiguration
import bitframe.core.MockDaoFactory
import bitframe.server.MongoDaoFactory
import bitframe.server.MongoDaoFactoryConfig
import bitframe.server.properties
import bitframe.server.readPropertiesOrNull
import mailer.MockMailer
import mailer.SmtpMailer
import mailer.SmtpMailerConfig
import pimonitor.core.picortex.PiCortexService
import pimonitor.core.picortex.PiCortexServiceMock
import pimonitor.core.picortex.toPicortexServiceConfig
import java.io.File
import java.io.IOException
import java.util.*


private fun unsupported(message: String): Nothing = throw UnsupportedOperationException("Unsupported $message")

private fun File.mailer(props: Properties) = when (val mailer = props["mailer"].toString().lowercase()) {
    "mock" -> MockMailer()
    "sendgrid" -> {
        val sendgrid = props["sendgrid"]?.toString() ?: throw IllegalArgumentException("missing sendgrid property")
        val file = File(this, sendgrid)
        SmtpMailer(SmtpMailerConfig.from(file.inputStream()))
    }
    else -> unsupported("mailer $mailer")
}

private fun File.database(props: Properties) = when (val db = props["database"].toString().lowercase()) {
    "mock" -> MockDaoFactory()
    "mongo" -> MongoDaoFactory(
        MongoDaoFactoryConfig.fromProperties(File(this, props["mongo"].toString()).inputStream())
    )
    else -> unsupported("database $db")
}

private fun File.sage(props: Properties): SageOneZAService {
    val sage = props["sage"]?.toString() ?: throw IllegalConfiguration("Missing sage config path")
    val sageProps = File(this, sage).properties()
    return when (sageProps["live"]) {
        "true" -> SageOneZAService(sageProps.toSageOneZAServiceConfig())
        else -> SageOneZAServiceMock()
    }
}

private fun File.picortex(props: Properties): PiCortexService {
    val picortex = props["picortex"]?.toString() ?: throw IllegalConfiguration("Missing picortex file")
    val picortexProps = File(this, picortex).properties()
    return when (picortexProps["live"]) {
        "true" -> PiCortexService(picortexProps.toPicortexServiceConfig())
        else -> PiCortexServiceMock()
    }
}


private fun File.config(path: String): ServerConfig {
    val props = File(this, path).properties()
    return ServerConfig(
        environment = props["environment"].toString(),
        database = database(props),
        mailer = mailer(props),
        sage = sage(props),
        picortex = picortex(props)
    )
}

internal fun appRoot(): File {
    val root = System.getenv("PIMONITOR_ROOT") ?: throw IllegalConfiguration("Missing PIMONITOR_ROOT environment variable")
    return File(root)
}

internal fun File.readConfig(): ServerConfig {
    val configDir = configDir()
    val configFile = System.getenv("PIMONITOR_CONFIG")?.let {
        File(configDir, "server/$it.properties")
    } ?: run {
        File(configDir, "server").listFiles()?.first() ?: throw IllegalConfiguration("Missing config file")
    }
    return configDir.config("server/${configFile.name}")
}

internal fun File.configDir(): File {
    val config = File(this, "config")
    if (!config.exists()) {
        throw IOException("Missing ${this.absoluteFile}/config")
    }
    return config
}