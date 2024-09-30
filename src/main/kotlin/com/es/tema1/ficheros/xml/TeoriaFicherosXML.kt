package com.es.tema1.ficheros.xml

import org.w3c.dom.*
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.*
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main() {

    // Usamos Paths para obtener la ruta del archivo
    val path = Path.of("src/main/resources/ficherosXML/productos.xml")
    // Ahora tenemos que conseguir... parsear ese documento y crear el ARBOL DOM:
    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    // Parseamos el archivo XML desde el Path
    val document = documentBuilder.parse(path.toFile())

    /*
    getDocumentElement() 	Accede al nodo raíz del documento
    normalize() 	        Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
     */
    document.documentElement.normalize()


    // Obtener el elemento raíz
    val root = document.documentElement
    val productos = root.getElementsByTagName("producto")

    for (i in 0..<productos.length) {
        val productoNode = productos.item(i)


        if(productoNode.nodeType == Node.ELEMENT_NODE) {
            val producto = productoNode as Element

            val nombre = producto.getElementsByTagName("nombre").item(0).textContent
            val precio = producto.getElementsByTagName("precio").item(0).textContent.toDouble()

            println("Nombre: $nombre, Precio: $precio")
        }

    }

    // Acceso a los atributos de los elementos
    for(i in 0..<productos.length){

        val node = productos.item(i)

        if (node.nodeType == Element.ELEMENT_NODE) {
            val element = node as Element

            // Verificamos si el elemento tiene algún atributo
            if (element.hasAttributes()) {
                println("Elemento: ${element.tagName}")
                val attributes = element.attributes
                for (j in 0..<attributes.length) {
                    val attr = attributes.item(j)
                    println(" - Atributo: ${attr.nodeName} = ${attr.nodeValue}")
                }
            }
        }
    }


    // ESCRIBIR XML
    /*
    En esta ocasión, vamos a escribir un documento XML.
     */

    //1º Instanciamos la clase DocumentBuilderFactory. También instanciamos/llamamos a los métodos necesarios
    //para poder controlar todos los métodos del dom
    val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    val builder: DocumentBuilder = factory.newDocumentBuilder()
    val imp: DOMImplementation = builder.domImplementation

    //En este punto, ya tendríamos todos los métodos y atributos de la implementacion de java para DOM
    //(entre ellos el parse, por ejemplo (que lo usamos anteriormente))
    //Nota: se ha hecho en tres pasos, pero la idea es la misma que hemos estado usando hasta ahora, antes lo hacíamos
    //tod0 en la misma línea.

    //2º Una vez tenemos la implementacion DOM, procedemos a crear un Document.
    //Recordamos que Document es el nodo padre, el primer nodo de todos.
    //Por ahora, ni namespaceURI ni doctype nos interesan
    val documentW: Document = imp.createDocument(null, "productos", null)

    //En este punto ya tendríamos el primer nodo creado. El nodo padre.
    //3º Ahora sólo tenemos que empezar a "meter" hijos a ese nodo padre

    //Inicio tag producto1
    val producto1: Element = documentW.createElement("producto")

    //Inicio tag nombre
    val nombreProd1: Element = documentW.createElement("nombre")
    val textoNombre: Text = documentW.createTextNode("Cereales")
    nombreProd1.appendChild(textoNombre)
    //Fin tag nombre
    //Inicio tag precio
    val precioProd1: Element = documentW.createElement("precio")
    val textoPrecio: Text = documentW.createTextNode("3.45")
    precioProd1.appendChild(textoPrecio)
    //Fin tag precio

    producto1.appendChild(nombreProd1)
    producto1.appendChild(precioProd1)

    documentW.documentElement.appendChild(producto1)
    //Fin tag producto1

    //Inicio tag producto2
    val producto2: Element = documentW.createElement("producto")

    //Inicio tag nombre
    val nombreProd2: Element = documentW.createElement("nombre")
    val textoNombre2: Text = documentW.createTextNode("Monster")
    nombreProd2.appendChild(textoNombre2)
    //Fin tag nombre
    //Inicio tag precio
    val precioProd2: Element = documentW.createElement("precio")
    val textoPrecio2: Text = documentW.createTextNode("1.45")
    precioProd2.appendChild(textoPrecio2)
    //Fin tag precio

    producto2.appendChild(nombreProd2)
    producto2.appendChild(precioProd2)

    documentW.documentElement.appendChild(producto2)
    //Fin tag producto2

    //... Añadimos todos los nodos que queramos, aplicando la dificultad y la profundidad que queramos

    //4º Finalmente, sólo queda escribir el fichero XML
    //Creamos la fuente XML a partir de nuestro nodo padre document
    //-> ¿Qué queremos escribir?
    val source: Source = DOMSource(documentW)
    //Creamos un "flujo" de escritura. Digo flujo para que se entienda.
    //-> ¿Dónde queremos escribir? ¿Cómo escribimos?
    val fichToWrite = Path.of("src/main/resources/ficherosXML/productosGenerados.xml")
    val result: Result = StreamResult(fichToWrite.toFile())
    //Obtenemos una instanciacion de la clase TransformerFactory
    // -> ¿Qué usamos para escribir?
    val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

    //Thanks to J.M.
    //Para indentar automáticamente nuestro XML, para que salga bonito
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")

    //Se realiza la "transformación" del documento a fichero
    // -> Realizamos la acción de escribir
    transformer.transform(source, result)






}