package com.es.tema2.orm.hibernateInstituto.repository

import com.es.tema2.orm.hibernateInstituto.model.Departamento
import com.es.tema2.orm.hibernateInstituto.utils.HibernateUtils
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

class DepartamentoRepository {

    /*
    CRUD de Dpto
    Excepciones = ¿Se puede hacer rollback?
    EntityManagerFactory creandose muchas veces = ¿Cómo lo solucionaríais?
     */
    private fun getEntityManager() : EntityManager {
        return HibernateUtils.getEntityManager("unidadInstituto")
    }

    fun insertDpto(dpto: Departamento) {
        val em: EntityManager = getEntityManager()
        try {
            em.transaction.begin()

            em.persist(dpto)

            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
        } finally {
            em.close()
        }
    }




}