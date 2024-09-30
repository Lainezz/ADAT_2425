package com.es.tema1.ficheros.plano.ejercicioCotizaciones

import com.es.tema1.ficheros.plano.ejercicioCotizaciones.utils.GestionFicheroCotiz
import java.nio.file.Path


fun main() {

    val rutaFichero = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("ficherosTema1")
        .resolve("cotizaciones")
        .resolve("cotizacion.csv")
    val rutaFicheroFinal = Path.of("src")
        .resolve("main")
        .resolve("resources")
        .resolve("ficherosTema1")
        .resolve("cotizaciones")
        .resolve("cotizacionesFinal.csv")

    val gestion: GestionFicheroCotiz = GestionFicheroCotiz()


    gestion.writeCotizFile(gestion.getCotizDictionary(rutaFichero), rutaFicheroFinal)


}