package com.utn.entities;

import com.utn.enums.Estado;
import com.utn.enums.FormaPago;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Pedido extends Base implements Calculable {

    @Builder.Default
    private Estado estado = Estado.PENDIENTE;
    @Builder.Default
    @Setter(AccessLevel.NONE)
    private Double total = 0.0;
    private LocalDate fecha;
    private FormaPago formaPago;
    private final Set<DetallePedido> detalles = new HashSet<DetallePedido>();

    public abstract static class PedidoBuilder<C extends Pedido, B extends PedidoBuilder<C, B>>
            extends Base.BaseBuilder<C, B> {

        public B total(Double ignored) {
            return self();
        }

        public B estado(Estado ignored) {
            return self();
        }
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        if (this.detalles.add(nuevoDetalle)) {
            calcularTotal();
        } else {
            System.out.println(
                    "No se puede agregar un detalle de pedido para el producto " + producto.getNombre() +
                    " porque ya existe un detalle del producto referido en este mismo pedido.");
        }
    }

    public Optional<DetallePedido> findDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(d -> d.getProducto().equals(producto))
                .findFirst();
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        findDetallePedidoByProducto(producto)
                .ifPresent(detalle -> {
                    detalles.remove(detalle);
                    calcularTotal();
                });
    }

    @Override
    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }
}