package com.es.tema2.orm.hibernateInstituto.model

import jakarta.persistence.*

@Entity
@Table(name = "directores")
class Director(

    @Column(name = "dni", nullable = false, length = 10, unique = false)
    val dni: String,

    @Column(name = "nombre", nullable = false)
    val nombre: String,

    @OneToOne(mappedBy = "director", cascade = [CascadeType.ALL])
    val instituto: Instituto?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?




) {
}