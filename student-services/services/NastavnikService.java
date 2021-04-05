package studsluzba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.Nastavnik;
import studsluzba.model.ObrazovanjeNastavnika;
import studsluzba.model.VisokoskolskaUstanova;
import studsluzba.model.VrstaStudija;
import studsluzba.model.Zvanje;
import studsluzba.repositories.NastavnikRepository;
import studsluzba.repositories.ObrazovanjeNastavnikaRepository;
import studsluzba.repositories.ZvanjeRepository;

@Service
public class NastavnikService {
	
	@Autowired
	NastavnikRepository nastavnikRepo;
	
	@Autowired
	ObrazovanjeNastavnikaRepository obrazovanjeNastavnikaRepo;
	
	@Autowired
	ZvanjeRepository zvanjeRepo;
    
	
	public List<Nastavnik> loadAll(){
		Iterable<Nastavnik> iter = nastavnikRepo.findAll();
		List<Nastavnik> res = new ArrayList<Nastavnik>();
		iter.forEach(res::add);
		return res;
	}
	
	public Nastavnik saveNastavnik(String ime,String prezime,String srednjeIme,String email, 
			List<Zvanje> zvanja, List<ObrazovanjeNastavnika> obrazovanje) {
		Nastavnik n = new Nastavnik();
		n.setIme(ime);
		n.setPrezime(prezime);
		n.setSrednjeIme(srednjeIme);
		n.setEmail(email);
        n.setZvanje(zvanja);
        n.setZvanje(zvanja);
		return nastavnikRepo.save(n);
	}
	
	public void updateNastavnik(Nastavnik n) {
		nastavnikRepo.save(n);
	}
	
	public void updateZvanje(Nastavnik nastavnik, List<Zvanje> zvanja) {
		for(Zvanje z : zvanja) {
			if(z.getNastavnik() != null)
				continue;
			z.setNastavnik(nastavnik);
			zvanjeRepo.save(z);
		}
	}
	
	public void updateObrazovanje(Nastavnik nastavnik, List<ObrazovanjeNastavnika> obrazovanja) {
		for(ObrazovanjeNastavnika o : obrazovanja) {
			if(o.getNastavnik() != null)
				continue;
			o.setNastavnik(nastavnik);
			obrazovanjeNastavnikaRepo.save(o);
		}
	}
	
	public Zvanje saveZvanjeNastavnika(String naziv, Date datumIzbora, String naucnaOblast) {
		Zvanje z = new Zvanje();
		z.setNaziv(naziv);
		z.setDatumIzbora(datumIzbora);
		z.setNaucnaOblast(naucnaOblast);
		System.out.println("service");
		System.out.println(z);
		return zvanjeRepo.save(z);
	}
	
	public List<Zvanje> loadAllZvanjaNastavnika(){
		Iterable<Zvanje> iter = zvanjeRepo.findAll();
		List<Zvanje> rez = new ArrayList<Zvanje>();		
		iter.forEach(rez::add);
		return rez;
	}

	public ObrazovanjeNastavnika saveObrazovanjeNastavnika(VrstaStudija vs, VisokoskolskaUstanova vu) {
		ObrazovanjeNastavnika on = new ObrazovanjeNastavnika();
		on.setVrstaStudija(vs);
		on.setZavrsenaSkola(vu);
		return obrazovanjeNastavnikaRepo.save(on);
	}
	
	public List<ObrazovanjeNastavnika> loadAllObrazovanjeNastavnika(){
		Iterable<ObrazovanjeNastavnika> iter = obrazovanjeNastavnikaRepo.findAll();
		List<ObrazovanjeNastavnika> rez = new ArrayList<ObrazovanjeNastavnika>();		
		iter.forEach(rez::add);
		return rez;
	}	
	
	public Zvanje addZvanjeNastavnika(Nastavnik n, String naziv, Date datum, String naucnaOblast) {
		Zvanje z = new Zvanje();
		z.setNastavnik(n);
		z.setNaziv(naziv);
		z.setDatumIzbora(datum);
		z.setNaucnaOblast(naucnaOblast);
		zvanjeRepo.save(z);
	//	n.getZvanje().add(z);
	//	nastavnikRepo.save(n);
		return z;
	}
	
}
