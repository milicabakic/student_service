package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.IzlazakNaIspit;
import studsluzba.model.PolozenPredmet;
import studsluzba.model.PredajePredmet;
import studsluzba.model.PredispitnaObaveza;
import studsluzba.model.PredispitniPoeni;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.repositories.PredajePredmetRepository;
import studsluzba.repositories.PredispitniPoeniRepository;
import studsluzba.repositories.StudProgramRepository;
import studsluzba.repositories.StudentRepository;
import studsluzba.repositories.StudentskiIndeksRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	StudentskiIndeksRepository indeksRepo;

	@Autowired
	StudProgramRepository studProgramRepo;
	
	@Autowired
	PredispitniPoeniRepository poeniRepo;
	
	@Autowired
	PredajePredmetRepository predajePredmetRepo;
	
	
	public List<Student> loadAll(){
		Iterable<Student> iter = studentRepo.findAll();
		List<Student> rez = new ArrayList<Student>();		
		iter.forEach(rez::add);
		return rez;
	}
	
	
	public Student saveStudent(Student s){
		return studentRepo.save(s);				
	}
	
	public void updateStudent(Student s) {
		studentRepo.save(s);
	}
	
	public List<Student> searchStudents(String ime, String prezime){
		List<Student> students = new ArrayList<Student>();
		if (ime.equals("")) students = studentRepo.findStudent(null, prezime.toLowerCase());
		else if (prezime.equals("")) students = studentRepo.findStudent(ime.toLowerCase(), null);
		else students = studentRepo.findStudent(ime.toLowerCase(), prezime.toLowerCase());
		return students;
	}
	
	public List<Student> searchStudents(String skraceni, int broj, int godina){
		List<Student> students = new ArrayList<Student>();
		students = indeksRepo.findStudentByIndexString(skraceni, godina, broj);
		return students;
	}
	
	public Student findStudentByIndex(Student s) {
		return studentRepo.findStudentByIndex(s);
	}
	
	public List<StudentskiIndeks> loadAllIndeks() {
		return indeksRepo.loadlAllWithPredmetiKojeSlusa();
	}
	
	public void addPredmetKojiSlusa(PredajePredmet predmetKojiSlusa,StudentskiIndeks indeks) {
		indeks.getPredmetiKojeSlusa().add(predmetKojiSlusa);
		indeksRepo.save(indeks);
	}
	
	public StudentskiIndeks saveStudentAndIndex(String ime, String prezime, String skraceniNaziv, 
			int broj, int godina, PredajePredmet pp) {
		Student s = new Student();
		s.setIme(ime);
		s.setPrezime(prezime);
		studentRepo.save(s);
		
		StudentskiIndeks indeks = new StudentskiIndeks();
		indeks.setGodina(godina);
		indeks.setBroj(broj);
		indeks.setStudent(s);
		indeks.setStudProgram(studProgramRepo.findStudProgramBySkraceniNaziv(skraceniNaziv));
		indeks.setAktivan(true);
		indeksRepo.save(indeks);
		
		Student stud = findStudentByIndex(s);
		stud.getIndeksi().add(indeks);
		saveStudent(stud);
		
		System.out.println(indeks);
		System.out.println(pp);
		
		saveIndeksWithPredajePredmet(indeks, pp);
		
		return indeks;
	}
	
	public PredispitniPoeni savePredispitniPoeniForStudent(StudentskiIndeks indeks, PredispitnaObaveza obaveza, 
		PredajePredmet predmet, float osvojeniPoeni) {
		PredispitniPoeni poeni = new PredispitniPoeni();
		poeni.setIndeks(indeks);
		poeni.setPredispitnaObaveza(obaveza);
		poeni.setPredmetKojiSlusa(predmet);
		poeni.setOsvojeniPoeni(osvojeniPoeni);
		
		return poeniRepo.save(poeni);
	}
	
	public void saveIndeksWithIzlazakNaIspit(StudentskiIndeks indeks, IzlazakNaIspit izlazak) {
		StudentskiIndeks studIndeks = indeksRepo.findIndeksWithIzlasciNaIspit(indeks);
		studIndeks.getIzlasciNaIspit().add(izlazak);
		indeksRepo.save(studIndeks);
	}
	
	public void saveIndeksWithPolozenPredmet(StudentskiIndeks indeks, PolozenPredmet pp) {
		StudentskiIndeks si = indeksRepo.findIndeksWithPolozeniPredmeti(indeks);
		si.getPolozeniPredmeti().add(pp);
		indeksRepo.save(si);
	}
	
	public void saveIndeksWithPredajePredmet(StudentskiIndeks indeks, PredajePredmet pp) {
		StudentskiIndeks si = indeksRepo.findIndeksWithPredmetiKojeSlusa(indeks);
		si.getPredmetiKojeSlusa().add(pp);
		indeksRepo.save(si);
		
		PredajePredmet predmet = predajePredmetRepo.loadPredmetWithStudents(pp);
		predmet.getStudentiNaPredmetu().add(indeks);
		predajePredmetRepo.save(predmet);
	}
	
	public void saveIndeksWithPoeni(StudentskiIndeks indeks, List<PredispitniPoeni> poeniList) {
		StudentskiIndeks si = indeksRepo.findIndeksWithPoeniList(indeks);
		si.getPredispitniPoeni().addAll(poeniList);
		indeksRepo.save(si);
	}
	
}
