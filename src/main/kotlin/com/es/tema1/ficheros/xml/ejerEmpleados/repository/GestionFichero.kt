package com.es.tema1.ficheros.xml.ejerEmpleados.repository

import com.es.tema1.ficheros.xml.ejerEmpleados.model.Empleado
import java.io.BufferedReader
import java.io.IOError
import java.io.IOException
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.notExists

class GestionFichero {


    fun readFichero(rutaFichero: Path): Pair<List<String>, Map<String, Empleado>> {


        // COmprobación de que el fichero existe
        if (rutaFichero.notExists()) {
            Files.createDirectories(rutaFichero.parent)
            Files.createFile(rutaFichero)
        }


        // Lectura del fichero usando BufferedReader
        val empleados: MutableMap<String, Empleado> = mutableMapOf();
        var claves: List<String> = listOf();

        // Apertura del flujo de lectura
        val br: BufferedReader = Files.newBufferedReader(rutaFichero)

        br.use {
            claves = it.readLine().split(",")

            it.forEachLine { line ->
                val splittedLine = line.split(",")
                try {

                    val key = splittedLine[0]
                    val apellido = splittedLine[1]
                    val dpto = splittedLine[2]
                    val salario = splittedLine[3].replace(",", ".").toDouble()
                    empleados.put(key, Empleado(key, apellido, dpto, salario))

                } catch (e: Exception) {
                    throw Exception("Error al leer el fichero")
                }
            }
        }

        return Pair(claves, empleados)
    }
}