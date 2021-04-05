package studsluzba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="predajePredmet")
public class PredajePredmet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private SkolskaGodina godina;
	
	@ManyToOne
	private Predmet predmet;
	
	@ManyToOne
	private Nastavnik nastavnik;
	
	@OneToMany
    private List<PredispitnaObaveza> predispitneObaveze;	
	

	@ManyToMany
	private List<StudentskiIndeks> studentiNaPredmetu;
	
	
	
	public PredajePredmet(){
		this.predispitneObaveze=new ArrayList<PredispitnaObaveza>();
	}


	public SkolskaGodina getGodina() {
		return godina;
	}


	public void setGodina(SkolskaGodina godina) {
		this.godina = godina;
	}


	public Nastavnik getNastavnik() {
		return nastavnik;
	}


	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}


	public Predmet getPredmet() {
		return predmet;
	}


	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<PredispitnaObaveza> getPredispitneObaveze() {
		return predispitneObaveze;
	}


	public void setPredispitneObaveze(List<PredispitnaObaveza> predispitneObaveze) {
		this.predispitneObaveze = predispitneObaveze;
	}

	
	

	public List<StudentskiIndeks> getStudentiNaPredmetu() {
		return studentiNaPredmetu;
	}


	public void setStudentiNaPredmetu(List<StudentskiIndeks> studentiNaPredmetu) {
		this.studentiNaPredmetu = studentiNaPredmetu;
	}
	
	public boolean containsStudent(StudentskiIndeks indeks) {
		for(StudentskiIndeks i : studentiNaPredmetu) {
			if(i.equals(indeks))
				return true;
		}
		
		return false;
	}


	@Override
	public String toString() {
		return predmet+","+nastavnik+","+godina;
	}


	
	
	
	
	

}
