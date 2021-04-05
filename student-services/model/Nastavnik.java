package studsluzba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="nastavnik")
public class Nastavnik {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNastavnik;	

	private String ime;
    private String srednjeIme;
	private String prezime;
	private String email;
	
	@OneToMany
	private List<Zvanje> zvanje;
	
	@OneToMany
	private List<ObrazovanjeNastavnika> obrazovanje;

	
	
	public Nastavnik() {
		zvanje = new ArrayList<Zvanje>();
	}
	
	public int getIdNastavnik() {
		return idNastavnik;
	}

	public void setIdNastavnik(int idNastavnik) {
		this.idNastavnik = idNastavnik;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getSrednjeIme() {
		return srednjeIme;
	}

	public void setSrednjeIme(String srednjeIme) {
		this.srednjeIme = srednjeIme;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Zvanje> getZvanje() {
		return zvanje;
	}

	public void setZvanje(List<Zvanje> zvanje) {
		this.zvanje = zvanje;
	}

	public List<ObrazovanjeNastavnika> getObrazovanje() {
		return obrazovanje;
	}

	public void setObrazovanje(List<ObrazovanjeNastavnika> obrazovanje) {
		this.obrazovanje = obrazovanje;
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	
		
	
}
