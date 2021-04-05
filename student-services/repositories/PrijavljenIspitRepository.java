package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Ispit;
import studsluzba.model.PrijavljenIspit;
import studsluzba.model.Student;

public interface PrijavljenIspitRepository extends CrudRepository<PrijavljenIspit, Integer> {

	@Query("select p.indeksStudenta.student from PrijavljenIspit p where "
			+ "p.ispit like :ispit")
	List<Student> findSignedStudentsForExam(Ispit ispit);

	@Query("select p from PrijavljenIspit p where "
			+ "p.ispit like :ispit")
	List<PrijavljenIspit> findAllPrijavljeniIspitiForIspit(Ispit ispit);
	
}
