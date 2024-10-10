package com.es.tema1.ficheros.xml.ejerEmpleados

import com.es.tema1.ficheros.xml.ejerEmpleados.model.Empleado
import com.es.tema1.ficheros.xml.ejerEmpleados.repository.GestionFichero
import com.es.tema1.ficheros.xml.ejerEmpleados.repository.GestionXML
import java.nio.file.Path


fun main() {

    try {
        val ficheroCSV = Path.of("src").resolve("main/resources/ficherosXML/datosEmpleados/empleados.csv")
        val ficheroXML = Path.of("src").resolve("main/resources/ficherosXML/datosEmpleados/empleados.xml")


        val gestionFichero = GestionFichero()
        val x: Pair<List<String>, Map<String, Empleado>> = gestionFichero.readFichero(ficheroCSV)


        val gestionXML = GestionXML()

        gestionXML.writeXMLEmpleados(x.first, x.second, ficheroXML)


        gestionXML.updateXML("2", "5000", ficheroXML)

        val lista = gestionXML.leerXML(ficheroXML)

        lista.forEach {
            println(it)
        }
    } catch (e: Exception) {
        println(e.message)
    }
}