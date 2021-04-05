package studsluzba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="predObaveze")
public class PredispitnaObaveza {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String vrstaPredispitneObaveze;
	private int maxBrojPoena;
	
	public PredispitnaObaveza() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVrsta() {
		return vrstaPredispitneObaveze;
	}

	public void setVrsta(String vrsta) {
		this.vrstaPredispitneObaveze = vrsta;
	}

	public int getMaxBrojPoena() {
		return maxBrojPoena;
	}

	public void setMaxBrojPoena(int maxBrojPoena) {
		this.maxBrojPoena = maxBrojPoena;
	}

	@Override
	public String toString() {
		return "PredispitnaObaveza [vrstaPredispitneObaveze=" + vrstaPredispitneObaveze + ", maxBrojPoena="
				+ maxBrojPoena + "]";
	}
	
	
	

}
