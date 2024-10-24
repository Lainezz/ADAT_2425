package com.es.tema2.orm.proyectoStockControl

import com.es.tema2.orm.proyectoStockControl.service.ProductoService
import com.es.tema2.orm.proyectoStockControl.service.ProveedorService
import com.es.tema2.orm.proyectoStockControl.service.UserService
import com.es.tema2.orm.proyectoStockControl.model.User

import java.util.Scanner

fun main() {

    /*
    Declaro aquí variables que voy a usar durante la ejecución del main
     */
    val scan = Scanner(System.`in`)
    var login = false  // Variable para comprobar si se hace un login correcto o no
    var user = User() // Variable para almacenar al usuario que se ha logado

    /*
    1A PARTE. LOGIN

    EN ESTA PARTE SE REALIZA UN LOGIN EN LA APLICACIÓN.
    SE PIDE EL USUARIO Y CONTRASENIA Y SE LLAMA AL METODO login DE LA CLASE UserController
     */
    do {
        // Ni caso a este try/catch
        try {
            Thread.sleep(500)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        println(
            """
            ******************************************************
            ****    Bienvenid@ a StockControl               ******
            ******************************************************
                            
            Introduzca su usuario y contrasena para continuar (0 para salir)
            """.trimIndent()
        )
        print("user: ")
        val userInput = scan.nextLine()

        if ("0".equals(userInput, ignoreCase = true)) {
            println("Saliendo...")
            System.exit(0)
        } else {
            print("password: ")
            val passwordInput = scan.nextLine()

            val pController = UserService()

            val respuestaHTTP = pController.login(userInput, passwordInput)

            try {
                if (respuestaHTTP.codigo == 200) {
                    respuestaHTTP.obj.let {
                        println("Bienvenid@")
                        user = it
                        login = true
                    } ?: run {
                        System.err.println("¡INTRODUCE EL OBJETO EN LA RESPUESTA HTTP DESDE EL CONTROLLER!")
                    }
                } else {
                    println("Error en el login\n\t-codigo ${respuestaHTTP.codigo}\n\t-${respuestaHTTP.mensaje}")
                }
            } catch (e: Exception) {
                println("Error controlado")
            }
        }
    } while (!login)

    /*
    2A PARTE. GESTION DE STOCK

    EN ESTA PARTE SE REALIZA UN CRUD DE GESTION DE STOCK
    1. Alta producto
    2. Baja producto
    3. Modificar nombre producto
    4. Modificar stock producto
    5. Get producto por id
    6. Get productos con stock
    7. Get productos sin stock
    8. Get proveedores de un producto
    9. Get todos los proveedores
     */
    var opc: String

    val proveedorService = ProveedorService()
    do {
        println(
            """
            ******************************************************
            ****            APP STOCK CONTROL               ******
            ******************************************************
                            
            1. Alta producto
            2. Baja producto
            3. Modificar nombre producto
            4. Modificar stock producto
            5. Get producto por id
            6. Get productos con stock
            7. Get productos sin stock
            8. Get proveedores de un producto
            9. Get todos los proveedores
            0. Salir
            """.trimIndent()
        )
        print("Seleccione una opción: ")
        opc = scan.nextLine()

        try {
            when (opc) {
                "1" -> altaProducto()
                "2" -> bajaProducto()
                "3" -> modificarNombreProducto()
                "4" -> modificarStockProducto()
                "5" -> getProductoPorId()
                "6" -> getProductosConStock()
                "7" -> getProductosSinStock()
                "8" -> getProveedoresDeUnProducto()
                "9" -> getTodosLosProveedores()
                "0" -> println("Saliendo...")
                else -> println("Error en la elección")
            }
        } catch (e: Exception) {
            println("ERROR CONTROLADO")
        }
    } while (opc != "0")
}

fun altaProducto() {
    val scan = Scanner(System.`in`)
    val ProductoService = ProductoService()

    println("1. Alta producto")

    println("DETALLES PRODUCTO")
    print("categoria: ")
    val categoriaProducto = scan.nextLine()
    print("nombre: ")
    val nombreProducto = scan.nextLine()
    print("precio sin IVA: ")
    val precioSinIva = scan.nextLine()
    print("descripcion: ")
    val descripcionProducto = scan.nextLine()

    println("DETALLES PROVEEDOR")
    print("nombre: ")
    val nombreProveedor = scan.nextLine()
    print("direccion: ")
    val direccionProveedor = scan.nextLine()

    val respuesta = ProductoService.altaProducto(
        categoriaProducto,
        nombreProducto,
        precioSinIva,
        descripcionProducto,
        nombreProveedor,
        direccionProveedor
    )

    if (respuesta != null && respuesta.codigo == 200) {
        println("PRODUCTO INSERTADO CORRECTAMENTE\n${respuesta.obj}")
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun bajaProducto() {
    val scan = Scanner(System.`in`)
    val ProductoService = ProductoService()
    println("2. Baja producto")

    print("Introduzca el id del producto: ")
    val idProducto = scan.nextLine()
    val respuesta = ProductoService.bajaProducto(idProducto)

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun modificarNombreProducto() {
    val scan = Scanner(System.`in`)
    val ProductoService = ProductoService()
    println("3. Modificar nombre producto")
    print("Introduzca el id del producto: ")
    val idProducto = scan.nextLine()
    print("Introduzca el nuevo nombre del producto: ")
    val nuevoNombre = scan.nextLine()
    val respuesta = ProductoService.modificarNombreProducto(idProducto, nuevoNombre)

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun modificarStockProducto() {
    val scan = Scanner(System.`in`)
    val ProductoService = ProductoService()
    println("4. Modificar stock producto")

    print("Introduzca el id del producto: ")
    val idProducto = scan.nextLine()
    print("Introduzca el nuevo stock: ")
    val nuevoStock = scan.nextLine()
    val respuesta = ProductoService.modificarStockProducto(idProducto, nuevoStock)

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun getProductoPorId() {
    val scan = Scanner(System.`in`)
    val ProductoService = ProductoService()

    println("5. Get producto por id")

    print("Introduzca el id del producto: ")
    val idProducto = scan.nextLine()
    val respuesta = ProductoService.getProducto(idProducto)

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
        println(respuesta.obj)
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun getProductosConStock() {
    val ProductoService = ProductoService()
    println("6. Get productos con stock")

    val respuesta = ProductoService.getProductosConStock()

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
        respuesta.obj.forEach { producto ->
            println(producto)
        }
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun getProductosSinStock() {
    val ProductoService = ProductoService()
    println("7. Get productos sin stock")

    val respuesta = ProductoService.getProductosSinStock()

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
        respuesta.obj.forEach { producto ->
            println(producto)
        }
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun getProveedoresDeUnProducto() {
    val scan = Scanner(System.`in`)
    val proveedorService = ProveedorService()
    println("8. Get proveedores de un producto")

    print("Introduzca el id del producto: ")
    val idProducto = scan.nextLine()
    val respuesta = proveedorService.getProveedoresProducto(idProducto)

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
        respuesta.obj.forEach { proveedor ->
            println(proveedor)
        }
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}

fun getTodosLosProveedores() {
    val proveedorService = ProveedorService()
    println("9. Get todos los proveedores")

    val respuesta = proveedorService.getTodosProveedores()

    if (respuesta != null && respuesta.codigo == 200) {
        println("OPERACION EXITOSA")
        respuesta.obj.forEach { proveedor ->
            println(proveedor)
        }
    } else {
        println("Error en la operacion\n\t-codigo ${respuesta?.codigo}\n\t-${respuesta?.mensaje}")
    }
}