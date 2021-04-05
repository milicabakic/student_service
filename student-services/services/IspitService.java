package studsluzba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.IzlazakNaIspit;
import studsluzba.model.Nastavnik;
import studsluzba.model.PolozenPredmet;
import studsluzba.model.PredispitniPoeni;
import studsluzba.model.Predmet;
import studsluzba.model.PrijavljenIspit;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.repositories.IspitRepository;
import studsluzba.repositories.IspitniRokRepository;
import studsluzba.repositories.IzlazakNaIspitRepository;
import studsluzba.repositories.PolozenPredmetRepository;
import studsluzba.repositories.PrijavljenIspitRepository;

@Service
public class IspitService {

	@Autowired IspitniRokRepository ispitniRokRepo;
	
	@Autowired IspitRepository ispitRepo;
	
	@Autowired PrijavljenIspitRepository prijavljenIspitRepo;
	
	@Autowired IzlazakNaIspitRepository izlazakNaIspitRepo;
	
	@Autowired PolozenPredmetRepository polozenPredmetRepo;
	
	@Autowired StudentService studentService;
	
	public List<IspitniRok> loadAllIspitniRokovi(){
		Iterable<IspitniRok> iter = ispitniRokRepo.findAll();
		List<IspitniRok> rez = new ArrayList<IspitniRok>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public IspitniRok saveIspitniRok(String naziv, Date pocetak, Date kraj, SkolskaGodina godina){
		IspitniRok rok = new IspitniRok();
		rok.setNaziv(naziv);
		rok.setDatumPocetka(pocetak);
		rok.setDatumZavrsetka(kraj);
		rok.setSkolskaGodina(godina);
		return ispitniRokRepo.save(rok);
	}	
	
	public IspitniRok saveIspitniRok(String naziv, SkolskaGodina godina){
		IspitniRok rok = new IspitniRok();
		rok.setNaziv(naziv);
		rok.setSkolskaGodina(godina);
		return ispitniRokRepo.save(rok);
	}
	
	public List<Ispit> loadAllIspiti(){
		Iterable<Ispit> iter = ispitRepo.findAll();
		List<Ispit> rez = new ArrayList<Ispit>();		
		iter.forEach(rez::add);
		return rez;
	}
	
	public void saveIspit(Date datum, Predmet predmet, Nastavnik nastavnik){
		Ispit i = new Ispit();
		i.setDatumOdrzavanja(datum);
		i.setPredmet(predmet);
		i.setNastavnik(nastavnik);
		ispitRepo.save(i);
	}	
	
	public Ispit saveIspit(Predmet predmet, IspitniRok rok){
		Ispit i = new Ispit();
		i.setPredmet(predmet);
		i.setIspitniRok(rok);
		return ispitRepo.save(i);
	}
	
	public void matchIspitAndIspitniRok(Ispit ispit, IspitniRok rok) {
		ispit.setIspitniRok(rok);
		ispitRepo.save(ispit);
	}
	
	public List<PrijavljenIspit> loadlAllPrijavljeniIspiti(){
		Iterable<PrijavljenIspit> iter = prijavljenIspitRepo.findAll();
		List<PrijavljenIspit> rez = new ArrayList<PrijavljenIspit>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public List<PrijavljenIspit> findPrijavljeniStudenti(Ispit ispit){
		return prijavljenIspitRepo.findAllPrijavljeniIspitiForIspit(ispit);
	}
	
	public List<IzlazakNaIspit> loadlAllIzlasciNaIspit(){
		Iterable<IzlazakNaIspit> iter = izlazakNaIspitRepo.findAll();
		List<IzlazakNaIspit> rez = new ArrayList<IzlazakNaIspit>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public List<IzlazakNaIspit> findRezultati(Ispit ispit){
		return izlazakNaIspitRepo.sortStudents(ispit);
	}
	
	public void savePrijavljenIspit(StudentskiIndeks indeks, Ispit ispit, boolean izlazak) {
		PrijavljenIspit prijava = new PrijavljenIspit();
		prijava.setIndeksStudenta(indeks);
		prijava.setIspit(ispit);
		prijava.setIzasao(izlazak);
		
		prijavljenIspitRepo.save(prijava);
	}
	
	public IzlazakNaIspit saveIzlazakNaIspit(StudentskiIndeks indeks, Ispit ispit, float poeniIspit, boolean ponistava, 
			float predispitniPoeni, List<PredispitniPoeni> listaPoena) {
		IzlazakNaIspit izlazak = new IzlazakNaIspit();
		izlazak.setIspit(ispit);
		izlazak.setStudentskiIndeks(indeks);
		izlazak.setPoeniNaIspitu(poeniIspit);
		izlazak.setPonistava(ponistava);
		izlazak.setPredispitniPoeni(listaPoena);
		izlazak.setUkupanBrojPoena(predispitniPoeni + poeniIspit); 
		izlazak.setOcena(generateMark(predispitniPoeni + poeniIspit));
		if(poeniIspit >= 50)
			izlazak.setPolozen(true);
		else
			izlazak.setPolozen(false);	
		izlazakNaIspitRepo.save(izlazak);
		
	    studentService.saveIndeksWithIzlazakNaIspit(indeks, izlazak);
		
		return izlazak;
	}
	
	public void savePolozenPredmet(StudentskiIndeks indeks, float ukupniPoeni, Predmet predmet,
			boolean priznatSaDrugogFakulteta) {
		PolozenPredmet pp = new PolozenPredmet();
		pp.setIndeksStudenta(indeks);
		pp.setPredmet(predmet);
		pp.setOcena(generateMark(ukupniPoeni));
		pp.setPriznatSaDrugogFakulteta(priznatSaDrugogFakulteta);
		
		polozenPredmetRepo.save(pp);
		
		studentService.saveIndeksWithPolozenPredmet(indeks, pp);
	}
	
	public List<Student> loadStudentsForIspit(IspitniRok rok, Predmet predmet){
		return izlazakNaIspitRepo.findStudentsForIzlazakNaIspit(rok, predmet);
	}
	
	public List<StudentskiIndeks> loadIndeksiForIspit(IspitniRok rok, Predmet predmet){
		return izlazakNaIspitRepo.findIndeksiForIzlazakNaIspit(rok, predmet);
	}
	
	public int countIzlazakNaIspitForStudent(Student s, Predmet p) {
		return izlazakNaIspitRepo.countIzlazakNaIspitForStudent(s, p);
	}
	
	public Ispit findIspitByPredmetNastavnikIspitniRok(Predmet p, IspitniRok r) {
		return ispitRepo.findIspitByPredmetNastavnikIspitniRok(p, r);
	}
	
	public int findPoeni(StudentskiIndeks si, Ispit i) {
		return izlazakNaIspitRepo.findPoeni(si, i);
	}
	
	public int findOcena(StudentskiIndeks si, Ispit i) {
		return izlazakNaIspitRepo.findOcena(si, i);
	}
	
	public String findNapomena(StudentskiIndeks si, Ispit i) {
		return izlazakNaIspitRepo.findNapomena(si, i);
	}
	
	private int generateMark(float poeni) {
		if(poeni >= 91)
			return 10;
		else if(poeni < 91 && poeni >= 81)
			return 9;
		else if(poeni < 81 && poeni >= 71)
			return 8;
		else if(poeni < 71 && poeni>= 61)
			return 7;
		else if(poeni < 61 && poeni>= 50)
			return 6;
		return 5;
	}
}
