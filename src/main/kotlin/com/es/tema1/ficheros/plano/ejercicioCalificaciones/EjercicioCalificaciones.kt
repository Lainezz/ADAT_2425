package com.es.tema1.ficheros.plano.ejercicioCalificaciones

import com.es.tema1.ficheros.plano.ejercicioCalificaciones.utils.GestionFicheroCalificaciones
import com.es.tema1.ficheros.plano.ejercicioCotizaciones.utils.GestionFicheroCotiz
import java.nio.file.Path

fun main() {


    val rutaFichero = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("ficherosTema1")
        .resolve("calificaciones")
        .resolve("calificaciones.csv")
//    val rutaFicheroFinal = Path.of("src")
//        .resolve("main")
//        .resolve("resources")
//        .resolve("ficherosTema1")
//        .resolve("cotizaciones")
//        .resolve("cotizacionesFinal.csv")

    val gestion = GestionFicheroCalificaciones()


    val listaEstudiantes = gestion.getCalificaciones(rutaFichero)
    gestion.addFinalNote(listaEstudiantes)
    gestion.getAprobados(listaEstudiantes)
}