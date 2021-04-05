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
@Table(name="izlazakNaIspit")
public class IzlazakNaIspit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Ispit ispit;
	
	@ManyToOne
	private StudentskiIndeks studentskiIndeks;
	
	private float ukupanBrojPoena;
	private float poeniNaIspitu;
	private String napomena;
	private boolean ponistava;
	private int ocena;
	private boolean polozen;
	
	@ManyToMany
	private List<PredispitniPoeni> predispitniPoeni;
	
	
	public IzlazakNaIspit() {
		this.predispitniPoeni = new ArrayList<PredispitniPoeni>();
	}

	
	public List<PredispitniPoeni> getPredispitniPoeni() {
		return predispitniPoeni;
	}

	public void setPredispitniPoeni(List<PredispitniPoeni> predispitniPoeni) {
		this.predispitniPoeni = predispitniPoeni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getUkupanBrojPoena() {
		return ukupanBrojPoena;
	}

	public void setUkupanBrojPoena(float ukupanBrojPoena) {
		this.ukupanBrojPoena = ukupanBrojPoena;
	}

	public float getPoeniNaIspitu() {
		return poeniNaIspitu;
	}

	public void setPoeniNaIspitu(float poeniNaIspitu) {
		this.poeniNaIspitu = poeniNaIspitu;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public boolean isPonistava() {
		return ponistava;
	}

	public void setPonistava(boolean ponistavanje) {
		this.ponistava = ponistavanje;
	}

	public Ispit getIspit() {
		return ispit;
	}

	public void setIspit(Ispit ispit) {
		this.ispit = ispit;
	}


	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public boolean isPolozen() {
		return polozen;
	}

	public void setPolozen(boolean polozen) {
		this.polozen = polozen;
	}

	public StudentskiIndeks getStudentskiIndeks() {
		return studentskiIndeks;
	}

	public void setStudentskiIndeks(StudentskiIndeks studentskiIndeks) {
		this.studentskiIndeks = studentskiIndeks;
	}
	
	@Override
	public String toString() {
		return studentskiIndeks.getStudProgram().getNaziv()+","+studentskiIndeks.getStudent()+" ,br. indeksa: "+studentskiIndeks.getBroj() +" ,ispit: "+ispit+
				" ,broj poena: " + ukupanBrojPoena;
	}

}
