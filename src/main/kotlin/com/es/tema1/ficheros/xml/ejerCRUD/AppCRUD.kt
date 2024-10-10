package com.es.tema1.ficheros.xml.ejerCRUD

import com.es.tema1.ficheros.xml.ejerCRUD.model.Empleado
import com.es.tema1.ficheros.xml.ejerCRUD.repository.XMLRepository
import java.nio.file.Path

fun main() {

    val ficheroXML = Path.of("src")
        .resolve("main/resources/ficherosXML/datosEmpleados/empleados.xml")

    val e: Empleado = Empleado("16", "Linares", "IT", 5000.0)

    val repository: XMLRepository = XMLRepository()

    repository.insert(e, ficheroXML)


}