package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.SkolskaGodina;
import studsluzba.repositories.SkolskaGodinaRepository;

@Service
public class SkolskaGodinaService {

	
	@Autowired
	SkolskaGodinaRepository godinaRepo;
	
	
	public SkolskaGodina saveSkolskaGodina(int godina1, int godina2, String aktivna){
		SkolskaGodina god = new SkolskaGodina();
		god.setGodinaPocetna(godina1);
		god.setGodinaZavrsna(godina2);
		if(aktivna.equals("DA")) {
			findAndChangdeLastActiveSkolskaGodina();
			god.setAktivna(true);
		}
		else
			god.setAktivna(false);
		
		return godinaRepo.save(god);
	}
	
	public SkolskaGodina saveSkolskaGodina(int godina1, int godina2){
		SkolskaGodina god = new SkolskaGodina();
		god.setGodinaPocetna(godina1);
		god.setGodinaZavrsna(godina2);
		god.setAktivna(false);
		return godinaRepo.save(god);
	}
	
	public List<SkolskaGodina> loadAll(){
		Iterable<SkolskaGodina> iter = godinaRepo.findAll();
		List<SkolskaGodina> rez = new ArrayList<SkolskaGodina>();		
		iter.forEach(rez::add);
		return rez;
	}	
	
	private void findAndChangdeLastActiveSkolskaGodina() {
		List<SkolskaGodina> list = loadAll();
		for(SkolskaGodina sg : list) {
			if(sg.isAktivna()) {
				sg.setAktivna(false);
				godinaRepo.save(sg);
			}
		}
	}
	
}
