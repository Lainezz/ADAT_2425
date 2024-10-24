package com.es.tema2.orm.proyectoStockControl.service

import com.es.tema2.orm.proyectoStockControl.model.RespuestaHTTP
import com.es.tema2.orm.proyectoStockControl.model.User


class UserService {
    fun login(userInput: String?, passInput: String?): RespuestaHTTP<User> {
        return RespuestaHTTP(200, "Mensaje", User())
    }
}