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
@Table(name="obnovaGodine")
@NamedQuery(name = "ObnovaGodine.findAll",query="SELECT o FROM ObnovaGodine o")
public class ObnovaGodine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private StudentskiIndeks indeksStudenta;
	
	@ManyToOne
	private SkolskaGodina skolskaGodina;
	
	@ManyToMany
	private List<Predmet> nepolozeniPredmeti;

	private Date datumObnavljanja;
	private String napomena;
	
	
	public ObnovaGodine() {
		this.nepolozeniPredmeti = new ArrayList<Predmet>();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public StudentskiIndeks getIndeksStudenta() {
		return indeksStudenta;
	}
	
	public void setIndeksStudenta(StudentskiIndeks indeksStudenta) {
		this.indeksStudenta = indeksStudenta;
	}


	public SkolskaGodina getSkolskaGodina() {
		return skolskaGodina;
	}


	public void setSkolskaGodina(SkolskaGodina skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}


	public Date getDatumObnavljanja() {
		return datumObnavljanja;
	}


	public void setDatumObnavljanja(Date datumObnavljanja) {
		this.datumObnavljanja = datumObnavljanja;
	}


	public String getNapomena() {
		return napomena;
	}


	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	
	public List<Predmet> getNepolozeniPredmeti() {
		return nepolozeniPredmeti;
	}


	public void setNepolozeniPredmeti(List<Predmet> nepolozeniPredmeti) {
		this.nepolozeniPredmeti = nepolozeniPredmeti;
	}


	@Override
	public String toString() {
		return "Obnova godine "+id+" s:"+indeksStudenta+" god: "+skolskaGodina 
				/*+ " preneti predmeti :" + nepolozeniPredmeti*/;
	}

}
