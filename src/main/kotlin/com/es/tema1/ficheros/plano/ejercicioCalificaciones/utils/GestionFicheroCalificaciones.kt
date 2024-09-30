package com.es.tema1.ficheros.plano.ejercicioCalificaciones.utils

import java.io.BufferedReader
import java.lang.NumberFormatException
import java.nio.file.Files
import java.nio.file.Path
import java.util.TreeMap
import kotlin.io.path.notExists

class GestionFicheroCalificaciones {


    private fun createFichero(rutaFichero: Path) {
        // Si rutaFichero no existe, creo el sistema de archivos hasta el mismo
        if (rutaFichero.notExists()) {
            Files.createDirectories(rutaFichero.parent)
            Files.createFile(rutaFichero)
        }
    }

    fun getCalificaciones(rutaFichero: Path): List<MutableMap<String, String>> {

        // Primero creamos el fichero si no existe
        createFichero(rutaFichero)

        // Creo la lista que va a contener a todos los estudiantes
        val listaEstudiantes = mutableListOf<MutableMap<String, String>>()
        val claves = mutableListOf<String>()

        // Abro el fichero y leo su contenido
        val br: BufferedReader = Files.newBufferedReader(rutaFichero)

        br.use {
            var fl = true
            it.forEachLine {
                line ->
                val splittedLine = line.split(";")
                if(!fl) {
                    val infoEstudiante = mutableMapOf<String, String>()
                    splittedLine.forEachIndexed { index, s ->
                        infoEstudiante[claves[index]] = s.replace(",", ".").ifEmpty { "0" }
                    }
                    listaEstudiantes.add(infoEstudiante)
                } else {
                    splittedLine.forEach { clave -> claves.add(clave) }
                    fl=!fl
                }
            }
        }

        println()
        return listaEstudiantes.sortedBy {
            it["Apellidos"]
        }
    }

    fun addFinalNote(listaEstudiantes: List<MutableMap<String, String>>) {

        println("de")
        listaEstudiantes.forEach {

            val parcial1 = it["Parcial1"]!!.toDouble()
            val parcial2 = it["Parcial2"]!!.toDouble()
            val ordinario1 = it["Ordinario1"]!!.toDouble()
            val ordinario2 = it["Ordinario2"]!!.toDouble()
            val practicas = it["Practicas"]!!.toDouble()
            val ordinarioPracticas = it["OrdinarioPracticas"]!!.toDouble()

            var nota1 = parcial1
            var nota2 = parcial2
            var nota3 = practicas

            if(ordinario1 > parcial1) {
                nota1 = ordinario1
            }

            if(ordinario2 > parcial2) {
                nota2 = ordinario2
            }

            if(ordinarioPracticas > practicas) {
                nota3 = ordinarioPracticas
            }

            var notaFinal = nota1.times(0.3) + nota2.times(0.3) * nota3.times(0.4)

            it.put("NotaFinal", notaFinal.toString())

        }
    }

    fun getAprobados(listaEstudiantes: List<MutableMap<String, String>>): Pair<List<MutableMap<String, String>>, List<MutableMap<String, String>>> {


        var listaAprobados = listaEstudiantes.filter {
            it.get("Asistencia")!! > "50%" && it.get("NotaFinal")!!.toDouble() >= 5.0
        }

        var listaSuspensos = listaEstudiantes.filter {
            it.get("Asistencia")!! < "50%" || it.get("NotaFinal")!!.toDouble() < 5.0
        }

        return Pair(listaAprobados, listaSuspensos)
    }

}