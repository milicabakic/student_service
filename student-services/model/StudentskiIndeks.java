package studsluzba.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="studIndeks")
@NamedQuery(name="StudentskiIndeks.findAll", query="SELECT si FROM StudentskiIndeks si")
public class StudentskiIndeks {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int godina;
	private int broj;
	
	@ManyToOne
	private StudProgram studProgram;
	
	private boolean aktivan;
	private Date datumAktivacije;
	
	@ManyToOne
	private Student student;
	
	@OneToMany
	private List<PolozenPredmet> polozeniPredmeti;
	
	@OneToMany
	private List<IzlazakNaIspit> izlasciNaIspit;
	
	@ManyToMany
	private List<PredajePredmet> predmetiKojeSlusa;
	
	@OneToMany
	private List<PredispitniPoeni> predispitniPoeni;
	
	@OneToMany
	private List<UpisGodine> upisaneGodine;
	
	@OneToMany
	private List<ObnovaGodine> obnovljeneGodine;
	
	
	
	public StudentskiIndeks() {
		this.polozeniPredmeti = new ArrayList<PolozenPredmet>();
		this.izlasciNaIspit = new ArrayList<IzlazakNaIspit>();
		this.predmetiKojeSlusa = new ArrayList<PredajePredmet>();
		this.predispitniPoeni = new ArrayList<PredispitniPoeni>();
		this.upisaneGodine = new ArrayList<UpisGodine>();
		this.obnovljeneGodine = new ArrayList<ObnovaGodine>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public StudProgram getStudProgram() {
		return studProgram;
	}

	public void setStudProgram(StudProgram studProgram) {
		this.studProgram = studProgram;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Date getDatumAktivacije() {
		return datumAktivacije;
	}

	public void setDatumAktivacije(Date datumAktivacije) {
		this.datumAktivacije = datumAktivacije;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<IzlazakNaIspit> getIzlasciNaIspit() {
		return izlasciNaIspit;
	}

	public void setIzlasciNaIspit(List<IzlazakNaIspit> izlasciNaIspit) {
		this.izlasciNaIspit = izlasciNaIspit;
	}

	public List<PredajePredmet> getPredmetiKojeSlusa() {
		return predmetiKojeSlusa;
	}

	public void setPredmetiKojeSlusa(List<PredajePredmet> predmetiKojeSlusa) {
		this.predmetiKojeSlusa = predmetiKojeSlusa;
	}
	
	
	public List<UpisGodine> getUpisaneGodine() {
		return upisaneGodine;
	}
	
	public void setUpisaneGodine(List<UpisGodine> upisaneGodine) {
		this.upisaneGodine = upisaneGodine;
	}
	
	public List<ObnovaGodine> getObnovljeneGodine() {
		return obnovljeneGodine;
	}
	
	public void setObnovljeneGodine(List<ObnovaGodine> obnovljeneGodine) {
		this.obnovljeneGodine = obnovljeneGodine;
	}

	public List<PolozenPredmet> getPolozeniPredmeti() {
		return polozeniPredmeti;
	}

	public void setPolozeniPredmeti(List<PolozenPredmet> polozeniPredmeti) {
		this.polozeniPredmeti = polozeniPredmeti;
	}

	public List<PredispitniPoeni> getPredispitniPoeni() {
		return predispitniPoeni;
	}

	public void setPredispitniPoeni(List<PredispitniPoeni> predispitniPoeni) {
		this.predispitniPoeni = predispitniPoeni;
	}
	
	@Override
	public String toString() {
		return  /*studProgram.getSkraceniNaziv()+" "*/+broj+"/"+godina + " "
				+student + ",aktivan: "+aktivan;
	}

}
