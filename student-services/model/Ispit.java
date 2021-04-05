package studsluzba.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ispit")
@NamedQuery(name = "Ispit.findAll",query="SELECT i FROM Ispit i")
public class Ispit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date datumOdrzavanja;
	
	@ManyToOne
	private Predmet predmet;
	
	@ManyToOne
	private Nastavnik nastavnik;

	@ManyToOne
	private IspitniRok ispitniRok;
	
	private LocalTime vreme;
	private boolean zakljucen;
	
	public Ispit() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}

	public boolean isZakljucen() {
		return zakljucen;
	}

	public void setZakljucen(boolean zakljucen) {
		this.zakljucen = zakljucen;
	}

	public IspitniRok getIspitniRok() {
		return ispitniRok;
	}

	public void setIspitniRok(IspitniRok ispitniRok) {
		this.ispitniRok = ispitniRok;
	}
	
	@Override
	public String toString() {
		return predmet + " " + ispitniRok ;
	}
	
}
