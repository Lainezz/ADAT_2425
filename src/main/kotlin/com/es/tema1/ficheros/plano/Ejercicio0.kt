package com.es.tema1.ficheros.plano

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.notExists


fun main() {

    /*
    Mover el archivo prueba.txt a la carpeta CarpetaPrueba
     */

    val rutaRaiz = Path.of("src")
    val rutaFichero = rutaRaiz.resolve("main").resolve("resources").resolve("prueba.txt")

    val rutaDestino = rutaRaiz.resolve("main")
        .resolve("resources")
        .resolve("CarpetaPrueba")
        .resolve("documentoDestino.txt")

    if (rutaDestino.notExists()) {
        // Creo los directorios hasta la ruta destino
        Files.createDirectories(rutaDestino.parent)
        // Creo el fichero documentoDestino.txt
        Files.createFile(rutaDestino)
    }
    // Una vez creado el fichero destino y la ruta hacia el mismo, puedo copiar el contenido
    Files.copy(rutaFichero, rutaDestino, StandardCopyOption.REPLACE_EXISTING)

}



