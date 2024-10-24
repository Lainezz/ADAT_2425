package com.es.tema2.orm.proyectoStockControl.service

import com.es.tema2.orm.proyectoStockControl.model.Producto
import com.es.tema2.orm.proyectoStockControl.model.RespuestaHTTP

class ProductoService {

     fun altaProducto(
        idProducto: String,
        nombreProducto: String,
        precioSinIva: String,
        descripcionProducto: String,
        nombreProveedor: String,
        direccionProveedor: String
    ): RespuestaHTTP<Producto>? {
        return null
    }

     fun bajaProducto(id: String): RespuestaHTTP<Producto>? {
        return null
    }

     fun modificarNombreProducto(id: String, nuevoNombre: String): RespuestaHTTP<Producto>? {
        return null
    }

     fun modificarStockProducto(id: String, nuevoStock: String): RespuestaHTTP<Producto>? {
        return null
    }

     fun getProducto(id: String): RespuestaHTTP<Producto>? {
        return null
    }

     fun getProductosConStock(): RespuestaHTTP<List<Producto>>? {
        return null
    }

     fun getProductosSinStock(): RespuestaHTTP<List<Producto>>? {
        return null
    }

}