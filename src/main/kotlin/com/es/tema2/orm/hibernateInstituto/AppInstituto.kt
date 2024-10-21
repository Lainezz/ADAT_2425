package com.es.tema2.orm.hibernateInstituto

import com.es.tema2.orm.hibernateInstituto.model.*
import com.es.tema2.orm.hibernateInstituto.utils.HibernateUtils
import jakarta.persistence.EntityManager

fun main() {


    val em: EntityManager = HibernateUtils.getEntityManager("unidadInstituto")

    val dptos = mutableListOf<Departamento>()
    dptos.add(Departamento(null, "Informatica", "Departamento de informatica", null))
    val inspectores = mutableListOf<Inspector>()
    inspectores.add(Inspector(null, "2345d", "pepe", null))

    val director: Director = Director(null, "123234z", "Paco", null)

    val proveedores = mutableListOf<Proveedor>()
    proveedores.add(Proveedor(null, "C1234", "Repuestos Suarez", null))

    val instituto: Instituto = Instituto(null, "Ies Rafael", "Call Amiel", 0, dptos, inspectores, director, proveedores)

    em.transaction.begin()

    em.persist(instituto)

    em.transaction.commit()

    HibernateUtils.closeEntityManager(em)


}