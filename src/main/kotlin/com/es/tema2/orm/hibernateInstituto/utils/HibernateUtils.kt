package com.es.tema2.orm.hibernateInstituto.utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {

    private lateinit var emf: EntityManagerFactory

    private fun getEntityManagerFactory(namePersistenceUnit: String = ""): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf;
        } else {
            Persistence.createEntityManagerFactory(namePersistenceUnit)
        }
    }

    fun getEntityManager(namePersistenceUnit: String = ""): EntityManager {
        return getEntityManagerFactory(namePersistenceUnit).createEntityManager()
    }


    // Método para cerrar todos los EntityManagerFactory
    fun shutdown() {
        if (emf.isOpen) {
            emf.close()
        }
    }

    // Método para cerrar un EntityManager específico
    fun closeEntityManager(em: EntityManager?) {
        try {
            if (em != null && em.isOpen) {
                em.close()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }



}