package studsluzba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="srednjaSkola")
public class SrednjaSkola {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String naziv;
	private String mesto;	
	private String vrstaSkole;
	
	@OneToMany
	private List<Student> bivsiUcenici;
	
    public SrednjaSkola() {
    	this.bivsiUcenici = new ArrayList<Student>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}


	public String getVrstaSkole() {
		return vrstaSkole;
	}

	public void setVrstaSkole(String vrstaSkole) {
		this.vrstaSkole = vrstaSkole;
	}

	public List<Student> getBivsiUcenici() {
		return bivsiUcenici;
	}

	public void setBivsiUcenici(List<Student> bivsiUcenici) {
		this.bivsiUcenici = bivsiUcenici;
	}
    
    @Override
    public String toString() {
    	return naziv + mesto;
    }
   
   
	

}
