package com.es.tema2.orm.hibernateProducto

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

fun main() {

    // Cargar la configuración que queramos
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    // Abre la conexión con la base de datos -> em contiene la conexión
    var em: EntityManager = emf.createEntityManager()



}