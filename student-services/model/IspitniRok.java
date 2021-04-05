package studsluzba.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ispitniRok")
public class IspitniRok {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String naziv;
	private Date datumPocetka;
	private Date datumZavrsetka;
	
	@OneToMany
	private List<Ispit> ispiti;
	
	@ManyToOne
	private SkolskaGodina SkolskaGodina;
	
	public IspitniRok() {
		this.ispiti = new ArrayList<Ispit>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public List<Ispit> getIspiti() {
		return ispiti;
	}

	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

	public SkolskaGodina getSkolskaGodina() {
		return SkolskaGodina;
	}

	public void setSkolskaGodina(SkolskaGodina skolskaGodina) {
		SkolskaGodina = skolskaGodina;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	@Override
	public String toString() {
		return naziv +" "+ SkolskaGodina;
	}
	

}
