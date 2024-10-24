package com.es.tema2.orm.hibernateProducto

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

fun main() {

    // Cargar la configuración que queramos
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    // Abre la conexión con la base de datos -> em contiene la conexión
    var em: EntityManager = emf.createEntityManager()

    // 1º Transaccion
    em.transaction.begin()

    // "Este objeto estaría "detached"
    var u: Usuario = Usuario("pepe123", "1234")

    // Dp de hacer el persist, ya estaría en el Persistence context
    em.persist(u)

    // Ya estaría en la base de datos
    em.transaction.commit()

    //
    em.transaction.begin()

    // Buscar
    var usuarioFromBD: Usuario = em.find(Usuario::class.java, "pepe123")

    // usuarioFromBD estaria en el persistence context
    usuarioFromBD.password = "345678"

    em.detach(usuarioFromBD)

    usuarioFromBD.password = "tfdveidheuhde"

    // commit
    em.transaction.commit()




    em.close()




}