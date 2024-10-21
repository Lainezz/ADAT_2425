package com.es.tema2.orm.hibernateProducto

import jakarta.persistence.*

@Entity
@Table(name="proveedores")
class Proveedor (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val nombre: String,

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    val productos: List<Producto>?


) {
}