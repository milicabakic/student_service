package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.SrednjaSkola;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.model.VrstaStudija;
import studsluzba.repositories.SrednjaSkolaRepository;
import studsluzba.repositories.VisokoskolskaUstanovaRepository;
import studsluzba.repositories.VrstaStudijaRepository;

@Service
public class SifarniciService {
	
	@Autowired
	SrednjaSkolaRepository srednjeSkolaRepo;
	
	@Autowired
	VisokoskolskaUstanovaRepository visUstanovaRepo;
	
	@Autowired
	VrstaStudijaRepository vrstaStudijaRepo;
	
	
	
	public List<SrednjaSkola> getSrednjeSkole(){		
		Iterable<SrednjaSkola> iter = srednjeSkolaRepo.findAll();
		List<SrednjaSkola> rez = new ArrayList<SrednjaSkola>();		
		iter.forEach(rez::add);
		return rez;		
	}
	
	public SrednjaSkola saveSrednjaSkola(SrednjaSkola ss) {
		return srednjeSkolaRepo.save(ss);
	}
	
	
	public List<VisokoskolskaUstanova> getVisokoskolskeUstanove(){
		Iterable<VisokoskolskaUstanova> iter = visUstanovaRepo.findAll();
		List<VisokoskolskaUstanova> rez = new ArrayList<VisokoskolskaUstanova>();
		iter.forEach(rez::add);
		return rez;		
	}
	
	public VisokoskolskaUstanova saveVisokoskolskaUstanova(VisokoskolskaUstanova vu) {
		return visUstanovaRepo.save(vu);
	}
	
	
	public List<VrstaStudija> getVrsteStudija(){
		Iterable<VrstaStudija> iter = vrstaStudijaRepo.findAll();
		List<VrstaStudija> rez = new ArrayList<VrstaStudija>();
		iter.forEach(rez::add);
		return rez;
	}
	
	public VrstaStudija saveVrstaStudija(VrstaStudija vs) {
		return vrstaStudijaRepo.save(vs);
	}
}
