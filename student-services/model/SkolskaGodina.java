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
@Table(name="skolskaGodina")
public class SkolskaGodina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int godinaPocetna;
	private int godinaZavrsna;
	private boolean aktivna;
	

	@OneToMany
	private List<IspitniRok> ispitniRokovi;
	
	
	
	public SkolskaGodina() {
		this.ispitniRokovi = new ArrayList<IspitniRok>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGodinaPocetna() {
		return godinaPocetna;
	}
	
	public void setGodinaPocetna(int godinaPocetna) {
		this.godinaPocetna = godinaPocetna;
	}
	
	public int getGodinaZavrsna() {
		return godinaZavrsna;
	}
	
	public void setGodinaZavrsna(int godinaZavrsna) {
		this.godinaZavrsna = godinaZavrsna;
	}
	
	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}
	
	public boolean isAktivna() {
		return aktivna;
	}

	public List<IspitniRok> getIspitniRokovi() {
		return ispitniRokovi;
	}

	public void setIspitniRokovi(List<IspitniRok> ispitniRokovi) {
		this.ispitniRokovi = ispitniRokovi;
	}
	
	@Override
	public String toString() {
		return godinaPocetna + "/" + godinaZavrsna;
	}
	
}
