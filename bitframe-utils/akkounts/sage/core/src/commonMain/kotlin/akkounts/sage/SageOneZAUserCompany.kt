package akkounts.sage

/**
 * @param uid ID as stored in the local database
 * @param name Name as stored in the local database
 * @param username Username/Email that is used to login into sage
 * @param password password that is used to login into sage
 * @param companyId CompanyID as saved/stored by sage
 */
data class SageOneZAUserCompany(
    val uid: String,
    val name: String,
    val username: String,
    val password: String,
    val companyId: String
)