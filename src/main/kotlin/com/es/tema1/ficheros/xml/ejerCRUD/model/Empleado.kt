package com.es.tema1.ficheros.xml.ejerCRUD.model

data class Empleado(val id:String, val apellido:String, val dpto: String, val salario: Double) {

    override fun toString(): String {
        return "ID: '$id', apellido='$apellido', dpto='$dpto', salario=$salario)"
    }
}