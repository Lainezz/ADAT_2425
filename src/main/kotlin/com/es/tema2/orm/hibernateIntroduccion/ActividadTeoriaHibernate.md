
# **Actividad: Investigación sobre Mapeo Objeto-Relacional y Herramientas ORM**

### Objetivo:
El objetivo de esta actividad es que los alumnos investiguen y comprendan los conceptos fundamentales del mapeo objeto-relacional (ORM), el desfase objeto-relacional, las herramientas ORM más utilizadas y su funcionalidad. Asimismo, deberán explorar las clases y métodos que nos proporciona el framework Hibernate.

### Instrucciones:
Deberás investigar y explicar cada uno de los siguientes puntos en un documento que entregarás al final de la actividad. Asegúrate de proporcionar ejemplos prácticos cuando sea necesario y utiliza tus propias palabras para describir los conceptos. Puedes utilizar libros, tutoriales en línea y la documentación oficial para completar la actividad.

### Puntos a desarrollar:

---

## **Parte 1: Conceptos de ORM y desfase objeto-relacional**

1. **¿Qué es el desfase objeto-relacional?**  
   Investiga y explica con tus palabras qué es el desfase objeto-relacional. Describe algunos de los problemas que surgen cuando se intenta trabajar con objetos de programación y bases de datos relacionales.

2. **¿Qué es el mapeo objeto-relacional (ORM)?**  
   Define qué es el mapeo objeto-relacional y por qué es útil en aplicaciones que utilizan bases de datos relacionales.

3. **Ventajas del uso de herramientas ORM**  
   Investiga las ventajas más importantes que ofrecen las herramientas ORM para el desarrollo de aplicaciones. Explica cómo estas ventajas mejoran la productividad y el mantenimiento del código.

4. **¿Qué es JPA (Java Persistence API)?**
   Investiga y explica qué es JPA. Describe cómo JPA define un estándar para la persistencia de objetos en bases de datos y qué ventajas ofrece respecto a escribir consultas SQL manualmente. Además, menciona cómo se relaciona con frameworks como Hibernate.

---

## **Parte 2: Herramientas ORM más utilizadas**

Investiga y realiza una breve descripción de las siguientes herramientas ORM, explicando en qué lenguajes o plataformas se utilizan, cuáles son sus características principales y cómo se diferencian entre sí.

1. **Hibernate**  
   a) ¿Qué es Hibernate?  
   b) ¿Qué características lo hacen destacar como herramienta ORM?

2. **iBatis**  
   a) ¿Qué es iBatis?  
   b) ¿Cómo se diferencia de Hibernate?

3. **TopLink**  
   a) ¿Qué es TopLink?  
   b) ¿Cuáles son sus principales características?

4. **Django**  
   a) ¿Qué es Django?  
   b) ¿Qué ventajas ofrece su ORM integrado?

---

## **Parte 3: Clases y métodos en Hibernate**

Investiga las siguientes clases y métodos proporcionados por Hibernate y describe en qué consisten, qué operaciones permiten realizar y su importancia dentro de la arquitectura ORM.

### a) **Clase `EntityManager`**
- ¿Qué permite hacer esta clase?
- Investiga y explica el funcionamiento de los siguientes métodos:
    1. `.persist()`
    2. `.find()`
    3. `.merge()`
    4. `.remove()`
    5. `.createQuery()`
    6. `.getTransaction()`
    7. `.flush()`
    8. `.refresh()`
    9. `.detach()`
    10. `.clear()`
    11. `.close()`

### b) **Interfaz `EntityManagerFactory`**
- ¿Qué es la interfaz `EntityManagerFactory`?
- ¿Cuál es su rol dentro de la arquitectura de JPA/Hibernate?

### c) **Interfaz `Persistence`**
- Investiga qué es la interfaz `Persistence` y cómo se relaciona con `EntityManagerFactory`.

### d) **Interfaz `Query`**
- ¿Qué permite hacer la interfaz `Query`?
- ¿Qué tipos de consultas permite ejecutar y cómo se configuran los parámetros en una consulta?

### e) **Interfaz `Transaction`**
- ¿Qué es una transacción en Hibernate?
- ¿Qué permite hacer la interfaz `Transaction`?
- Explica cómo se utilizan los métodos para iniciar, confirmar (commit) y deshacer (rollback) una transacción.

---

### **Formato de entrega:**
- El trabajo se realizará por parejas
- El documento debe tener una portada con el nombre completo de cada integrante del grupo, fecha y título de la actividad.
- Explica cada punto con claridad y usando un lenguaje correcto.
- Utiliza ejemplos prácticos de código donde sea necesario para explicar las clases y métodos.
- El trabajo debe realizarse en un documento de texto (Word o PDF).

### **Criterios de evaluación:**
- Claridad y profundidad de las explicaciones.
- Uso de ejemplos prácticos de código.
- Calidad de la investigación y recursos utilizados.
- Organización y presentación del trabajo.

---

Con esta actividad, deberás ser capaz de entender los conceptos fundamentales de ORM y la relación entre objetos de programación y bases de datos. Además, tendrás una visión detallada de cómo utilizar algunas de las herramientas más importantes en el ámbito del desarrollo de software.
