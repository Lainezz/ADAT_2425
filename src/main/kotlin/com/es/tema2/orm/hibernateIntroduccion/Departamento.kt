package com.es.tema2.orm.hibernateIntroduccion

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name="departamentos")
class Departamento(

    @Column(name="nombre", nullable = false, unique = true)
    val nombre: String,

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    val fechaCreacion: Date,

    @OneToOne(mappedBy = "dpto", cascade = [CascadeType.ALL])
    val empleado: Empleado? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val numDpto: Long? = null

) {


}