package studsluzba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="predmet")
public class Predmet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private String opis;
	private int esbp;
	private int semestar;
	private int fondCasova;
	
	@ManyToOne
	private StudProgram studProgram;
	
	@ManyToMany
	private List<Nastavnik> nastavnici;
	
	
	public Predmet() {
		super();
		this.nastavnici = new ArrayList<Nastavnik>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSifraPredmeta() {
		return sifraPredmeta;
	}

	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public StudProgram getStudProgram() {
		return studProgram;
	}

	public void setStudProgram(StudProgram studProgram) {
		this.studProgram = studProgram;
	}

	public List<Nastavnik> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<Nastavnik> nastavnici) {
		this.nastavnici = nastavnici;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getEsbp() {
		return esbp;
	}

	public void setEsbp(int esbp) {
		this.esbp = esbp;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public int getFondCasova() {
		return fondCasova;
	}

	public void setFondCasova(int fondCasova) {
		this.fondCasova = fondCasova;
	}
	
	@Override
	public String toString() {
		return nazivPredmeta; 
				//+", "+studProgram.getSkraceniNaziv();
	}
	
	
}
