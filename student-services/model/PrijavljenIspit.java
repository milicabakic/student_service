package studsluzba.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="prijavljenIspit")
@NamedQuery(name="PrijavljenIspit.findAll",query="SELECT p FROM PrijavljenIspit p")
public class PrijavljenIspit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date datumPrijave;
	private boolean izasao;	
	
	@ManyToOne
	private StudentskiIndeks indeksStudenta;
	
	@ManyToOne
	private Ispit ispit;
	
	
	public PrijavljenIspit() {
		
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


	public Ispit getIspit() {
		return ispit;
	}


	public void setIspit(Ispit ispit) {
		this.ispit = ispit;
	}


	public Date getDatumPrijave() {
		return datumPrijave;
	}


	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}


	public boolean isIzasao() {
		return izasao;
	}


	public void setIzasao(boolean izasao) {
		this.izasao = izasao;
	}
	
	@Override
	public String toString() {
		return "Prijavljen ispit "+id+" "+ispit+ "student: "+indeksStudenta 
				+ "izasao: " + izasao;
	}
	
}
