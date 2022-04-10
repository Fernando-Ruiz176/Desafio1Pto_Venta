package PuntoVenta;

import java.util.Scanner;
import java.util.ArrayList;

public class PuntoVenta {
	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private static ArrayList<Venta> ventas       = new ArrayList<Venta>();
	
	public final static int OPCION_MENU_SALIR               = 8;
	public final static int OPCION_MENU_VENTAS        		= 7;
	public final static int OPCION_MENU_CARRO        		= 6;
	public final static int OPCION_MENU_ELIMINAR    		= 5;
	public final static int OPCION_MENU_PRODUCTOS    		= 4;
	public final static int OPCION_MENU_ACTUALIZAR    		= 3;
	public final static int OPCION_MENU_CREAR 	     		= 2;
	public final static int OPCION_MENU_CARGA_PRODUCTOS     = 1;
	
	public static void main(String[] args) {
		int opcionSeleccionada;
		do {
			opcionSeleccionada = menu();
			switch (opcionSeleccionada) {
				case OPCION_MENU_CARGA_PRODUCTOS:
					cargarProductosIniciales();
					break;
			
				case OPCION_MENU_CREAR:
					crearProducto();
					break;
					
				case OPCION_MENU_ACTUALIZAR:
					actualizarProductos();
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
					
				case OPCION_MENU_VENTAS:
					reporteVentas();
					break;
					
			}
		} while ( opcionSeleccionada != OPCION_MENU_SALIR);
		System.out.printf("Selecciono la opcion %d", opcionSeleccionada);
	}
	
	private static void cargarProductosIniciales() {
		productos.add(new Producto("CH1", "Chicle Menta   ", 200));
		productos.add(new Producto("CH2", "Chicle Frutilla", 250));
		productos.add(new Producto("BC1", "Coca Cola 500ml", 800));
		productos.add(new Producto("BC2", "Coca Cola 350ml", 620));
		productos.add(new Producto("SO1", "Super Ocho 29gr", 270));
		System.out.println("El listado de productos iniciales se ha cargado.\n");
	}
	
	
	private static void crearProducto() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Por favor ingrese un codigo para su producto: ");
		String codigoProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un nombre para su producto:");
		String nombreProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un precio para su producto:");
		int precioProducto = scanner.nextInt();
		
		Producto productoNuevo = new Producto(codigoProducto, nombreProducto, precioProducto);
		productos.add( productoNuevo );
		System.out.println("El nuevo producto ha sido creado\n ");
	}

	private static void verProductos() {
		System.out.println("\nPRODUCTOS:\n==========================\n");
		for(Producto producto : productos ) {
			System.out.printf("Código: %s Producto:  %s  Precio: %d %n"
					, producto.getCodigo()
					, producto.getNombre()
					, producto.getPrecio()	
			);
		}
		System.out.println("");
	}
	
	private static Producto buscarProducto(String codigo) {
		for(Producto producto: productos) {
			if( producto.getCodigo().equalsIgnoreCase(codigo)) {
				return producto;
			}
		}
		return null;
	}
	
	private static void eliminarProducto() {
		System.out.println("Escriba el codigo del producto a eliminar:");
		Scanner scanner = new Scanner( System.in );
		String codigo = scanner.nextLine();
		for( int i = 0; i < productos.size(); i++ ) {
			Producto get = productos.get(i);
			
			if( codigo.equals( get.getCodigo()) ) {
				productos.remove( i );
				break;		
			}
		}
		System.out.println("El producto se ha eliminado correctamente.\n");
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
			venta.agregarLineaDetalle(lineaDetalle);
			
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
	
	private static void actualizarProductos() {
		boolean seguirModificandoProductos = true;
		
		do {
			System.out.println ("Ingrese el codigo del producto");
			Scanner scanner = new Scanner(System.in);
			String codigos = scanner.nextLine();
			Producto producto = buscarProducto( codigos );
			
			System.out.println("Por favor ingrese el nuevo nombre para su producto");
			String nombreProducto = scanner.nextLine();
			producto.setNombre( nombreProducto);
			
			System.out.println( "Por favor ingrese el nuevo precio para su producto");
			int precioProducto = scanner.nextInt();
			producto.setPrecio(precioProducto);
			
			System.out.println("Desea modificar mas productos: [si/no]");
			String seguirModificandoProductosStr = scanner.next();
			
			seguirModificandoProductos = seguirModificandoProductosStr.equalsIgnoreCase("SI")?true:false;
				
		} while(seguirModificandoProductos);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("¿Ha terminado con la modificacion? SI/NO");
		boolean productoActualizado = scanner.nextLine().equalsIgnoreCase("SI")?true:false;
		System.out.println("Producto se ha actualizado");
		
	}
	
	private static void reporteVentas() {
		System.out.println("\nVentas\n**************************");
		System.out.println("Fecha \t                         Monto");
		
		for(Venta venta: ventas) {
			System.out.printf("%td de %tB %tY, %tH:%tM hrs \t %d\n"
					, venta.getFecha()
					, venta.getFecha()
					, venta.getFecha()
					, venta.getFecha()
					, venta.getFecha()
					, venta.calcularTotal());	
		}
		System.out.printf("%n%n\n");
	}
	
	private static int menu() {
		System.out.println("MENU PUNTO DE VENTA\n==========================\n");
		System.out.println("1. Cargar productos iniciales");
		System.out.println("2. Crear Producto");
		System.out.println("3. Actualizar Productos");
		System.out.println("4. Ver Productos");
		System.out.println("5. Eliminar Producto");
		System.out.println("6. Agregar Productos al Carro");
		System.out.println("7. Reporte de Ventas");
		System.out.println("8. SALIR");
		
		System.out.println("\nPor favor digite la opcion deseada: ");
		Scanner scanner = new Scanner(System.in);
		int opcionSeleccionada = scanner.nextInt();
		return opcionSeleccionada;
	}

}