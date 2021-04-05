package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Predmet;
import studsluzba.model.StudProgram;

public interface PredmetRepository extends CrudRepository<Predmet, Integer> {

	
	@Query("select p from Predmet p where "
			+ "p.studProgram like :smer")
	List<Predmet> findSubjectsOnSection(StudProgram smer);

}
