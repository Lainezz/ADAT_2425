package com.es.tema1.ficheros.xml.ejerLecturaNoticias

import com.es.tema1.ficheros.xml.ejerLecturaNoticias.classes.Noticia
import com.es.tema1.ficheros.xml.ejerLecturaNoticias.repository.NotasPrensaRepository
import java.nio.file.Path

fun main() {

    val rutaFichero = Path.of("src/main/resources/ficherosXML/notas-prensa.xml")


    val repository = NotasPrensaRepository.getInstance(rutaFichero)
    val listaNoticias : List<Noticia> = repository.getItems()

    listaNoticias.forEach { println(it.toString()) }



}