package com.es.tema1.ficheros.plano

import java.io.BufferedReader
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path


fun main() {


    val ficheroPrueba = Path.of("src/main/resources/prueba.txt")
    // Vamos a explorar cómo leer un archivo con BufferedReader
    // Debemos crear/abrir un flujo de lectura de tipo BufferedReader
    val br: BufferedReader = Files.newBufferedReader(ficheroPrueba)

//    // Una vez tenemos el flujo de lectura abierto, podemos consumirlo
//    br.forEachLine { line -> println(line) }
//    // IMPORTANTE cerrar los flujos
//    br.close()

    // Una manera más segura de recorrer todas las líneas es usando el .use
    // El .use asegura que aunque haya excepciones, se cierra el flujo
    br.use { flujo ->
        flujo.forEachLine {
            // line -> println(it)
            println(it)
        }
    }

    // BUFFERED WRITER
    val ficheroPrueba2 = Path.of("src/main/resources/prueba2.txt")
    // Vamos a explorar cómo ESCRIBIR un archivo con BufferedWriter
    // Debemos crear/abrir un flujo de escritura de tipo BufferedWriter
    val bw: BufferedWriter = Files.newBufferedWriter(ficheroPrueba2)

    // .use para asegurar que liberamos el recurso
    bw.use { flujo ->
        flujo.write("Hola Mundo Writer")
        flujo.append("Hola Mundo Writer1")
        flujo.append("Hola Mundo Writer2")
        flujo.append("Hola Mundo Writer3")
        flujo.append("Hola Mundo Writer4")
        flujo.newLine()
        flujo.append("Hola Mundo Writer4")

    }
}