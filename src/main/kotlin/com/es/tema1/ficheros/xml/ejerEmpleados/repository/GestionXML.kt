package com.es.tema1.ficheros.xml.ejerEmpleados.repository

import com.es.tema1.ficheros.xml.ejerEmpleados.model.Empleado
import org.w3c.dom.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import kotlin.io.path.notExists


class GestionXML {

    private fun createFichero(rutaFichero: Path) {
        // Si rutaFichero no existe, creo el sistema de archivos hasta el mismo
        if (rutaFichero.notExists()) {
            Files.createDirectories(rutaFichero.parent)
            Files.createFile(rutaFichero)
        }
    }


    fun writeXMLEmpleados(claves: List<String>, empleados: Map<String, Empleado>, rutaXML: Path) {

        // Aseguramos que la ruta está creada
        createFichero(rutaXML)

        val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        val imp: DOMImplementation = builder.domImplementation

        val document: Document = imp.createDocument(null, "empleados", null)

        empleados.forEach { key, empleado ->

            try {
                // Creo el elemento
                val elementEmpleado = document.createElement("empleado")

                // Uno el atributo al elemento empleado
                elementEmpleado.setAttribute(claves[0].lowercase(), empleado.id)

                // Uno el elemento empleado al nodo raiz
                document.documentElement.appendChild(elementEmpleado)

                // Creo los tres elementos del XML
                val elementApellido = document.createElement(claves[1].lowercase())
                val elementDpto = document.createElement(claves[2].lowercase())
                val elementSalario = document.createElement(claves[3].lowercase())

                // Creo los text nodes
                val textNodeApellido = document.createTextNode(empleado.apellido)
                val textNodeDpto = document.createTextNode(empleado.departamento)
                val textNodeSalario = document.createTextNode(empleado.salario.toString())

                // Uno los textNodes al elemento empleado
                elementApellido.appendChild(textNodeApellido)
                elementDpto.appendChild(textNodeDpto)
                elementSalario.appendChild(textNodeSalario)

                // Uno los elementos apellido, dpto y salario al elementoEmpleado
                elementEmpleado.appendChild(elementApellido)
                elementEmpleado.appendChild(elementDpto)
                elementEmpleado.appendChild(elementSalario)


            } catch (e: Exception) {
                throw Exception("Error al formar el XML")
            }

            // Escribo el XML
            transformXML(document, rutaXML)
        }

    }


    fun updateXML(id: String, newSalary: String, rutaXML: Path) {

        // Aseguro que el fichero este creado
        createFichero(rutaXML)

        // Compruebo la validez de los datos
        if (id.isNullOrEmpty() || newSalary.isNullOrBlank()) {
            throw Exception("Datos incorrectos")
        }

        val document = getParsedXML(rutaXML)
        val root: Element = document.documentElement
        root.normalize()

        val listaNodos: NodeList = root.getElementsByTagName("empleado")

        var modified = false

        for (i in 0..<listaNodos.length) {

            val nodo: Node = listaNodos.item(i)

            if (nodo.nodeType == Node.ELEMENT_NODE) {

                val nodoEmpleado = nodo as Element
                if (nodoEmpleado.hasAttributes()) {
                    if (nodoEmpleado.getAttribute("id") == id) {
                        val elementoSalario = nodoEmpleado.getElementsByTagName("salario").item(0)

                        elementoSalario.textContent = newSalary
                        modified = !modified
                        break
                    }
                }
            }
        }
        if (modified) {
            transformXML(document, rutaXML)
        } else {
            throw Exception("XML no modificado")
        }
    }

    fun leerXML(rutaXML: Path): List<Empleado> {
        // Aseguro que el fichero este creado
        createFichero(rutaXML)

        // Creo la lista a devolver
        val listaEmpleados = mutableListOf<Empleado>()

        val document = getParsedXML(rutaXML)
        val root: Element = document.documentElement
        root.normalize()

        val listaNodos: NodeList = root.getElementsByTagName("empleado")

        for (i in 0..<listaNodos.length){

            val nodo:Node = listaNodos.item(i)
            if(nodo.nodeType == Node.ELEMENT_NODE) {
                try {
                    val nodoElemento = nodo as Element
                    var id: String = ""
                    if(nodoElemento.hasAttributes()) {
                        id = nodoElemento.getAttribute("id")
                    }
                    val elementoApellido = nodoElemento.getElementsByTagName("apellido")
                    val elementoDpto = nodoElemento.getElementsByTagName("departamento")
                    val elementoSalario = nodoElemento.getElementsByTagName("salario")

                    val apellido = elementoApellido.item(0).textContent
                    val dpto = elementoDpto.item(0).textContent
                    val salario = elementoSalario.item(0).textContent.toDouble()

                    listaEmpleados.add(Empleado(id, apellido, dpto, salario))
                } catch (e: Exception) {
                    throw Exception("Error al leer el XML")
                }
            }

        }
        return listaEmpleados.toList()
    }

    private fun transformXML(document: Document, rutaXML: Path) {
        val source: Source = DOMSource(document)
        val result: StreamResult = StreamResult(rutaXML.toFile())
        val xslt: Source = StreamSource(Path.of("src/main/resources/ficherosXML/datosEmpleados/Style.xsl").toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer(xslt)

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty(OutputKeys.METHOD, "xml")
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")

        transformer.transform(source, result)
    }

    private fun getParsedXML(rutaXML: Path): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        return db.parse(rutaXML.toFile())
    }
}
