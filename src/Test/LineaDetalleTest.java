package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import PuntoVenta.LineaDetalle;
import PuntoVenta.Producto;


class LineaDetalleTest {

	
	@Test
	void test() {
		Producto producto1 = new Producto();
		producto1.getPrecio();
		producto1.setPrecio(100);
		
		Producto producto2 = new Producto();
		producto2.getCodigo();
		producto2.getNombre();
		producto2.setPrecio(500);
		
		Producto producto3 = new Producto();
		producto3.setPrecio(800);
		
		
		LineaDetalle Id = new LineaDetalle();
		Id.getCantidad();
		Id.setCantidad(2);
		Id.setProducto(producto1);
		
		assertEquals(200, Id.calcularSubtotal() );
		
		LineaDetalle Id2 = new LineaDetalle();
		Id2.getProducto();
		Id2.setCantidad(1);
		Id2.setProducto(producto2);
		
		assertEquals(500, Id2.calcularSubtotal() );
		
		LineaDetalle Id3 = new LineaDetalle();
		Id3.setCantidad(3);
		Id3.setProducto(producto3);
		
		assertEquals(2400, Id3.calcularSubtotal() );
		
		
		Producto producto4 = new Producto();
		producto4.setPrecio(650);
		
		LineaDetalle Id4 = new LineaDetalle();
		Id4.setCantidad(2);
		Id4.setProducto(producto4);
		
		assertEquals(1300, Id4.calcularSubtotal() );
		
		
		
		
		Producto producto5 = new Producto();
		producto5.setPrecio(940);
		
		LineaDetalle Id5 = new LineaDetalle();
		Id5.setCantidad(3);
		Id5.setProducto(producto5);
		
		assertEquals(2820, Id5.calcularSubtotal() );
		
		
		Producto producto6 = new Producto();
		producto6.setPrecio(1500);
		
		LineaDetalle Id6 = new LineaDetalle();
		Id6.setCantidad(4);
		Id6.setProducto(producto6);
		
		assertEquals(6000, Id6.calcularSubtotal() );
		
		
		Producto producto7 = new Producto();
		producto7.setPrecio(910);
		
		LineaDetalle Id7 = new LineaDetalle();
		Id7.setCantidad(2);
		Id7.setProducto(producto7);
		
		assertEquals(1820, Id7.calcularSubtotal() );
		
		
		Producto producto8 = new Producto();
		producto8.setPrecio(1040);
		
		LineaDetalle Id8 = new LineaDetalle();
		Id8.setCantidad(3);
		Id8.setProducto(producto8);
		
		assertEquals(3120, Id8.calcularSubtotal() );
		
		
		
		
	}

}
