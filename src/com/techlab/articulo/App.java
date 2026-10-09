package com.techlab.articulo;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloBebible;
import com.techlab.articulo.model.ArticuloComestible;
import com.techlab.articulo.model.Categoria;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);

        ArrayList<Articulo> articulos = new ArrayList<>();
        
        ArrayList<Categoria> categorias = new ArrayList<>();
        cargarCategorias(categorias);

        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar artículo");
            System.out.println("2. Mostrar artículos");
            System.out.println("3. Consultar un artículo");
            System.out.println("4. Modificar un artículo");
            System.out.println("5. Eliminar un articulo");
            System.out.println("6. Mostrar categorías");
            System.out.println("0. Salir");
            opcion = leerEntero(scanner, "Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    insertarArticulo(scanner, articulos, categorias);    
                    break;
                case 2:
                    mostrarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos, categorias);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }

        }while(opcion != 0);

        scanner.close();
    }



    /*----- Método para insertar un nuevo artículo --------*/

    public static void  insertarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias){
        
        int tipoArticulo;
        do{
            System.out.println("Seleccione el tipo de artículo a agregar:");
            System.out.println("1. Artículo Bebible");
            System.out.println("2. Artículo Comestible");
            tipoArticulo = leerEntero(scanner, "Ingrese su opción: ");

            if(tipoArticulo != 1 && tipoArticulo != 2){
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                continue;
            }
        }while(tipoArticulo != 1 && tipoArticulo != 2);
        
        int codigo = leerEntero(scanner, "Ingrese el código del artículo: ");
        if (buscarArticuloPorCodigo(articulos, codigo) != null) {
            System.out.println("Ya existe un artículo con ese código. No se puede agregar.");
            return; 
        }

        String nombre= leerStringNoVacio(scanner, "Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del artículo: ");

        Categoria categoriaSeleccionada = null;
        do{
            System.out.println("Seleccione la categoría del artículo:");
            listarCategorias(categorias);

            int idCategoria = leerEntero(scanner, "Ingrese el ID de la categoría: ");
            categoriaSeleccionada = buscarCategoriaPorId(categorias, idCategoria);
            if (categoriaSeleccionada == null) {
                System.out.println("Categoría no encontrada. Por favor, seleccione una categoría válida.");
                continue;
            }
            break;
        } while (true);

        Articulo articulo;
        if (tipoArticulo == 1) {
            int cantidadMl = leerEntero(scanner, "Ingrese la cantidad en mililitros: ");
            articulo = new ArticuloBebible(codigo, nombre, precio, categoriaSeleccionada, cantidadMl);
        } else {
            int cantidadGr = leerEntero(scanner, "Ingrese la cantidad en gramos: ");
            articulo = new ArticuloComestible(codigo, nombre, precio, categoriaSeleccionada, cantidadGr);
        }
        articulos.add(articulo);
        System.out.println("Artículo agregado exitosamente: " + articulo);
    }



    /*----- Método para mostrar todos los artículos --------*/
    public static void mostrarArticulos(ArrayList<Articulo> articulos){
        if(articulos.isEmpty()){
            System.out.println("No hay artículos registrados.");
            return;
        }
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    /*----- Método para consultar un artículo --------*/
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos){
        int codigo = leerEntero(scanner, "Ingrese el código del artículo a consultar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) {
            System.out.println("Artículo encontrado: " + articulo);
        } else {
            System.out.println("No se encontró un artículo con ese código.");
        }
    }

    /*----- Método para buscar un artículo por código --------*/
    public static Articulo buscarArticuloPorCodigo(ArrayList<Articulo> articulos, int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

    /*----- Método para modificar un artículo --------*/
    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos, ArrayList<Categoria> categorias){
        int codigo = leerEntero(scanner, "Ingrese el código del artículo a modificar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) { 
            String nuevoNombre = leerStringNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");
            double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del artículo: ");
            articulo.setNombre(nuevoNombre);
            articulo.setPrecio(nuevoPrecio);

            Categoria categoriaSeleccionada = null;
            do{
                System.out.println("Seleccione la categoría del artículo:");
                listarCategorias(categorias);

                int idCategoria = leerEntero(scanner, "Ingrese el ID de la categoría: ");
                categoriaSeleccionada = buscarCategoriaPorId(categorias, idCategoria);
                if (categoriaSeleccionada == null) {
                    System.out.println("Categoría no encontrada. Por favor, seleccione una categoría válida.");
                    continue;
                }
                break;
            } while (true);

            articulo.setCatergoria(categoriaSeleccionada);

            if (articulo instanceof ArticuloBebible) {
                int nuevaCantidadMl = leerEntero(scanner, "Ingrese la nueva cantidad en mililitros: ");
                ((ArticuloBebible) articulo).setCantidadMl(nuevaCantidadMl);
            } else if (articulo instanceof ArticuloComestible) {
                int nuevaCantidadGr = leerEntero(scanner, "Ingrese la nueva cantidad en gramos: ");
                ((ArticuloComestible) articulo).setCantidadGr(nuevaCantidadGr);
            }

            System.out.println("Artículo modificado exitosamente: " + articulo);
        } else {
            System.out.println("No se encontró un artículo con ese código.");
        }
    }

    /*----- Método para eliminar un artículo --------*/
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos){
        int codigo = leerEntero(scanner, "Ingrese el código del artículo a eliminar: ");
        Articulo articulo = buscarArticuloPorCodigo(articulos, codigo);
        if (articulo != null) {
            articulos.remove(articulo);
            System.out.println("Artículo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un artículo con ese código.");
        }
    }

    /*----- Método para cargar Categorias--------*/
    public static void cargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Bebidas", "Artículos líquidos para consumo"));
        categorias.add(new Categoria(2, "Alimentos", "Artículos comestibles"));
        categorias.add(new Categoria(3, "Electrónica", "Artículos electrónicos y gadgets"));
        categorias.add(new Categoria(4, "Ropa", "Artículos de vestimenta y accesorios"));
        categorias.add(new Categoria(5, "Hogar", "Artículos para el hogar y decoración"));
    }
    
    /*----- Método para listar las categorías --------*/
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    
    public static Categoria buscarCategoriaPorId(ArrayList<Categoria> categorias, int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try{
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public static String leerStringNoVacio(Scanner scanner, String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Entrada inválida. Por favor, ingrese un valor no vacío.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());
                
                if (valor < 0) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número no negativo.");
                    continue;
                }

                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }


}

