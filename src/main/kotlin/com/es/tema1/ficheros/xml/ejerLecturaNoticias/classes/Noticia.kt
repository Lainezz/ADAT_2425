package com.es.tema1.ficheros.xml.ejerLecturaNoticias.classes

import java.time.OffsetDateTime
import java.time.ZonedDateTime

data class Noticia(val titulo: String, val link: String, val guid: String, val pubDate: OffsetDateTime, val description: String) {
}