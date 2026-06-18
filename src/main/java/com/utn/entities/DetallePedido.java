package com.utn.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(
        callSuper = false,
        onlyExplicitlyIncluded = false
)
public class DetallePedido extends Base {

    private int cantidad;
    private Double subtotal;
    @EqualsAndHashCode.Include
    private final Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        this.calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = producto.getPrecio() * this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

}
