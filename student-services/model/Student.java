package studsluzba.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idstudent;

	private int godinaUpisa;
	
	private String pol;	
	private String ime;
	private String prezime;
	private String srednjeIme;
	private String jmbg;
	private Date datumRodjenja;
	private String mestoRodjenja;
	private String drzavaRodjenja;
	private String drzavljanstvo;
	private String nacionalnost;
	private String adresa;
	private String mestoStanovanja;
	private String brojTelefona;
	private String emailFakultet;
	private String emailPrivatni;
	private String licnaKarta;
	
	@ManyToOne
	private SrednjaSkola srednjaSkola;
	
	private double uspehIzSrednje;
	private double uspehSaPrijemnog;
	
	@ManyToOne
	private VisokoskolskaUstanova prelaz;
	
	@OneToMany
	private List<StudentskiIndeks> indeksi;
	
	
	public Student() {
		
		this.indeksi = new ArrayList<StudentskiIndeks>();
		
	}
	
	
	public Student(int godinaUpisa, String ime, String prezime) {
		super();
		this.godinaUpisa = godinaUpisa;
		this.ime = ime;
		this.prezime = prezime;
	}



	public int getIdstudent() {
		return this.idstudent;
	}

	public void setIdstudent(int idstudent) {
		this.idstudent = idstudent;
	}

	public int getGodinaUpisa() {
		return this.godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}




	public String getSrednjeIme() {
		return srednjeIme;
	}



	public void setSrednjeIme(String srednjeIme) {
		this.srednjeIme = srednjeIme;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public Date getDatumRodjenja() {
		return datumRodjenja;
	}


	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}


	public String getMestoRodjenja() {
		return mestoRodjenja;
	}


	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}


	public String getDrzavaRodjenja() {
		return drzavaRodjenja;
	}


	public void setDrzavaRodjenja(String drzavaRodjenja) {
		this.drzavaRodjenja = drzavaRodjenja;
	}


	public String getDrzavljanstvo() {
		return drzavljanstvo;
	}


	public void setDrzavljanstvo(String drzavljanstvo) {
		this.drzavljanstvo = drzavljanstvo;
	}


	public String getNacionalnost() {
		return nacionalnost;
	}


	public void setNacionalnost(String nacionalnost) {
		this.nacionalnost = nacionalnost;
	}


	public String getPol() {
		return pol;
	}


	public void setPol(String pol) {
		this.pol = pol;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getBrojTelefona() {
		return brojTelefona;
	}


	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}


	public String getEmailFakultet() {
		return emailFakultet;
	}


	public void setEmailFakultet(String emailFakultet) {
		this.emailFakultet = emailFakultet;
	}


	public String getEmailPrivatni() {
		return emailPrivatni;
	}


	public void setEmailPrivatni(String emailPrivatni) {
		this.emailPrivatni = emailPrivatni;
	}


	public String getLicnaKarta() {
		return licnaKarta;
	}


	public void setLicnaKarta(String licnaKarta) {
		this.licnaKarta = licnaKarta;
	}


	public SrednjaSkola getSrednjaSkola() {
		return srednjaSkola;
	}


	public void setSrednjaSkola(SrednjaSkola srednjaSkola) {
		this.srednjaSkola = srednjaSkola;
	}


	public double getUspehIzSrednje() {
		return uspehIzSrednje;
	}


	public void setUspehIzSrednje(double uspehIzSrednje) {
		this.uspehIzSrednje = uspehIzSrednje;
	}


	public double getUspehSaPrijemnog() {
		return uspehSaPrijemnog;
	}


	public void setUspehSaPrijemnog(double uspehSaPrijemnog) {
		this.uspehSaPrijemnog = uspehSaPrijemnog;
	}


	public VisokoskolskaUstanova getPrelaz() {
		return prelaz;
	}


	public void setPrelaz(VisokoskolskaUstanova prelaz) {
		this.prelaz = prelaz;
	}


	public List<StudentskiIndeks> getIndeksi() {
		return indeksi;
	}


	public void setIndeksi(List<StudentskiIndeks> indeksi) {
		this.indeksi = indeksi;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public void addStudentskiIndeks(StudentskiIndeks si) {
		this.indeksi.add(si);
	}
	
	
	@Override
	public String toString() {
		return ime+" "+srednjeIme+" "+prezime;
	}
	
	public String getMestoStanovanja() {
		return mestoStanovanja;
	}
	
	public void setMestoStanovanja(String mestoStanovanja) {
		this.mestoStanovanja = mestoStanovanja;
	}
	

}