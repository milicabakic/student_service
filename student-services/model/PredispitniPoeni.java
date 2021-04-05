package studsluzba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="predispitniPoeni")
public class PredispitniPoeni {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private StudentskiIndeks indeks;
	
	@ManyToOne
	private PredispitnaObaveza predispitnaObaveza;
	
	private float osvojeniPoeni;
	
	@ManyToOne
	private PredajePredmet predmetKojiSlusa;
	
	
	
	public PredispitniPoeni() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public StudentskiIndeks getIndeks() {
		return indeks;
	}



	public void setIndeks(StudentskiIndeks indeks) {
		this.indeks = indeks;
	}



	public PredispitnaObaveza getPredispitnaObaveza() {
		return predispitnaObaveza;
	}



	public void setPredispitnaObaveza(PredispitnaObaveza predispitnaObaveza) {
		this.predispitnaObaveza = predispitnaObaveza;
	}



	public float getOsvojeniPoeni() {
		return osvojeniPoeni;
	}



	public void setOsvojeniPoeni(float osvojeniPoeni) {
		this.osvojeniPoeni = osvojeniPoeni;
	}



	public PredajePredmet getPredmetKojiSlusa() {
		return predmetKojiSlusa;
	}



	public void setPredmetKojiSlusa(PredajePredmet predmetKojiSlusa) {
		this.predmetKojiSlusa = predmetKojiSlusa;
	}
	
	
	@Override
	public String toString() {
		return "Poeni: "+osvojeniPoeni+" ,student: "+indeks+" ,predmet: " + predmetKojiSlusa.getPredmet();
	}
	

}
