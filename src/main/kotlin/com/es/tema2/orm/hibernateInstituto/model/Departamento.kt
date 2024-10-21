package com.es.tema2.orm.hibernateInstituto.model

import jakarta.persistence.*

@Entity
@Table(name = "departamentos")
class Departamento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "nombre", nullable = false, length = 50)
    val nombre: String,

    @Column(name = "descripcion")
    val descripcion: String?,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_instituto")
    val instituto: Instituto?

) {
}