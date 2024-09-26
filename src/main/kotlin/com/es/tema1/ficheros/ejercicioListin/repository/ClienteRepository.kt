package com.es.tema1.ficheros.ejercicioListin.repository

import com.es.tema1.ficheros.ejercicioListin.model.Cliente
import java.io.BufferedReader
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class ClienteRepository(val rutaFichero: Path) {


    fun addTlfn(name: String, tlfn: String) : Cliente? {

        // Comprobar que el fichero existe
        if(Files.notExists(rutaFichero)) {
            // createDirectories crea todos los directorios necesarios
            Files.createDirectories(rutaFichero.parent)
            // createFile crea el fichero
            Files.createFile(rutaFichero)
        }

        // Leer el fichero
        val br: BufferedReader = Files.newBufferedReader(rutaFichero)
        var existe = false;
        br.use {
            it.forEachLine {
                line ->
                val lineaSpliteada = line.split(",")
                if (lineaSpliteada[1].equals(tlfn)) {
                    existe = true
                }
            }
        }

        if(existe) {
            return null
        } else {

            // Si no existe, debemos añadir el telefono al cliente
            // Abrimos flujo de escritura
            val bw: BufferedWriter =
                Files.newBufferedWriter(rutaFichero, StandardOpenOption.APPEND)

            // Ahora escribimos el telefono con el cliente
            bw.use {
                it.append(name)
                it.append(",")
                it.append(tlfn)
                it.newLine()
            }

            return Cliente(name, tlfn)

        }


    }
}