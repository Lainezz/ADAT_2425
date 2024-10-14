
# JPA en Java

### ¿Qué es JPA en Java?

**JPA (Java Persistence API)** es una especificación de Java para la gestión de datos relacionales en aplicaciones Java a través de ORM (Object-Relational Mapping). Permite que los desarrolladores manejen datos persistentes en bases de datos relacionales utilizando objetos Java, sin necesidad de escribir código SQL manualmente.

JPA no es una implementación, es solo una especificación que define cómo debería comportarse un sistema de persistencia en Java. Para utilizar JPA, es necesario usar una implementación que siga esta especificación. Algunas implementaciones de JPA son **Hibernate**, **EclipseLink** y **OpenJPA**.

### Diferencia entre JPA y Hibernate

**JPA** es solo una especificación, mientras que **Hibernate** es una de las implementaciones más populares de dicha especificación. Hibernate proporciona una implementación concreta de las interfaces definidas en JPA y extiende sus capacidades con características adicionales.

- **JPA**: Solo define el conjunto de reglas y API que los proveedores deben seguir.
- **Hibernate**: Es una implementación concreta de JPA que además incluye varias características avanzadas como:
    - Caché de segundo nivel.
    - Soporte para diferentes dialectos de bases de datos.
    - Carga perezosa (lazy loading) avanzada.
    - Varias estrategias de herencia en modelos de objetos.

En resumen, JPA es la **API estándar**, mientras que Hibernate es una **herramienta específica** que implementa JPA (y añade características adicionales).

### Diferencias entre `Session` y `EntityManager`

1. **`Session` (de Hibernate)**:
    - Es la clase central en Hibernate para interactuar con la base de datos.
    - Maneja el ciclo de vida de las entidades y las operaciones CRUD.
    - `Session` pertenece a Hibernate, y por tanto, no es parte de la especificación JPA.
    - Tiene métodos específicos de Hibernate, lo que ofrece más funcionalidades que las definidas por JPA.

2. **`EntityManager` (de JPA)**:
    - Es el API estándar de JPA para realizar operaciones CRUD en entidades.
    - Proporciona una abstracción independiente de la implementación, lo que significa que al usar `EntityManager`, el código es portable entre diferentes proveedores de JPA.
    - `EntityManager` es parte de la especificación JPA y no ofrece tantas funcionalidades fuera del estándar, lo que lo hace menos específico pero más portable.

### Comparación resumida:

- **Origen**:
    - `Session` es propio de Hibernate.
    - `EntityManager` es parte de JPA y más portable.

- **Funciones**:
    - `Session` ofrece características avanzadas adicionales de Hibernate, como estrategias de caché más específicas o gestión de proxies.
    - `EntityManager` se ajusta a la API estándar de JPA, lo que asegura portabilidad entre distintas implementaciones de JPA.

- **Portabilidad**:
    - El uso de `Session` hace que el código esté fuertemente acoplado a Hibernate.
    - El uso de `EntityManager` permite que el código sea portable entre distintas implementaciones de JPA.

En resumen, **si necesitas flexibilidad y portabilidad**, es preferible usar `EntityManager`. Pero si **trabajas específicamente con Hibernate y necesitas funcionalidades avanzadas**, `Session` es más adecuado.
