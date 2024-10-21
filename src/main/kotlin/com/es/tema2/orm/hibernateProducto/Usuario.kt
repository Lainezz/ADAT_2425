package com.es.tema2.orm.hibernateProducto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "usuarios")
class Usuario (

    @Id
    val nombreUsuario: String,

    @Column(name = "password", nullable = false, length = 20)
    val password: String

) {
}