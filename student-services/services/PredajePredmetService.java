package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.Nastavnik;
import studsluzba.model.PredajePredmet;
import studsluzba.model.PredispitnaObaveza;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.StudentskiIndeks;
import studsluzba.repositories.NastavnikRepository;
import studsluzba.repositories.PredajePredmetRepository;
import studsluzba.repositories.PredispitnaObavezaRepository;
import studsluzba.repositories.PredmetRepository;

@Service
public class PredajePredmetService {

	
	@Autowired
	PredajePredmetRepository predajePredmetRepo;
	
	@Autowired
	NastavnikRepository nastavnikRepo;
	
	@Autowired
	PredmetRepository predmetRepo;
	
	@Autowired
	PredispitnaObavezaRepository predObavezaRepo;
		
	
	public PredajePredmet savePredajePredmet(Nastavnik nastavnik, SkolskaGodina godina, Predmet predmet){
		PredajePredmet pp = new PredajePredmet();
		pp.setGodina(godina);
		pp.setNastavnik(nastavnik);
		pp.setPredmet(predmet);
		return predajePredmetRepo.save(pp);				
	}
	
	public PredajePredmet savePredajePredmet(SkolskaGodina godina, Predmet predmet){
		PredajePredmet pp = new PredajePredmet();
		pp.setGodina(godina);
		pp.setPredmet(predmet);
		return predajePredmetRepo.save(pp);				
	}
	
	public PredajePredmet savePredajePredmet(SkolskaGodina godina, Predmet predmet, List<PredispitnaObaveza> list){
		PredajePredmet pp = new PredajePredmet();
		pp.setGodina(godina);
		pp.setPredmet(predmet);
		pp.setPredispitneObaveze(list);
		pp.setNastavnik(nastavnikRepo.findNastavnikByIme("Bojana"));
				
		return predajePredmetRepo.save(pp);				
	}
	
	public List<PredajePredmet> loadAll(){
		Iterable<PredajePredmet> iter = predajePredmetRepo.findAll();
		List<PredajePredmet> rez = new ArrayList<PredajePredmet>();		
		iter.forEach(rez::add);
		return rez;
	}

	public List<PredajePredmet> loadAllWithStudents(){
		return predajePredmetRepo.loadAllFetchStudenti();
	}	
	
	public List<PredajePredmet> loadlAllForStudent(StudentskiIndeks indeks){
		return predajePredmetRepo.loadAllForStudent(indeks);
	}
	
	public void addStudentaNaPredmet(StudentskiIndeks indeks, PredajePredmet predmetKojiSlusa) {
		predmetKojiSlusa.getStudentiNaPredmetu().add(indeks);
		predajePredmetRepo.save(predmetKojiSlusa);
	}
	
	public List<PredajePredmet> findPredajePredmet(StudentskiIndeks si){
		return predajePredmetRepo.findProvidedSubjectsByIndex(si.getId());
	}	
	
	public PredispitnaObaveza savePredispitnaObaveza(String vrsta, int maxPoena) {
		PredispitnaObaveza obaveza = new PredispitnaObaveza();
		obaveza.setVrsta(vrsta);
		obaveza.setMaxBrojPoena(maxPoena);
		
		return predObavezaRepo.save(obaveza);
	}
}
