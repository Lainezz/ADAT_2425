package com.es.tema2.orm.hibernateInstituto.model

import jakarta.persistence.*

@Entity
@Table(name = "inspectores")
class Inspector(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "dni", unique = true, nullable = false)
    val dni: String,

    @Column(name = "nombre")
    val nombre: String,

    @OneToMany(cascade = [CascadeType.ALL])
    val instituto: Instituto?



) {
}