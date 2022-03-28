package PuntoVenta;

import java.util.ArrayList;
import java.util.Calendar;

public class Venta {

private Calendar fecha = Calendar.getInstance();
	
	public Venta(Calendar fecha, ArrayList<LineaDetalle> lineasDetalle) {
		this.fecha = fecha;
		this.lineasDetalle = lineasDetalle;
	}
	
	public Venta() {
		
	}


	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public ArrayList<LineaDetalle> getLineasDetalle() {
		return lineasDetalle;
	}

	public void setLineasDetalle(ArrayList<LineaDetalle> lineasDetalle) {
		this.lineasDetalle = lineasDetalle;
	}

	private ArrayList<LineaDetalle> lineasDetalle = new ArrayList();
	
	
	// Esta linea es nueva
	public void agregarLineaDetalle(LineaDetalle lineaDetalle) {
		// TODO Auto-generated method stub
		
	}
	
	
}
