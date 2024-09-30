package com.es.tema1.ficheros.plano.ejercicioCotizaciones.utils

import java.io.BufferedReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.notExists

//class GestionFicheroCotiz(val rutaFichero: Path) {
class GestionFicheroCotiz() {
//    init {
//        // Si rutaFichero no existe, creo el sistema de archivos hasta el mismo
//        if(rutaFichero.notExists()) {
//            Files.createDirectories(rutaFichero.parent)
//            Files.createFile(rutaFichero)
//        }
//    }

    fun createFichero(rutaFichero: Path) {
        // Si rutaFichero no existe, creo el sistema de archivos hasta el mismo
        if (rutaFichero.notExists()) {
            Files.createDirectories(rutaFichero.parent)
            Files.createFile(rutaFichero)
        }
    }

    fun getCotizDictionary(rutaFichero: Path): MutableMap<String, MutableList<Double>> {

        // Primero creo el fichero si no está creado
        createFichero(rutaFichero)

        // Creo el diccionario que almacenará la informacion
        val diccionario = mutableMapOf<String, MutableList<Double>>()

        // Luego recorro el fichero con un BufferedReader para obtener el diccionario
        val br: BufferedReader = Files.newBufferedReader(rutaFichero, StandardCharsets.UTF_8)

        // Usamos una lista temporal para almacenar las claves de la primera linea del fichero
        var claves = listOf<String>()

        br.use {
            var fl = true
            it.forEachLine { line ->
                // lineaSpliteada es cada linea del fichero, la cual dividimos por ;
                val lineaSpliteada = line.split(";")
                if (fl) {

                    // Asigno la primera linea a claves para poder acceder a dichas claves cuando se procesen el resto de lineas
                    claves = lineaSpliteada
                    // Inserto en el diccionario como claves, la primera linea del documento
                    lineaSpliteada.forEach {
                        diccionario.put(it, mutableListOf<Double>())
                    }
                    fl = !fl;
                } else {
                    // forEachIndexed nos da la flexibilidad de contar con un índice, ya que lo necesitamos para acceder a
                    // una posicion concreta de la lineaSpliteada (que es la lista con los valores de la linea)
                    claves.forEachIndexed { index, clave ->
                        // Recordamos que la columna de nombre no la vamos a tratar
                        if (index != 0) diccionario[clave]!!.add(
                            lineaSpliteada.get(index).replace(".", "", true)
                                .replace(",", ".", true).toDouble()
                        )
                    }
                }

            }
        }
        return diccionario
    }

    fun writeCotizFile(diccionario: Map<String, MutableList<Double>>, rutaFicheroFinal: Path) {

        // Creo el fichero si no existe
        createFichero(rutaFicheroFinal)

        // Maximos
        val maximoFinal = diccionario.get("Final")!!.sortedDescending().first()
        val maximoMaximo = diccionario.get("Maximo")!!.sortedDescending().first()
        val maximoMinimo = diccionario.get("Minimo")!!.sortedDescending().first()
        val maximoVolumen = diccionario.get("Volumen")!!.sortedDescending().first()
        val maximoEfectivo = diccionario.get("Efectivo")!!.sortedDescending().first()

        // Minimos

        // Abro flujo BufferedWriter
        val bw = Files.newBufferedWriter(rutaFicheroFinal, StandardOpenOption.WRITE)


        bw.use {
            var fitem: Boolean = true
            diccionario.forEach { clave, listaValores ->

                if (!fitem) {
                    val maximo = listaValores.maxOf { it }
                    val minimo = listaValores.minOf { it }
                    val media = maximo.div(minimo)

                    it.write(clave)
                    it.write(";")
                    it.write(minimo.toString())
                    it.write(";")
                    it.write(maximo.toString())
                    it.write(";")
                    it.write(media.toString())
                    it.newLine()
                } else {
                    it.write("Nombre;Minimo;Maximo;Media")
                    it.newLine()
                    fitem = !fitem
                }


            }
        }



    }


}