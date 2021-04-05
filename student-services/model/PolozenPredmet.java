package studsluzba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="polozenPredmet")
@NamedQuery(name="PolozenPredmet.findAll",query="SELECT pp FROM PolozenPredmet pp")
public class PolozenPredmet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private StudentskiIndeks indeksStudenta;
	
	@ManyToOne
	private Predmet predmet;
	
	private int ocena;
	private boolean priznatSaDrugogFakulteta = false;	
	private int godina;
	
	public PolozenPredmet() {
		
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


	public Predmet getPredmet() {
		return predmet;
	}


	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}


	public int getOcena() {
		return ocena;
	}


	public void setOcena(int ocena) {
		this.ocena = ocena;
	}


	public boolean isPriznatSaDrugogFakulteta() {
		return priznatSaDrugogFakulteta;
	}


	public void setPriznatSaDrugogFakulteta(boolean priznatSaDrugogFakulteta) {
		this.priznatSaDrugogFakulteta = priznatSaDrugogFakulteta;
	}


	@Override
	public String toString() {
		return "Polozen predmet :"+predmet+" "+indeksStudenta + " sa ocenom " + ocena;
	}


	public int getGodina() {
		return godina;
	}


	public void setGodina(int godina) {
		this.godina = godina;
	}
	
	

}
