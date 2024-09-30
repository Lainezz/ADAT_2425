# Acceso a Elementos con Atributos en XML utilizando DOM

### Introducción

Para acceder a todos los elementos que tienen atributos en un documento XML usando **DOM**, se puede iterar sobre los elementos del documento y verificar si tienen atributos asociados. En XML, cualquier elemento puede tener uno o más atributos, y en DOM, los atributos de un elemento pueden ser accedidos y gestionados utilizando métodos como `hasAttributes()` y `getAttributeNames()`.

## Ejemplo de Código en Kotlin para Obtener Elementos con Atributos

Aquí te muestro cómo puedes acceder a todos los elementos que tienen atributos en un documento XML utilizando **DOM** en Kotlin:

```kotlin
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import java.io.File

fun buscarElementosConAtributos(file: File) {
    try {
        // Inicializamos el parser DOM
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(file)
        
        // Normalizamos el documento
        doc.documentElement.normalize()

        // Obtenemos todos los nodos del documento
        val nodeList = doc.getElementsByTagName("*") // El "*" selecciona todos los elementos

        // Iteramos sobre los nodos
        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node.nodeType == Element.ELEMENT_NODE) {
                val element = node as Element
                
                // Verificamos si el elemento tiene algún atributo
                if (element.hasAttributes()) {
                    println("Elemento: ${element.tagName}")
                    val attributes = element.attributes
                    for (j in 0 until attributes.length) {
                        val attr = attributes.item(j)
                        println(" - Atributo: ${attr.nodeName} = ${attr.nodeValue}")
                    }
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
```

### Explicación del Código:

1. **getElementsByTagName("*")**: Usamos `"*"` como parámetro en `getElementsByTagName()` para obtener todos los elementos del documento XML. Esto selecciona cualquier elemento dentro del documento.

2. **hasAttributes()**: Para cada nodo, verificamos si el elemento tiene atributos usando el método `hasAttributes()`. Si es verdadero, significa que ese elemento tiene uno o más atributos asociados.

3. **attributes**: El objeto `attributes` contiene todos los atributos del elemento. Iteramos sobre ellos y mostramos su nombre (`nodeName`) y su valor (`nodeValue`).

## Ejecución:

Si tienes un archivo XML como el siguiente:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Empleados>
  <empleado id="1">
    <apellido>Martinez</apellido>
    <dep>10</dep>
    <salario>1000.33</salario>
  </empleado>
  <empleado id="2" tipo="temporal">
    <apellido>Garcia</apellido>
    <dep>11</dep>
    <salario>2000.34</salario>
  </empleado>
</Empleados>
```

Y ejecutas la función de la siguiente manera:

```kotlin
val file = File("ruta/a/tu/archivo/empleados.xml")
buscarElementosConAtributos(file)
```

La salida sería algo como:

```
Elemento: empleado
 - Atributo: id = 1
Elemento: empleado
 - Atributo: id = 2
 - Atributo: tipo = temporal
```

## Explicación Adicional:

- **Recorrer Atributos:** Usamos el objeto `attributes`, que contiene una lista de todos los atributos asociados al elemento, y para cada atributo, mostramos su nombre y valor.
- **Uso de `getElementsByTagName("*")`:** Esto permite seleccionar todos los elementos en el documento, independientemente del nombre de la etiqueta. Luego, podemos filtrar aquellos que tienen atributos.

## Alternativa:

Si solo te interesan los elementos con un atributo específico (por ejemplo, los elementos que tienen el atributo `id`), podrías usar el método `getAttribute()` y comprobar si no es nulo o vacío:

```kotlin
val id = element.getAttribute("id")
if (id.isNotEmpty()) {
    println("Elemento con id: ${element.tagName}, id = $id")
}
```

## Conclusión:

Este enfoque te permite recorrer todo el documento XML y encontrar los elementos que tienen atributos, además de mostrar esos atributos con sus respectivos valores. El uso de DOM es adecuado para este tipo de manipulación, especialmente si el archivo XML no es excesivamente grande.
