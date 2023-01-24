package com.paula.thenextbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="FacturaLibro")
public class FacturaLibro {
	
	private Integer idFactura;
	private Integer idLibro;
	
	
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public Integer getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}
	
	
	
}
