package com.es.tema2.orm.proyectoStockControl.model

data class RespuestaHTTP<T>(var codigo: Int, var mensaje: String, var obj: T) {


}