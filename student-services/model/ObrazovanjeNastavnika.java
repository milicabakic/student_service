package studsluzba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="obrazovanjeNastavnika")
public class ObrazovanjeNastavnika {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Nastavnik nastavnik;
	
	@ManyToOne
	private VisokoskolskaUstanova zavrsenaSkola;
	
	@ManyToOne
	private VrstaStudija vrstaStudija;
	
	
	public ObrazovanjeNastavnika() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Nastavnik getNastavnik() {
		return nastavnik;
	}


	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}


	public VisokoskolskaUstanova getZavrsenaSkola() {
		return zavrsenaSkola;
	}


	public void setZavrsenaSkola(VisokoskolskaUstanova zavrsenaSkola) {
		this.zavrsenaSkola = zavrsenaSkola;
	}


	public VrstaStudija getVrstaStudija() {
		return vrstaStudija;
	}


	public void setVrstaStudija(VrstaStudija vrstaStudija) {
		this.vrstaStudija = vrstaStudija;
	}
	
	

}
