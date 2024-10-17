package com.es.tema2.orm.hibernateIntroduccion

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.EntityTransaction
import jakarta.persistence.Persistence
import java.time.Instant
import java.util.*

fun main() {

    // Cargar la configuración que queramos
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    // Abre la conexión con la base de datos -> em contiene la conexión
    var em: EntityManager = emf.createEntityManager()

    // Si queremos empezar una transaccion...
    em.transaction.begin()

    val fecha = Date.from(Instant.now())
    val dpto: Departamento = Departamento("IT", fecha)
    val e: Empleado = Empleado( "Pepe", 39, dpto)
    em.persist(e) // .persist() persiste el objeto en un PersistenceContext

    // Empujamos los cambios a la base de datos
    em.transaction.commit()

    // Cerrar la conexion a la BD
    em.close()


    em = emf.createEntityManager()






}