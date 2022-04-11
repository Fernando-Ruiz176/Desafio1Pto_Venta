package PuntoVenta;
  
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
 
public class PuntoVenta { 
	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private static ArrayList<Venta> ventas       = new ArrayList<Venta>();
	 
	public final static int OPCION_MENU_SALIR               = 7;
	public final static int OPCION_MENU_VENTAS        		= 6;
	public final static int OPCION_MENU_CARRO        		= 5;
	public final static int OPCION_MENU_ELIMINAR    		= 4;
	public final static int OPCION_MENU_ACTUALIZAR    		= 3;
	public final static int OPCION_MENU_CREAR 	     		= 2;
	public final static int OPCION_MENU_PRODUCTOS    		= 1;
	
	public static void main(String[] args) {
		init();
		int opcionSeleccionada;
		do {
			opcionSeleccionada = menu();
			switch (opcionSeleccionada) {
				
				case OPCION_MENU_PRODUCTOS:
					verProductos();
					break;
					
				case OPCION_MENU_CREAR:
					crearNuevoProducto();
					break;
					
				case OPCION_MENU_ACTUALIZAR:
					actualizarProductos();
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
		
		crearArchivoCsv();
		generarContenidoArchivoCSV();
	}
	
	private static void init() {
		productos.add(new Producto("CH1", "Chicle Menta          ", 200));
		productos.add(new Producto("CH2", "Chicle Frutilla       ", 250));
		productos.add(new Producto("BC1", "Coca Cola 500ml       ",	800));
		productos.add(new Producto("BC2", "Coca Cola 350ml       ", 620));
		productos.add(new Producto("SO1", "Super Ocho 29gr       ", 270));
		productos.add(new Producto("SO2", "Super Ocho c/mani 29gr", 450));
		productos.add(new Producto("GT1", "Galleta triton 126gr  ", 670));
		productos.add(new Producto("NE1", "Chocolate Negrita 30gr", 480));
		System.out.println("El listado de productos iniciales se ha cargado correctamente.\n");
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
	
	private static void crearNuevoProducto() {
		Scanner scanner = new Scanner(System.in);
		
		try {
		System.out.println("Por favor ingrese un codigo para su producto: ");
		String codigoProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un nombre para su producto:");
		String nombreProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un precio para su producto:");
		int precioProducto = scanner.nextInt();
		
		Producto productoNuevo = new Producto(codigoProducto, nombreProducto, precioProducto);
		productos.add( productoNuevo );
		System.out.println("El nuevo producto ha sido creado\n ");
		} catch (InputMismatchException ime){
			System.out.println("Debe ingresar los datos solicitados.");
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
	
	private static void crearArchivoCsv() {
	String nombreArchivo               =             "reporte-Inventario.csv";
	String contenidoArchivoReporte     =             generarContenidoArchivoCSV();
	
		try {
			FileWriter writer = new FileWriter (nombreArchivo);
			writer.write( contenidoArchivoReporte);
			writer.close();
		
		}	catch (IOException ioe) {
			System.out.println("Hubo un problema al escribir el reporte.");
			System.out.println("Contacte con el administrador");
		}
	
	}
	
	private static String generarContenidoArchivoCSV() {
		
		String cabeceraCSV               =      "Codigo, Nombre, Precio";
		String contenidoArchivo          =      cabeceraCSV + "\n";
		
		contenidoArchivo += "CH1, Chicle Menta, 200\n";
		contenidoArchivo += "CH2, Chicle Frutilla, 250\n";
		contenidoArchivo += "BC1, Coca Cola 500ml,	800\n";
		contenidoArchivo += "BC2, Coca Cola 350ml, 620\n";
		contenidoArchivo += "SO1, Super Ocho 29gr, 270\n";
		contenidoArchivo += "SO2, Super Ocho c/mani 29gr, 450\n";
		contenidoArchivo += "GT1, Galleta triton 126gr, 670\n";
		contenidoArchivo += "NE1, Chocolate Negrita 30gr, 480\n";
		
		return contenidoArchivo;
		
	}
	
	private static int menu() {
		System.out.println("MENU PUNTO DE VENTA\n==========================\n");
		System.out.println("1. Ver Productos");
		System.out.println("2. Crear Nuevo Producto");
		System.out.println("3. Actualizar Productos");
		System.out.println("4. Eliminar Producto");
		System.out.println("5. Agregar Productos al Carro");
		System.out.println("6. Reporte de Ventas");
		System.out.println("7. SALIR");
		
		System.out.println("\nPor favor digite la opcion deseada: ");
		Scanner scanner = new Scanner(System.in);
		try {
			int opcionSeleccionada = scanner.nextInt();
			return opcionSeleccionada;
		} catch (InputMismatchException ime) {
			System.out.println("Debe ingresar los datos solicitados!!!");
		}
		return 0;
	}

}