package com.es.tema1.ficheros.xml.ejerLecturaNoticias.repository

import com.es.tema1.ficheros.xml.ejerLecturaNoticias.classes.Noticia
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class NotasPrensaRepository private constructor(val rutaFichero: Path) { // Private constructor para poder implementar el Patrón singleton


    companion object {
        @Volatile // This annotation ensures that the instance variable is visible to all threads and prevents caching issues when accessed concurrently.
        private var instance: NotasPrensaRepository? = null // Variable que guarda la instancia de clase

        fun getInstance(rutaFichero: Path) = // Método getInstance que obtiene la instancia creada de NotasPrensaRepository
            instance ?: synchronized(this) { // Elvis operator. Si instance no es null, devuelve instance. Si instance es null, ejecuta el bloque synchronized
                instance ?: NotasPrensaRepository(rutaFichero).also { // Double check. Comprueba de nuevo si instance es null. Si instance no es null, lo devuelve. Si instance es null, instancia la clase NotasPrensaRepository
                    instance = it // Bloque adicional para asignar la instanciación creada a la variable instance
                }
            }
    }


    /**
     * Lectura del XML para obtener una lista de Noticias (Items)
     */
    fun getItems(): List<Noticia> {

        // Creo la lista que voy a devolver al final
        val listaItems = mutableListOf<Noticia>()
        // Creo también el formatter para la fecha
        val formatter = DateTimeFormatter.ofPattern("EEE, d MMM uuuu HH:mm:ss Z").withLocale(Locale.ENGLISH)

        // Instancio la clase DocumentBuilderFactory
        val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        // Instancio la clase DocumentBuilder
        val db: DocumentBuilder = dbf.newDocumentBuilder()
        // Con la instancia del DocumentBuilder podemos proceder a "parsear" el fichero
        val document: Document = db.parse(rutaFichero.toFile())

        // Normalizo el arbol DOM para eliminar nodos vacíos o duplicados.
        document.documentElement.normalize()

        val root = document.documentElement

        // Me piden todos los items, así que puedo buscar la etiqueta item
        val nodeListItems = root.getElementsByTagName("item")

        // Recorro la lista para obtener los elementos item
        for (i in 0 until nodeListItems.length) {

            val nodo = nodeListItems.item(i)
            if(nodo.nodeType == Node.ELEMENT_NODE) {

                val item = nodo as Element

                var title = item.getElementsByTagName("title").item(0)
                val link = item.getElementsByTagName("link").item(0)
                val guid = item.getElementsByTagName("guid").item(0)
                val pubDateSt = item.getElementsByTagName("pubDate").item(0)
                val pubDate = OffsetDateTime.parse(pubDateSt.textContent.trim(), formatter)
                var description = item.getElementsByTagName("description").item(0)


                listaItems.add(Noticia(title.textContent.trim(), link.textContent.trim(), guid.textContent.trim(), pubDate, description.textContent.trim()))

            }

        }
        return listaItems.toList()
    }

}