package com.utn.entities;

import com.utn.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    @ToString.Exclude
    private String contrasena;
    @ToString.Exclude
    private Rol rol;
    private final Set<Pedido> pedidos = new HashSet<Pedido>();

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

}
