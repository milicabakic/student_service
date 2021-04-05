package studsluzba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studsluzba.model.ObnovaGodine;
import studsluzba.model.Student;
import studsluzba.repositories.ObnovaGodineRepository;

@Service
public class ObnovaGodineService {
	
	@Autowired
	ObnovaGodineRepository obnovaGodineRepository;
	
	public List<ObnovaGodine> loadAllObnove(Student s){
		return obnovaGodineRepository.loadAllObnove(s);
	}
	
	public ObnovaGodine saveObnovaGodine(ObnovaGodine og){
		return obnovaGodineRepository.save(og);
	}

}
