package com.es.tema1.ficheros.xml.ejerCRUD.repository

import com.es.tema1.ficheros.xml.ejerCRUD.model.Empleado
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import kotlin.io.path.notExists

class XMLRepository {

    /*
    AQUÍ ES DONDE VAMOS A IMPLEMENTAR LOS MÉTODOS CRUD
     */

    // C -> Create / Insertar un nuevo empleado
    fun insert(newEmpleado: Empleado, rutaXML: Path) {

        if(rutaXML.notExists()) {
            throw Exception("Ruta XML No existe")
        }

        if (newEmpleado.id.isBlank()
            || newEmpleado.apellido.isBlank()
            || newEmpleado.dpto.isBlank()) {
            throw Exception("Atributos incorrectos")
        }

        // "Parseo" el documento
        val document = parseXML(rutaXML)
        val root: Element = document.documentElement
        root.normalize()

        // Insertar el Empleado
        val elementoNuevoEmpleado = document.createElement("empleado")
        elementoNuevoEmpleado.setAttribute("id", newEmpleado.id)

        root.appendChild(elementoNuevoEmpleado)

        val elementoApellido = document.createElement("apellido")
        val elementoDpto = document.createElement("dpto")
        val elementoSalario = document.createElement("salario")

        val textNodeApellido = document.createTextNode(newEmpleado.apellido)
        val textNodeDpto = document.createTextNode(newEmpleado.dpto)
        val textNodeSalario = document.createTextNode(newEmpleado.salario.toString())

        elementoApellido.appendChild(textNodeApellido)
        elementoDpto.appendChild(textNodeDpto)
        elementoSalario.appendChild(textNodeSalario)

        elementoNuevoEmpleado.appendChild(elementoApellido)
        elementoNuevoEmpleado.appendChild(elementoDpto)
        elementoNuevoEmpleado.appendChild(elementoSalario)


        // transformacion del arbol a XML
        val source: Source = DOMSource(document)
        val result: StreamResult = StreamResult(rutaXML.toFile())
        val xslt: Source = StreamSource(Path.of("src/main/resources/ficherosXML/datosEmpleados/Style.xsl").toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer(xslt)

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty(OutputKeys.METHOD, "xml")
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")

        transformer.transform(source, result)


    }

    private fun parseXML(rutaXML: Path): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        return db.parse(rutaXML.toFile())
    }


}