package studsluzba.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.StudProgram;
import studsluzba.repositories.StudProgramRepository;

@Service
public class StudProgramService{
	
	@Autowired
	StudProgramRepository studProgramRepo;
	
	public StudProgram saveStudProgram(String punNaziv, String skraceniNaziv){
		StudProgram sp = new StudProgram();
		sp.setNaziv(punNaziv);
		sp.setSkraceniNaziv(skraceniNaziv);
		return studProgramRepo.save(sp);				
	}
	
	public List<StudProgram> loadAll(){
		Iterable<StudProgram> iter = studProgramRepo.findAll();
		List<StudProgram> rez = new ArrayList<StudProgram>();		
		iter.forEach(rez::add);
		return rez;
	}
	
	
	
	
	

}
