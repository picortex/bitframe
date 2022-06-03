package pimonitor

import identifier.NameGenerator

class DemoBusiness(
    val name: String,
    val domain: String,
    val secret: String,
    val contactName: String = NameGenerator.randomFullName(),
    val contactEmail: String = "support@picortex.com",
) {
    companion object {
        val defaults = listOf(
            DemoBusiness("Ziyahlanjwa Trading (Pty) Ltd", "ziyahlanjwa", "fbjkfsk4vk34n05nrtr4g3r156"),
            DemoBusiness("Hopefield Raised", "hopefield", "5bsu6s2tk9g79jjskefn8i4kjb"),
            DemoBusiness("Ludada and Associates Orthopaedic Services", "ludada", "a8lch29mut8tp4r5kvm2vtgakc"),
            DemoBusiness("Bleu Rose (Pty) Ltd", "bleurose", "ss9amfj7cdhlag4peu1o74mlkg"),
            DemoBusiness("Curl Chemistry", "curlchemistry", "j811kldtbgm3pd93mip6eudeg5"),
        )
    }
}