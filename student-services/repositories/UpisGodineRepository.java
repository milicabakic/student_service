package studsluzba.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.SrednjaSkola;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;
import studsluzba.model.UpisGodine;

public interface UpisGodineRepository extends CrudRepository<UpisGodine, Integer> {

	
	@Query("select u from UpisGodine u where "
			+ "u.studentskiIndeks like :indeks")
	List<UpisGodine> findEnrolledYearsByIndex(StudentskiIndeks indeks);
	
	
	@Query("select u.studentskiIndeks.student from UpisGodine u where "
			+ "u.skolskaGodina.aktivna = 1 and "
			+ "u.studentskiIndeks.student.srednjaSkola like :skola")
	List<Student> findEnrolledStudentsByHighSchool(SrednjaSkola skola);

	@Query("select distinct ug from UpisGodine ug left join fetch ug.nepolozenPredmet where ug.studentskiIndeks.student like :s")
	List<UpisGodine> loadAllUpisi(Student s);	
	
}
