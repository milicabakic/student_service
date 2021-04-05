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
import javax.persistence.Table;

@Entity
@Table(name="upisGodine")
@NamedQuery(name="UpisGodine.selectAll", query="SELECT u FROM UpisGodine u")
public class UpisGodine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date datumUpisa;
	private String napomena;	
	
	@ManyToOne
	private StudentskiIndeks studentskiIndeks;
	
	@ManyToOne
	private SkolskaGodina skolskaGodina;
	
	@ManyToMany
	private List<Predmet> nepolozenPredmet;
	
	
	public UpisGodine() {
		this.nepolozenPredmet = new ArrayList<Predmet>();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public StudentskiIndeks getStudentskiIndeks() {
		return studentskiIndeks;
	}
	
	public void setStudentskiIndeks(StudentskiIndeks studentskiIndeks) {
		this.studentskiIndeks = studentskiIndeks;
	}


	public SkolskaGodina getSkolskaGodina() {
		return skolskaGodina;
	}


	public void setSkolskaGodina(SkolskaGodina skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}


	public Date getDatumUpisa() {
		return datumUpisa;
	}


	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}


	public String getNapomena() {
		return napomena;
	}


	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	
	
	public List<Predmet> getNepolozenPredmet() {
		return nepolozenPredmet;
	}


	public void setNepolozenPredmet(List<Predmet> nepolozenPredmet) {
		this.nepolozenPredmet = nepolozenPredmet;
	}


	@Override
	public String toString() {
		return "UpisGodine " + skolskaGodina+" student:"+studentskiIndeks /*+
				" predmeti: " + nepolozenPredmet*/;
	}
	

}
