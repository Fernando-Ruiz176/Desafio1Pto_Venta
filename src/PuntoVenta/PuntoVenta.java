
package PuntoVenta;

import java.util.Scanner;
import java.util.ArrayList;

public class PuntoVenta {
	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private static ArrayList<Venta> ventas = new ArrayList<Venta>();
	
	public final static int OPCION_MENU_SALIR        = 6;
	public final static int OPCION_MENU_PAGAR        = 5;
	public final static int OPCION_MENU_CARRO        = 4;
	public final static int OPCION_MENU_ELIMINAR     = 3;
	public final static int OPCION_MENU_PRODUCTOS    = 2;
	public final static int OPCION_MENU_CREAR 	     = 1;
	
	
	
//Quede en la pagina 224////Quede en la pagina 224////Quede en la pagina 224//
	
	

	public static void main(String[] args) {
		int opcionSeleccionada;
		do {
			opcionSeleccionada = menu();
			switch (opcionSeleccionada) {
				case OPCION_MENU_CREAR:
					crearProducto();
					break;
					
				case OPCION_MENU_PRODUCTOS:
					verProductos();
					break;
					
				case OPCION_MENU_ELIMINAR:
					eliminarProducto();
					break;
					
				case OPCION_MENU_CARRO:
					agregarProductosAlCarro();
					break;
					
				case OPCION_MENU_PAGAR:
					pagar();
					break;
			
			}
		} while ( opcionSeleccionada != OPCION_MENU_SALIR);
		System.out.printf("Selecciono la opcion %d", opcionSeleccionada);
	}
	
	private static void reporteVentas() {
		System.out.println("Ventas\n*****************\n");
		System.out.println("Fecha \t Monto");
		for(Venta venta: ventas) {
			System.out.printf("&s \t %d", venta.getFecha(), venta.calcularTotal());
		}
		System.out.printf("%n%n");
	}
	
	private static void crearProducto() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Por favor ingrese un codigo para su producto: ");
		String codigoProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un nombre para su producto: ");
		String nombreProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un precio para su producto: ");
		int precioProducto = scanner.nextInt();
		
		Producto productoNuevo = new Producto(codigoProducto, nombreProducto, precioProducto);
		productos.add( productoNuevo );
	}

	private static void verProductos() {
		System.out.println("\n==========Productos:\n============\n");
		
		for(Producto producto : productos ) {
			System.out.printf("Código: %s Producto: %s Precio: %d %n"
					, producto.getCodigo()
					, producto.getNombre()
					, producto.getPrecio()	
			);
		}
		
		System.out.println("");
	}

	private static void eliminarProducto() {
		System.out.println("Escriba el codigo del producto a eliminar:");
		Scanner scanner = new Scanner( System.in );
		String codigo = scanner.nextLine();
		
		for( Producto pto: productos ) {
			if( pto.getCodigo().equalsIgnoreCase( codigo) ) {
				productos.remove( pto );
				System.out.printf("Se ha eliminado correctamente el producto: %s", pto.getNombre());		
			} else {
				System.out.printf("No se encontro ningun producto con el codigo %s %n%n", codigo);
			}
		}
	}
	
	private static Producto buscarProducto(String codigo) {
		for(Producto producto: productos) {
			if( producto.getCodigo().equalsIgnoreCase(codigo)) {
				return producto;
			}
		}
		return null;
	}
	
	private static void agregarProductosAlCarro() {	
		Venta venta = new Venta();
		boolean seguirAgregandoProductos = true;
		do {
			verProductos();
			System.out.println("Escriba el codigo del producto que desea comprar: ");
			Scanner scanner = new Scanner(System.in);
			String codigo = scanner.next();
			Producto producto = buscarProducto(codigo);
		
			System.out.println("Escriba la cantidad que desea comprar: ");
			int cantidad = scanner.nextInt();
			
			LineaDetalle lineaDetalle = new LineaDetalle(cantidad, producto);
			venta.agregarLineasDetalle(lineaDetalle);
			
			System.out.println("Desea agregar mas productos al carro: [si/no]");
			String seguirAgregandoProductosStr =scanner.next();
			seguirAgregandoProductos = seguirAgregandoProductosStr.equalsIgnoreCase("SI")? true:false;
		} while ( seguirAgregandoProductos );
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("¿La venta fue pagada? [si/no]");
		boolean ventaPagada = scanner.nextLine().equalsIgnoreCase("SI")?true:false;
		
		if( ventaPagada ) {
			ventas.add( venta );
		}
		
	}
	
	private static void pagar() {	
	}

	private static int menu() {
		System.out.println("MENU PUNTO DE VENTA\n==========================\n");
		System.out.println("1. Crear Producto");
		System.out.println("2. Ver Productos");
		System.out.println("3. Eliminar Producto");
		System.out.println("4. Agregar Productos al Carro");
		System.out.println("5. Pagar");
		System.out.println("6. SALIR");
		
		System.out.println("\nPor favor digite la opcion deseada: ");
		Scanner scanner = new Scanner(System.in);
		int opcionSeleccionada = scanner.nextInt();
		return opcionSeleccionada;
	}

}