package studsluzba.services;

import org.springframework.stereotype.Service;

import studsluzba.model.Student;
import studsluzba.model.UpisGodine;
import studsluzba.repositories.UpisGodineRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UpisGodineService {
	
	@Autowired
	UpisGodineRepository upisGodineRepository;
	
	public UpisGodine saveUpisGodine(UpisGodine ug) {
		return upisGodineRepository.save(ug);
	}

	
	public List<UpisGodine> loadAll(Student s){
		return upisGodineRepository.loadAllUpisi(s);
	}
	
	
	

}
