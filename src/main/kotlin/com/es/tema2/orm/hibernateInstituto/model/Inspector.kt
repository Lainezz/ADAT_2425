package com.es.tema2.orm.hibernateInstituto.model

import jakarta.persistence.*

@Entity
@Table(name = "inspectores")
class Inspector(

    @Column(name = "dni", unique = true, nullable = false)
    val dni: String,

    @Column(name = "nombre")
    val nombre: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_instituto")
    val institutos: Instituto?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,



) {
}