# Trabajo Práctico 8: Jakarta Persistence API

<img src="LogoUTN.png" width="60" align="left" />

**Universidad Tecnológica Nacional<br>
Tecnicatura Universitaria en Programación<br>
Programación III**

Estudiante: Pedro Hidalgo - pedro.hidalgo@tupad.utn.edu.ar <br>
Trabajo Práctico 8 de Programación III.

Este proyecto consiste en una refactorización del sistema desarrollado en los Trabajos Prácticos anteriores, implementando JPA e Hibernate.

---
## Links:

Repositorio en GitHub: https://github.com/phidalg/JPA

## Programación funcional:

Se refactorizó el método calcularTotal() de la clase Pedido, utilizando stream() para obtener el valor del subtotal de cada elemento del conjunto (Set) de DetallePedido y calcular el total mediante su suma.

Además, se implementó en el método main una rutina que procesa colecciones utilizando stream(), mostrando los resultados por consola.

## Lombok:

Este proyecto utiliza la librería Lombok para reducir el código repetitivo en las entidades mediante el uso de anotaciones, mejorando la legibilidad y mantenibilidad del código.

## Identidad de las entidades:

Cada entidad define su identidad a través de equals() y hashCode() según el criterio que tiene sentido semántico en el dominio, no necesariamente por el ID  autogenerado.

Categoria y Producto se identifican por nombre. En un catálogo, el nombre funciona como identificador natural de negocio: no tiene sentido que existan dos categorías "Bebidas" o dos productos "Coca-Cola 500ml". Usar el nombre facilita además la búsqueda en colecciones sin necesidad de conocer el ID del producto.

DetallePedido se identifica por su producto. La razón es estructural: dentro de un pedido no puede haber dos líneas para el mismo producto, si se quisieran agregar más unidades, se debería incrementar la cantidad del detalle existente. Al estar almacenados en un HashSet<DetallePedido> dentro de Pedido, esta definición de igualdad garantiza la inexistencia de duplicados.

Pedido y Usuario se identifican por id. A diferencia de los casos anteriores, ninguno tiene un atributo de negocio que garantice unicidad: dos pedidos pueden tener exactamente los mismos productos y el mismo total, y dos usuarios pueden compartir nombre y apellido. El id autogenerado por Base es la única garantía de distinción, ya que representa una transacción o una persona irrepetibles aunque todos sus otros atributos coincidan.
