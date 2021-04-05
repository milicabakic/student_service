package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.Nastavnik;
import studsluzba.model.PolozenPredmet;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.StudProgram;
import studsluzba.model.StudentskiIndeks;
import studsluzba.repositories.PolozenPredmetRepository;
import studsluzba.repositories.PredajePredmetRepository;
import studsluzba.repositories.PredmetRepository;

@Service
public class PredmetService {

	
	@Autowired
	PredmetRepository predmetRepo;

	@Autowired
	PolozenPredmetRepository polozenPredmetRepository;
	
	@Autowired
	PredajePredmetRepository predajePredmetRepo;
	
	
	public List<PolozenPredmet> getPolozeniPredmeti(StudentskiIndeks si) {
		return polozenPredmetRepository.findPassedSubjectsByIndex(si);
	}
	
	public Predmet savePredmet(String naziv, String sifra, String opis, StudProgram program, int esbp, int fondCasova, int semestar) {
		Predmet p = new Predmet();
		p.setNazivPredmeta(naziv);
		p.setSifraPredmeta(sifra);
		p.setOpis(opis);
		p.setStudProgram(program);
		p.setEsbp(esbp);
		p.setFondCasova(fondCasova);
		p.setSemestar(semestar);
		return predmetRepo.save(p);
	}
	
	public Predmet savePredmet(String naziv) {
		Predmet p = new Predmet();
		p.setNazivPredmeta(naziv);
		return predmetRepo.save(p);
	}
	
	public List<Predmet> loadAll(){
		Iterable<Predmet> iter = predmetRepo.findAll();
		List<Predmet> rez = new ArrayList<Predmet>();		
		iter.forEach(rez::add);
		return rez;
	}
	
	public List<Predmet> findPredmetByStudProgram(StudProgram program){
		return predmetRepo.findSubjectsOnSection(program);
	}
	
	public Nastavnik findNastavnikForPredajePredmet(Predmet predmet, SkolskaGodina godina) {
		return predajePredmetRepo.findNastavnik(predmet, godina);
	}
	
}
