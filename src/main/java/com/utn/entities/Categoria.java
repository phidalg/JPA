package com.utn.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(
        callSuper = false,
        onlyExplicitlyIncluded = true)
public class Categoria extends Base{

    @EqualsAndHashCode.Include
    private String nombre;
    private String descripcion;
    private final Set<Producto> productos = new HashSet<Producto>();

    public boolean addProducto(Producto producto) {
        if(this.productos.add(producto)) {
            return true;
        } else {
            System.out.println("No se pudo agregar el producto " + producto.getNombre() + " porque ya existe en esta categoría.");
            return false;
        }
    }
}
