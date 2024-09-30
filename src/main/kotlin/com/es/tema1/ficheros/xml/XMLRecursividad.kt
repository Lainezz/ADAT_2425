package com.es.tema1.ficheros.xml

import org.w3c.dom.Node
import java.io.File
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

// Función recursiva que procesa cada nodo y sus hijos para obtener los textNodes
fun obtenerTextNodes(nodo: Node) {
    // Verificamos si el nodo es de tipo texto y si contiene texto
    if (nodo.nodeType == Node.TEXT_NODE && nodo.textContent.trim().isNotEmpty()) {
        println("Texto encontrado: ${nodo.textContent.trim()}")
    }

    // Si el nodo tiene hijos, recorremos sus hijos de manera recursiva
    val hijos = nodo.childNodes
    for (i in 0 until hijos.length) {
        val nodoHijo = hijos.item(i)
        obtenerTextNodes(nodoHijo) // Llamada recursiva para procesar el nodo hijo
    }
}

// Función principal para iniciar el procesamiento del archivo XML
fun procesarArchivoXML(file: File) {
    try {
        // Inicializamos el parser DOM
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(file)

        // Normalizamos el documento
        doc.documentElement.normalize()

        // Iniciamos la recursividad desde el elemento raíz del documento
        obtenerTextNodes(doc.documentElement)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
    val file = Path.of("src/main/resources/ficherosXML/productos.xml")
    procesarArchivoXML(file.toFile())
}