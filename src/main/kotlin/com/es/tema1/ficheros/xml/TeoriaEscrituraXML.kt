package com.es.tema1.ficheros.xml

import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main() {

    /*
    ESCRITURA DE ARCHIVOS XML
     */

    // 1º Instanciar la clase DocumentBuilderFactory
    val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    // Instanciar la clase DocumentBuilder
    val builder: DocumentBuilder = factory.newDocumentBuilder()
    // (Opcional) DOMImplementation contiene los métodos para crear un Document
    val imp: DOMImplementation = builder.domImplementation

    // 2º Procedemos a crear un Document vacío (namespaceURI, qualifiedName, doctype)
    val document: Document = imp.createDocument(null, "productos", null)

    // En este punto, ya tendríamos el primer "Element" creado. El nodo root.
    // 3º Sólo tenemos que adjuntar hijos al nodo root

    // a) Primero, creamos el Element
    val producto1: Element = document.createElement("producto")
    document.documentElement.appendChild(producto1)

    // b) Hijos de producto1
    val nombreP1: Element = document.createElement("nombre")
    val precioP1: Element = document.createElement("precio")

    val textoNombreP1: Text = document.createTextNode("Agua")
    val textoPrecioP1: Text = document.createTextNode("1.50")

    // Unimos el textNode al elemento correspondiente
    nombreP1.appendChild(textoNombreP1)
    precioP1.appendChild(textoPrecioP1)

    // Unimos el nombre y el precio al producto
    producto1.appendChild(nombreP1)
    producto1.appendChild(precioP1)

    // Añadiriamos todos los nodos que quisieramos

    // TODO: REALIZAR LA OTRA RAMA


    // 4º Procedemos a crear el XML
    // -> ¿Qué queremos escribir?
    val source: Source = DOMSource(document)

    // -> Qué clase usamos para escribir: StreamResult(File)
    val result: StreamResult = StreamResult(Path.of("src/main/resources/ficherosXML/productosWrite.xml").toFile())

    // -> Qué herramienta usamos para realizar la transformación: Transformer
    val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

    // BONUS POINT
    // Para indentar el XML correctamente
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")

    // POR ULTIMO. Realizamos la transformación
    transformer.transform(source, result)

}