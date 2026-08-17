public class FusionTiendas {
    public static void main(String[] args) {
        // Tienda 1: matriz de 2x3 = 6 productos
        Producto[][] tienda1 = new Producto[2][3];
        String[] nombresTienda1 = {
                "Arroz", "Aceite", "Azucar",
                "Sal", "Cafe", "Pasta"
        };

        // Tienda 2: matriz de 2x3 = 6 productos, con algunos nombres repetidos
        // a proposito ("Arroz", "Cafe") para demostrar la fusion.
        Producto[][] tienda2 = new Producto[2][3];
        String[] nombresTienda2 = {
                "Arroz", "Leche", "Huevos",
                "Cafe", "Harina", "Frijol"
        };

        GestorTiendas gestor = new GestorTiendas();
        gestor.llenarTienda(tienda1, nombresTienda1);
        gestor.llenarTienda(tienda2, nombresTienda2);

        gestor.mostrarTienda("Tienda 1", tienda1);
        gestor.mostrarTienda("Tienda 2", tienda2);

        Producto[] fusion = gestor.fusionarTiendas(tienda1, tienda2);

        System.out.println();
        gestor.mostrarResultado(fusion);
    }
}
