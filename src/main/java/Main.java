import com.utn.entities.*;
import com.utn.enums.FormaPago;
import com.utn.enums.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        final Set<Producto> productos = new HashSet<>();

        // Crear instancia de EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Creando 2 usuarios...");
        Usuario usuarioAdmin = Usuario.builder()
                .nombre("Pepe")
                .apellido("Fernandez")
                .mail("p.fernandez@mail.com")
                .celular("542944123321")
                .contrasena("abc123")
                .rol(Rol.ADMIN)
                .build();

        Usuario usuarioUser = Usuario.builder()
                .nombre("Juan")
                .apellido("Perez")
                .mail("jp@mail.com")
                .celular("543514123321")
                .contrasena("abc321")
                .rol(Rol.USUARIO)
                .build();

        System.out.println("Persistiendo los usuarios...");

        entityManager.getTransaction().begin();
        entityManager.persist(usuarioUser);
        entityManager.persist(usuarioAdmin);
        entityManager.getTransaction().commit();

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

        System.out.println("Creando 3 categorías...");

        Categoria electronica = Categoria.builder()
                .nombre("Electrónica")
                .descripcion("Productos electrónicos y accesorios tecnológicos")
                .build();

        Categoria alimentos = Categoria.builder()
                .nombre("Alimentos")
                .descripcion("Productos alimenticios y bebidas")
                .build();

        Categoria ropa = Categoria.builder()
                .nombre("Ropa")
                .descripcion("Prendas de vestir y accesorios")
                .build();

        System.out.println("Asociando productos a categorías...");

        electronica.addProducto(pendrive);
        electronica.addProducto(auriculares);
        electronica.addProducto(teclado);
        electronica.addProducto(cargador);

        alimentos.addProducto(alfajor);
        alimentos.addProducto(gaseosa);
        alimentos.addProducto(chicles);

        ropa.addProducto(remera);
        ropa.addProducto(buzo);
        ropa.addProducto(zapatillas);

        System.out.println("Persistiendo categorías y productos en la base de datos...");

        entityManager.getTransaction().begin();
        entityManager.persist(electronica);
        entityManager.persist(alimentos);
        entityManager.persist(ropa);
        entityManager.getTransaction().commit();

        System.out.println("Categorías y productos persistidos correctamente.\n");


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

        System.out.println("Persistiendo los pedidos...");

        entityManager.getTransaction().begin();
        entityManager.persist(pedido1);
        entityManager.persist(pedido2);
        entityManager.persist(pedido3);
        entityManager.getTransaction().commit();

        System.out.println("Actualizando el pedido 1, agregando un producto más...");

        entityManager.getTransaction().begin();
        pedido1.addDetallePedido(3, gaseosa);
        entityManager.merge(pedido1);
        entityManager.getTransaction().commit();

        System.out.println("Pedido 1 actualizado y persistido correctamente.\n");

        System.out.println("Actualizando el pedido 2, cambiando la cantidad de un producto...");

        entityManager.getTransaction().begin();
        pedido2.findDetallePedidoByProducto(remera).ifPresent(detalle -> detalle.setCantidad(10));
        entityManager.merge(pedido2);
        entityManager.getTransaction().commit();

        System.out.println("Pedido 2 actualizado y persistido correctamente.\n");

        System.out.println("Buscando usuario con id 1...");

        Usuario usuarioBuscado = entityManager.find(Usuario.class, 1L);

        if (usuarioBuscado != null) {
            System.out.println("Usuario encontrado: " + usuarioBuscado.getNombre() + " " + usuarioBuscado.getApellido());
        } else {
            System.out.println("Usuario con id 1 no encontrado.");
        }

        System.out.println("Buscando usuario por mail (p.fernandez@mail.com)...");

        usuarioBuscado = entityManager.find(Usuario.class, 2L);

        if (usuarioBuscado != null) {
            System.out.println("Usuario encontrado: " + usuarioBuscado.getNombre() + " " + usuarioBuscado.getApellido());
        } else {
            System.out.println("Mail no encontrado.");
        }

       System.out.println("Borrando el producto auriculares...");

       entityManager.getTransaction().begin();
       Producto pABorrar = entityManager.find(Producto.class, auriculares.getId());
       if (pABorrar != null) {
           pABorrar.getCategoria().getProductos().remove(pABorrar);
           entityManager.remove(pABorrar);
           System.out.println("Producto auriculares eliminado correctamente.");
       }
       entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();

    }
}