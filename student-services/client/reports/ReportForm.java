package studsluzba.client.reports;

public class ReportForm {
	
	String ime;
	String prezime;
	String indeks;
	int brojIzlazakaNaIspit;
	int ocena;
	double brojPoena;
    String napomena;
    
	
	public ReportForm() {
		
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getIndeks() {
		return indeks;
	}


	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}


	public int getBrojIzlazakaNaIspit() {
		return brojIzlazakaNaIspit;
	}


	public void setBrojIzlazakaNaIspit(int brojIzlazakaNaIspit) {
		this.brojIzlazakaNaIspit = brojIzlazakaNaIspit;
	}


	public int getOcena() {
		return ocena;
	}


	public void setOcena(int ocena) {
		this.ocena = ocena;
	}


	public double getBrojPoena() {
		return brojPoena;
	}


	public void setBrojPoena(double brojPoena) {
		this.brojPoena = brojPoena;
	}
	
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	public String getNapomena() {
		return napomena;
	}
	
	@Override
	public String toString() {
		return ime + " " + prezime + " " + indeks;
	}
}



