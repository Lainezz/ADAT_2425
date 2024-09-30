
# Lectura y Escritura de Ficheros XML con DOM en Kotlin

En esta lección profundizaremos en la lectura y escritura de ficheros XML utilizando el modelo DOM (Document Object Model) en Kotlin. DOM es una interfaz que permite a los programadores manipular documentos XML como una estructura en forma de árbol, lo que ofrece flexibilidad para realizar operaciones complejas sobre los nodos del documento.

XML (eXtensible Markup Language) es un metalenguaje que permite estructurar información de forma jerárquica mediante etiquetas. Es utilizado ampliamente para la transmisión y almacenamiento de datos debido a su simplicidad y portabilidad.

## Conceptos Clave de DOM

**DOM** almacena todo el documento XML en memoria en forma de árbol, donde cada nodo representa una parte del documento (elementos, atributos, texto, etc.). Las clases principales en DOM son:

- **Document:** Representa el documento XML completo. Es el punto de entrada para manipular los nodos.
- **Element:** Cada etiqueta en el documento XML corresponde a un objeto `Element`.
- **Node:** Es la clase base de la que derivan todos los nodos (Element, Attr, Text).
- **NodeList:** Es una lista que contiene nodos. Generalmente se utiliza para acceder a elementos hijos de un nodo.
- **Attr:** Representa un atributo de un nodo.
- **Text:** Contiene el texto de un nodo.

## Paquetes Necesarios en Kotlin

Para trabajar con DOM en Kotlin, necesitaremos los siguientes paquetes:

```kotlin
import org.w3c.dom.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import java.io.File
```

## Lectura de un Fichero XML

Vamos a empezar con la lectura de un fichero XML. Supongamos que tenemos un archivo llamado `empleados.xml` con la siguiente estructura:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Empleados>
  <empleado id="1">
    <apellido>Martinez</apellido>
    <dep>10</dep>
    <salario>1000.33</salario>
  </empleado>
  <empleado id="2">
    <apellido>Garcia</apellido>
    <dep>11</dep>
    <salario>2000.34</salario>
  </empleado>
</Empleados>
```

### Código en Kotlin para Leer el Archivo XML

A continuación se muestra cómo podemos leer este archivo XML usando DOM en Kotlin:

```kotlin
import org.w3c.dom.*
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

fun leerXml(file: File) {
    try {
        // Inicializamos el parser DOM
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(file)
        
        // Normalizamos el documento (elimina nodos vacíos o inconsistencias en el formato)
        doc.documentElement.normalize()

        // Obtenemos todos los nodos "empleado"
        val nodeList = doc.getElementsByTagName("empleado")
        println("Número de empleados: ${nodeList.length}")

        // Iteramos sobre cada nodo "empleado"
        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val element = node as Element
                // Accedemos a los atributos y elementos hijo
                println("Empleado ID: ${element.getAttribute("id")}")
                println("Apellido: ${element.getElementsByTagName("apellido").item(0).textContent}")
                println("Departamento: ${element.getElementsByTagName("dep").item(0).textContent}")
                println("Salario: ${element.getElementsByTagName("salario").item(0).textContent}")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
```

#### Explicación:
- **DocumentBuilderFactory** y **DocumentBuilder** son las clases que nos permiten crear un objeto `Document` a partir de un archivo XML.
- **normalize()** asegura que el documento XML esté en un estado consistente, eliminando nodos vacíos o redundantes.
- **getElementsByTagName()** se utiliza para obtener una lista de todos los nodos con el nombre de etiqueta proporcionado, en este caso, "empleado".
- **getAttribute()** y **getTextContent()** permiten acceder a los atributos y al contenido de texto de los nodos hijos.

## Escritura de un Fichero XML

Ahora veamos cómo generar un archivo XML utilizando DOM. Vamos a escribir un archivo XML con información sobre coches.

### Código en Kotlin para Escribir un Archivo XML

```kotlin
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File

fun escribirXml(file: File) {
    try {
        // Inicializamos el DocumentBuilder
        val docFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = docFactory.newDocumentBuilder()
        val doc = docBuilder.newDocument()

        // Creamos el elemento raíz
        val rootElement = doc.createElement("Concesionario")
        doc.appendChild(rootElement)

        // Creamos un nodo "coche" con sus hijos
        val coche = doc.createElement("Coche")
        rootElement.appendChild(coche)

        // Atributo ID para coche
        coche.setAttribute("id", "1")

        // Creamos los elementos del coche
        val marca = doc.createElement("Marca")
        marca.appendChild(doc.createTextNode("Renault"))
        coche.appendChild(marca)

        val modelo = doc.createElement("Modelo")
        modelo.appendChild(doc.createTextNode("Megane"))
        coche.appendChild(modelo)

        val cilindrada = doc.createElement("Cilindrada")
        cilindrada.appendChild(doc.createTextNode("1.5"))
        coche.appendChild(cilindrada)

        // Escribimos el contenido en el archivo
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val source = DOMSource(doc)
        val result = StreamResult(file)
        transformer.transform(source, result)

        println("Archivo XML generado exitosamente.")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
```

#### Explicación:
- Utilizamos **DocumentBuilderFactory** y **DocumentBuilder** para crear un documento XML en blanco.
- Los elementos y atributos se crean utilizando `createElement()` y `setAttribute()`, respectivamente.
- Finalmente, el archivo XML se escribe usando un **Transformer** que transforma el documento DOM en un archivo físico.

## Ejercicios Propuestos

1. **Lectura Básica:** Modifica el ejemplo de lectura para agregar más validaciones de contenido. Por ejemplo, si el salario de un empleado es mayor de 1500, imprime un mensaje personalizado.

2. **Escritura Dinámica:** Escribe un programa que pida al usuario información sobre varios coches (marca, modelo, cilindrada) y genere un archivo XML dinámicamente con esa información.

3. **Validación de Atributos:** Añade validaciones a los atributos del XML (por ejemplo, si un atributo "id" está vacío, que se asigne un valor predeterminado antes de mostrar la información).

## Práctica Compleja

Crea un programa en Kotlin que realice las siguientes tareas:
1. Lea los datos de empleados desde un archivo de texto (con campos como ID, apellido, departamento, salario) utilizando lectura de caracteres.
2. Genere un archivo XML con los datos de los empleados utilizando DOM.
3. Implemente una función adicional que modifique un nodo específico en el XML (por ejemplo, cambiar el salario de un empleado basado en su ID).
4. Lea el archivo XML resultante y muestre la información en la consola.

Este ejercicio integra todo lo aprendido sobre la lectura y escritura de ficheros XML, manejo de atributos y validaciones, además de incluir el uso de ficheros de texto.
