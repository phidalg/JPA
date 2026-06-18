import com.utn.entities.*;
import com.utn.enums.FormaPago;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        final Set<Producto> productos = new HashSet<>();

        System.out.println("Creando 10 productos...");

        Producto pendrive = Producto.builder()
                .nombre("Pendrive del Jefe de Gabinete")
                .precio(6800.00)
                .descripcion("Con Bitcoins de dudosa procedencia.")
                .stock(120)
                .imagen("pendrive.png")
                .disponible(true)
                .build();

        Producto auriculares = Producto.builder()
                .nombre("Auriculares Bluetooth")
                .precio(25999.99)
                .descripcion("Auriculares inalámbricos con cancelación de ruido.")
                .stock(4)
                .imagen("auriculares_bt.png")
                .disponible(false)
                .build();

        Producto teclado = Producto.builder()
                .nombre("Teclado Mecánico")
                .precio(18500.00)
                .descripcion("Teclado mecánico retroiluminado, disposición en español.")
                .stock(3)
                .imagen("teclado_mecanico.png")
                .disponible(true)
                .build();


        Producto cargador = Producto.builder()
                .nombre("Cargador Inalámbrico")
                .precio(9750.50)
                .descripcion("Cargador inalámbrico rápido, compatible con Qi.")
                .stock(0)
                .imagen("cargador.png")
                .disponible(false)
                .build();

        Producto alfajor = Producto.builder()
                .nombre("Alfajor")
                .precio(850.00)
                .descripcion("Alfajor de chocolate con tres capas de dulce de leche.")
                .stock(200)
                .imagen("alfajor.png")
                .disponible(true)
                .build();

        Producto gaseosa = Producto.builder()
                .nombre("Gaseosa Cola 500 ml")
                .precio(1200.00)
                .descripcion("Bebida gaseosa sabor cola, botella individual.")
                .stock(150)
                .imagen("gaseosa_cola.png")
                .disponible(true)
                .build();

        Producto chicles = Producto.builder()
                .nombre("Chicles Menta")
                .precio(350.00)
                .descripcion("Pastillas de goma menta, caja por 12 unidades.")
                .stock(300)
                .imagen("chicles_menta.png")
                .disponible(false)
                .build();

        Producto remera = Producto.builder()
                .nombre("Remera Básica Blanca")
                .precio(7200.00)
                .descripcion("Remera 100% algodón, talle M.")
                .stock(2)
                .imagen("remera_blanca.png")
                .disponible(true)
                .build();

        Producto buzo = Producto.builder()
                .nombre("Buzo Canguro Gris")
                .precio(15400.00)
                .descripcion("Buzo con capucha y bolsillo frontal, talle L.")
                .stock(45)
                .imagen("buzo_gris.png")
                .disponible(true)
                .build();

        Producto zapatillas = Producto.builder()
                .nombre("Zapatillas Running")
                .precio(42000.00)
                .descripcion("Zapatillas deportivas con suela amortiguada, número 42.")
                .stock(0)
                .imagen("zapatillas_running.png")
                .disponible(false)
                .build();

        productos.addAll(Set.of(
                pendrive,
                auriculares,
                teclado,
                cargador,
                alfajor,
                gaseosa,
                chicles,
                remera,
                buzo,
                zapatillas));

        System.out.println("Creando 3 pedidos...");

        Pedido pedido1 = Pedido.builder()
                .formaPago(FormaPago.TRANSFERENCIA)
                .build();

        Pedido pedido2 = Pedido.builder()
                .formaPago(FormaPago.EFECTIVO)
                .build();

        Pedido pedido3 = Pedido.builder()
                .formaPago(FormaPago.TARJETA)
                .build();

        System.out.println("Agregando detalles a los pedidos...");

        pedido1.addDetallePedido(1, pendrive);
        pedido1.addDetallePedido(2, alfajor);

        pedido2.addDetallePedido(5, remera);
        pedido2.addDetallePedido(1, buzo);
        pedido2.addDetallePedido(1, zapatillas);

        pedido3.addDetallePedido(10, chicles);
        pedido3.addDetallePedido(1, teclado);

        System.out.println("Imprimiendo por consola el listado de productos disponibles:\n");

        Map<Boolean, List<Producto>> particionSegunDisponibilidad = productos.stream()
                .collect(Collectors.partitioningBy(Producto::getDisponible));

        particionSegunDisponibilidad.get(true).forEach(System.out::println);

        System.out.println("\nImprimiendo por consola los detalles de un pedido:\n");

        pedido1.getDetalles().forEach(System.out::println);

        System.out.println("\nImprimiendo la cantidad de items del pedido anterior:\n");

        System.out.println(pedido1.getDetalles().stream().mapToInt(DetallePedido::getCantidad).sum());

        System.out.println("\nDetectando productos con stock menor a 5:\n");

        productos.stream()
                .filter((producto -> producto.getStock() < 5))
                .forEach(System.out::println);
    }
}