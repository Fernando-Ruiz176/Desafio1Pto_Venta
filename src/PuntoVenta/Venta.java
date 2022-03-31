package PuntoVenta;

import java.util.ArrayList;
//import java.util.Calendar;
import java.time.LocalDateTime;


public class Venta {

	private LocalDateTime fecha = LocalDateTime();
	private ArrayList<LineaDetalle> lineasDetalle = new ArrayList<>();


	public Venta() {
	
	}
	
	public Venta(LocalDateTime fecha, ArrayList<LineaDetalle> lineaDetalle) {
		this.fecha = fecha;
		this.lineasDetalle = lineaDetalle;
	}
	
	public void agregarLineaDetalle(lineaDetalle lineaDetalle) {
		this.lineasDetalle.add(lineaDetalle);
	}
	
		public int calcularTotal() {
			int total = 0
			for (LineaDetalle lineaDetalle : lineasDetalle) {
				total = total + lineaDetalle.calcularSubtotal();				
			}
			return total;
		}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public ArrayList<LineaDetalle> getLineaDetalle() {
		return lineasDetalle;
	}

	public void setLineaDetalle(ArrayList<LineaDetalle> lineaDetalle) {
		this.lineasDetalle = lineaDetalle;
	}

	private ArrayList<LineaDetalle> lineasDetalle = new ArrayList();
	
	
	// Esta linea tambien es nueva
	public void calcularTotal( calcularTotal calcularTotal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
