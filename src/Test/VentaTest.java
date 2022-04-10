package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import PuntoVenta.LineaDetalle;
import PuntoVenta.Producto;
import PuntoVenta.Venta;


class VentaTest {

	

	@Test
	void test() {
		
		Producto producto1 = new Producto("aaa", "AAA", 100);
		Producto producto2 = new Producto("bbb", "BBB", 200);
		
		LineaDetalle Id1 = new LineaDetalle(1, producto1);
		LineaDetalle Id2 = new LineaDetalle(2, producto2);
		
		
		Venta venta = new Venta();
		venta.agregarLineaDetalle(Id1);
		venta.agregarLineaDetalle(Id2);
		
		
		assertEquals(500, venta.calcularTotal());
		
		
		LineaDetalle Id3 = new LineaDetalle(10, producto1);
		
		Venta venta2 = new Venta();
		venta2.getLineaDetalle();
		venta2.getFecha();
		venta2.agregarLineaDetalle(Id3);
		
		
		assertEquals(1000, venta2.calcularTotal());
		
		
		
		LineaDetalle Id4 = new LineaDetalle(14, producto1);
		
		Venta venta3 = new Venta();
		venta3.getLineaDetalle();
		venta3.getFecha();
		venta3.agregarLineaDetalle(Id4);
		
		assertEquals(1400, venta3.calcularTotal());
		
		
		
		LineaDetalle Id5 = new LineaDetalle(8, producto1);
		
		Venta venta4 = new Venta();
		venta4.getLineaDetalle();
		venta4.getFecha();
		venta4.agregarLineaDetalle(Id5);
		
		assertEquals(800, venta4.calcularTotal());
	}

}
