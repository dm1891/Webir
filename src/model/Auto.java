package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Auto {
	
	private int id;
	private String titulo;
	private String categoria;
	private String urlimg;
	private int cantpasajeros;
	private int cantvalijas;
	private boolean transmision;
	private int cantpuertas;
	private boolean aire;
	private int cilindrada;
	private int preciobajo;
	private int precioalto;
	private int idempresa;
	private String nombreEmpresa;
	private String urlEmpresa;
	
	public Auto(int id, String titulo, String categoria, String urlimg, int cantpasajeros, int cantvalijas,
			boolean transmision, int cantpuertas, boolean aire, int cilindrada, int preciobajo, int precioalto,
			int idempresa, String nombreEmpresa, String urlEmpresa) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.urlimg = urlimg;
		this.cantpasajeros = cantpasajeros;
		this.cantvalijas = cantvalijas;
		this.transmision = transmision;
		this.cantpuertas = cantpuertas;
		this.aire = aire;
		this.cilindrada = cilindrada;
		this.preciobajo = preciobajo;
		this.precioalto = precioalto;
		this.idempresa = idempresa;
		this.nombreEmpresa = nombreEmpresa;
		this.urlEmpresa = urlEmpresa;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getUrlimg() {
		return urlimg;
	}
	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}
	
	public int getCantpasajeros() {
		return cantpasajeros;
	}
	public void setCantpasajeros(int cantpasajeros) {
		this.cantpasajeros = cantpasajeros;
	}
	
	public int getCantvalijas() {
		return cantvalijas;
	}
	public void setCantvalijas(int cantvalijas) {
		this.cantvalijas = cantvalijas;
	}
	
	public boolean getTransmision() {
		return transmision;
	}
	public void setTransmision(boolean transmision) {
		this.transmision = transmision;
	}
	
	public int getCantpuertas() {
		return cantpuertas;
	}
	public void setCantpuertas(int cantpuertas) {
		this.cantpuertas = cantpuertas;
	}
	
	public boolean getAire() {
		return aire;
	}
	public void setAire(boolean aire) {
		this.aire = aire;
	}
	
	public int getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	public int getPreciobajo() {
		return preciobajo;
	}
	public void setPreciobajo(int preciobajo) {
		this.preciobajo = preciobajo;
	}
	
	public int getPrecioalto() {
		return precioalto;
	}
	public void setPrecioalto(int precioalto) {
		this.precioalto = precioalto;
	}
	
	public int getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public String getUrlEmpresa() {
		return urlEmpresa;
	}
	public void setUrlEmpresa(String urlEmpresa) {
		this.urlEmpresa = urlEmpresa;
	}
	
	

}
