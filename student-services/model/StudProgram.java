package studsluzba.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the studProgram database table.
 * 
 */
@Entity
@Table(name="studProgram")
@NamedQuery(name="StudProgram.findAll", query="SELECT s FROM StudProgram s")
public class StudProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idstudProgram;

	private String naziv;
	private String skraceniNaziv;
	private int godAkreditacije;
	private String zvanje;
	private int trajanje;               //u semestrima
	
	@ManyToOne
	private VrstaStudija vrstaStudija;
	
	@OneToMany
	private List<StudentskiIndeks> indeksi;
	
	@OneToMany(mappedBy="studProgram")
	private List<Predmet> predmeti;

	public StudProgram() {
		this.predmeti = new ArrayList<Predmet>();
		this.indeksi = new ArrayList<StudentskiIndeks>();
	}

	public int getIdstudProgram() {
		return this.idstudProgram;
	}

	public void setIdstudProgram(int idstudProgram) {
		this.idstudProgram = idstudProgram;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSkraceniNaziv() {
		return this.skraceniNaziv;
	}

	public void setSkraceniNaziv(String skraceniNaziv) {
		this.skraceniNaziv = skraceniNaziv;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public int getGodAkreditacije() {
		return godAkreditacije;
	}

	public void setGodAkreditacije(int godAkreditacije) {
		this.godAkreditacije = godAkreditacije;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public VrstaStudija getVrstaStudija() {
		return vrstaStudija;
	}

	public void setVrstaStudija(VrstaStudija vrstaStudija) {
		this.vrstaStudija = vrstaStudija;
	}

	public List<StudentskiIndeks> getIndeksi() {
		return indeksi;
	}

	public void setIndeksi(List<StudentskiIndeks> indeksi) {
		this.indeksi = indeksi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return skraceniNaziv + "-" + naziv;
	}
	

}