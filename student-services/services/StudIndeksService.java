package studsluzba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.StudentskiIndeks;
import studsluzba.repositories.StudentskiIndeksRepository;

@Service
public class StudIndeksService {
	
	@Autowired
	StudentskiIndeksRepository studIndeksRepo;
	
	
	public StudentskiIndeks saveStudIndeks(StudentskiIndeks si){
		return studIndeksRepo.save(si);				
	}
	
	public StudentskiIndeks findStudIndeksForUpisGodine(StudentskiIndeks si) {
		return studIndeksRepo.findStudIndeksForUpisGodine(si);
	}
	
	public StudentskiIndeks findStudIndeksForObnovaGodine(StudentskiIndeks si) {
		return studIndeksRepo.findStudIndeksForObnovaGodine(si);
	}
	
	public void updateStudIndeks(StudentskiIndeks si) {
		studIndeksRepo.save(si);
	}
	
	public StudentskiIndeks findStudIndeks(String skraceniProgram, int broj, int godina) {
		return studIndeksRepo.findIndex(skraceniProgram, godina, broj);
	}

}
