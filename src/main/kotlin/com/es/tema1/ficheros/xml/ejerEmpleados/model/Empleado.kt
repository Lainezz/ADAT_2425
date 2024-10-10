package com.es.tema1.ficheros.xml.ejerEmpleados.model

class Empleado(val id: String, val apellido: String, val departamento: String, val salario: Double) {

    override fun toString(): String {
        return "ID: '$id', apellido='$apellido', departamento='$departamento', salario=$salario"
    }
}