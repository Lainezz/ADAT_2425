package com.es.tema2.orm.hibernateInstituto.model

import jakarta.persistence.*

@Entity
@Table(name="proveedores")
data class Proveedor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name="cif", unique = true, nullable = false)
    val cif: String,

    @Column(nullable = false)
    val nombre: String,

    @ManyToMany(mappedBy = "proveedores", cascade = [CascadeType.ALL])
    val institutos: List<Instituto>?


) {

}