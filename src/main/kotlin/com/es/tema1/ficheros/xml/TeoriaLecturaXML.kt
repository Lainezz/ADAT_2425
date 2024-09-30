package com.es.tema1.ficheros.xml

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

fun main() {

    // LECTURA DE FICHERO XML

    // EL OBJETIVO ES PARSEAR UN FICHERO XML A ARBOL DOM
    // 1º Sería instanciar un objeto DocumentBuilderFactory
    val dbf = DocumentBuilderFactory.newInstance()
    // 2º Con el dbf, podemos crear un objeto de tipo DocumentBuilder
    val db = dbf.newDocumentBuilder()
    // 3º Cuando tenemos el db, ya podemos "parsear" nuestro documento
    // (Primero necesitamos el file)
    val ficheroXMl = Path.of("src").resolve("main/resources/ficherosXML/productos.xml")
    // Ahora sí "parseamos"
    val document = db.parse(ficheroXMl.toFile())

    // Dentro de la clase Document, tenemos un método importante
    // 1. para obtener el elemento root
    val root: Element = document.documentElement

    // Dentro de la clase Element tenemos varios métodos importantes
    // 1. para "normalizar" el arbol
    root.normalize()

    // 2. Para obtener elementos por su nombre de etiqueta
    val listaNodos: NodeList = root.getElementsByTagName("producto")

    // Cuando tenemos la NodeList, podemos iterar sobre ella
    for (i in 0..<listaNodos.length){

        // Para acceder a un item en particular, accedemos a través del index
        val nodo:Node = listaNodos.item(i)

        // Para acceder al tipo del Nodo, usamos .nodeType
        if(nodo.nodeType == Node.ELEMENT_NODE) {

            // "Casteamos" a Element
            val nodoElemento = nodo as Element

            // Podemos buscar los elementos que nos convienen
            val elementoNombre = nodoElemento.getElementsByTagName("nombre")
            val elementoPrecio = nodoElemento.getElementsByTagName("precio")

            // Una vez tenemos el elemento que queremos, podemos acceder a su contenido
            val textContentNombre = elementoNombre.item(0).textContent
            val textContentPrecio = elementoPrecio.item(0).textContent.toDouble()

            // imprimo
            println("Producto ${i}:\n\t - nombre: ${textContentNombre}\n" +
                    "\t - precio: ${textContentPrecio}")
        }


    }




}