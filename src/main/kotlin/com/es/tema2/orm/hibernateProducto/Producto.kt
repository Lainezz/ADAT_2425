package com.es.tema2.orm.hibernateProducto

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "productos")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val nombre: String,

    val descripcion: String,

    @Column(nullable = false)
    val precio: Double,

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date,

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "id_producto")
    val proveedor: Proveedor?


) {
}