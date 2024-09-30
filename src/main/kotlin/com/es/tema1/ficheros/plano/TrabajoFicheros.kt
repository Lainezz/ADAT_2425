package com.es.tema1.ficheros.plano

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

/*
fun main(args: Array<String>) {


    // 1º Declarar una variable que almacene el File
    // file.separator nos da el separador por defecto de nuestro OS
    val separador = System.getProperty("file.separator")
    val separador2 = File.separator;
    println(separador)
    println(separador2)

    // user.dir nos da la ruta hasta nuestro proyecto
    val userDir = System.getProperty("user.dir")
    println(userDir)

    // user.home nos da la ruta hasta nuestro usuario
    val userHome = System.getProperty("user.home")
    println(userHome)

    // os.name
    val osName = System.getProperty("os.name")
    println(osName)

    // Clase File
    var ficheroPrueba: File = File("src${separador}" +
            "main${separador}resources${separador}prueba.txt")

    if (ficheroPrueba.exists()) {
        println("Fichero existe")
    } else {
        println("Fichero no existe")
    }


    // Otros métodos de la clase File

    var rutaRoot = Path.of("src")
    var rutaFinal = rutaRoot.resolve("main/resources/prueba.txt");
    var rutaFinal2 = rutaRoot.resolve("main/resources/prueba2.txt")
    println(rutaFinal)

    if(!rutaFinal2.exists()) {

        Files.createFile(rutaFinal2)
    }

    // Podemos usar BufferedReader, la cual tiene el método readLines()
    println("Buffered Reader")
    val fichero = Files.newBufferedReader(rutaFinal, Charset.defaultCharset())
    fichero.readLines().forEach { linea -> println(linea) }

    // Si el fichero es muy grande, se puede hacer de la siguiente manera
    val fichero3 = Files.newBufferedReader(rutaFinal, Charset.defaultCharset())
    fichero3.use { br ->
        br.forEachLine { line -> println(line) }
    }
    fichero3.close()


    // También podemos usar la API Stream para leer archivos grandes de texto.
    // Files.lines nos va a dar un Stream de Strings, que corresponderán a las líneas del documento.
    // Estas líneas se cargarán en memoria de forma "Lazy", es decir, sólo una porción del contenido estará en memoria a cada momento
    println("Stream")
    val fichero2 = Files.lines(rutaFinal)
    fichero2.forEach(::println)


    val rutaPrueba = Path.of("src/main/resources/prueba.txt")
    val rutaPrueba2 = Path.of("src/main/resources/CarpetaPrueba")

    println(rutaPrueba)
    println(rutaPrueba2)

    Files.copy(rutaPrueba, rutaPrueba2, LinkOption.NOFOLLOW_LINKS)

}*/

fun copiarArchivo() {
    // Definir la ruta del archivo de origen
    val sourcePath = Path.of("src/main/resources/prueba.txt")

    // Definir la ruta del archivo de destino
    val targetPath = Path.of("src/main/resources/CarpetaPrueba/documento.txt")

    try {

        // Crear el directorio de destino si no existe
        Files.createDirectories(targetPath.parent)

        // Copiar el archivo usando Files.copy
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING)
        println("Archivo copiado correctamente de $sourcePath a $targetPath.")

        // Verificar si el archivo fue copiado correctamente
        if (Files.exists(targetPath)) {
            println("La copia se ha realizado con éxito.")
        } else {
            println("El archivo no se copió correctamente.")
        }

    } catch (e: Exception) {
        // Manejo de excepciones si ocurre un error durante la copia
        println("Error al copiar el archivo: ${e.message}")
    }
}

fun main() {
    copiarArchivo()
}


