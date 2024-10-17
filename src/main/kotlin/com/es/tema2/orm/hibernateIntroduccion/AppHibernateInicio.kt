package com.es.tema2.orm.hibernateIntroduccion

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

fun main() {

    // Cargar la configuración que queramos
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    val em: EntityManager = emf.createEntityManager()


}