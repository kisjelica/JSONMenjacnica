package model;

import java.time.LocalDateTime;

public class Transakcija {
	private String izvornaValuta;
	private String krajnjaValuta;
	private double pocetniIznos;
	private double krajnjiIznos;
	private LocalDateTime datumTransakcije;
	
	public Transakcija() {
		// TODO Auto-generated constructor stub
	}

	public Transakcija(String izvornaValuta, String krajnjaValuta, double pocetniIznos, double krajnjiIznos,
			LocalDateTime datumTransakcije) {
		super();
		this.izvornaValuta = izvornaValuta;
		this.krajnjaValuta = krajnjaValuta;
		this.pocetniIznos = pocetniIznos;
		this.krajnjiIznos = krajnjiIznos;
		this.datumTransakcije = datumTransakcije;
	}

	public String getIzvornaValuta() {
		return izvornaValuta;
	}

	public void setIzvornaValuta(String izvornaValuta) {
		this.izvornaValuta = izvornaValuta;
	}

	public String getKrajnjaValuta() {
		return krajnjaValuta;
	}

	public void setKrajnjaValuta(String krajnjaValuta) {
		this.krajnjaValuta = krajnjaValuta;
	}

	public double getPocetniIznos() {
		return pocetniIznos;
	}

	public void setPocetniIznos(double pocetniIznos) {
		this.pocetniIznos = pocetniIznos;
	}

	public double getKrajnjiIznos() {
		return krajnjiIznos;
	}

	public void setKrajnjiIznos(double krajnjiIznos) {
		this.krajnjiIznos = krajnjiIznos;
	}

	public LocalDateTime getDatumTransakcije() {
		return datumTransakcije;
	}

	public void setDatumTransakcije(LocalDateTime datumTransakcije) {
		this.datumTransakcije = datumTransakcije;
	}

	@Override
	public String toString() {
		return "Transakcija [izvornaValuta=" + izvornaValuta + ", krajnjaValuta=" + krajnjaValuta + ", pocetniIznos="
				+ pocetniIznos + ", krajnjiIznos=" + krajnjiIznos + ", datumTransakcije=" + datumTransakcije + "]";
	}

}
