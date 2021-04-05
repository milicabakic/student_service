package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.StudProgram;

public interface StudProgramRepository extends CrudRepository<StudProgram, Integer> {


	@Query("select s from StudProgram s where s.skraceniNaziv like :skraceniNaziv")
	StudProgram findStudProgramBySkraceniNaziv(String skraceniNaziv);
	
}
