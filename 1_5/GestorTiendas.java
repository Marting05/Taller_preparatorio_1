import java.util.Random;

public class GestorTiendas {

    public Producto[][] llenarTienda(Producto[][] tienda, String[] nombresInventario) {
        int contador = 0;
        Random random = new Random();
        for (int i = 0; i < tienda.length; i++) {
            for (int j = 0; j < tienda[i].length; j++) {
                Producto p = new Producto();
                p.setNombre(nombresInventario[contador]);
                p.setPrecio(random.nextInt(30000) + 20000);
                p.setStock(random.nextInt(50) + 1);
                tienda[i][j] = p;
                contador++;
            }
        }
        return tienda;
    }

    public void mostrarTienda(String titulo, Producto[][] tienda) {
        System.out.println("=== " + titulo + " ===");
        for (int i = 0; i < tienda.length; i++) {
            for (int j = 0; j < tienda[i].length; j++) {
                System.out.printf("[%d][%d] %s%n", i, j, tienda[i][j]);
            }
        }
    }

    public void mostrarResultado(Producto[] resultado) {
        System.out.println("=== Inventario Fusionado ===");
        for (int i = 0; i < resultado.length; i++) {
            System.out.printf("[%d] %s%n", i, resultado[i]);
        }
    }

    private int buscarPorNombre(Producto[] resultado, int cantidadUsada, String nombre) {
        for (int i = 0; i < cantidadUsada; i++) {
            if (resultado[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Producto[] fusionarTiendas(Producto[][] tienda1, Producto[][] tienda2) {
        int capacidadMaxima = (tienda1.length * tienda1[0].length)
                + (tienda2.length * tienda2[0].length);
        Producto[] resultado = new Producto[capacidadMaxima];
        int cantidadUsada = 0;

        // Primero copiamos todos los productos de la tienda1 tal cual
        for (int i = 0; i < tienda1.length; i++) {
            for (int j = 0; j < tienda1[i].length; j++) {
                Producto actual = tienda1[i][j];
                Producto nuevo = new Producto(actual.getNombre(), actual.getPrecio(), actual.getStock());
                resultado[cantidadUsada] = nuevo;
                cantidadUsada++;
            }
        }

        // Ahora recorremos la tienda2: si el producto ya existe, sumamos stock;
        // si no existe, lo agregamos como nuevo al resultado.
        for (int i = 0; i < tienda2.length; i++) {
            for (int j = 0; j < tienda2[i].length; j++) {
                Producto actual = tienda2[i][j];
                int indiceExistente = buscarPorNombre(resultado, cantidadUsada, actual.getNombre());

                if (indiceExistente != -1) {
                    // Producto identico: sumamos el stock
                    Producto existente = resultado[indiceExistente];
                    existente.setStock(existente.getStock() + actual.getStock());
                } else {
                    // Producto nuevo: lo agregamos al resultado
                    Producto nuevo = new Producto(actual.getNombre(), actual.getPrecio(), actual.getStock());
                    resultado[cantidadUsada] = nuevo;
                    cantidadUsada++;
                }
            }
        }

        // Recortamos el arreglo al tamaño realmente usado (sin huecos vacios al final)
        Producto[] resultadoFinal = new Producto[cantidadUsada];
        for (int i = 0; i < cantidadUsada; i++) {
            resultadoFinal[i] = resultado[i];
        }
        return resultadoFinal;
    }
}
