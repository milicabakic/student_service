package studsluzba.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.ObnovaGodine;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;

public interface ObnovaGodineRepository extends CrudRepository<ObnovaGodine, Integer> {

	
	@Query("select o from ObnovaGodine o where "
			+ "o.indeksStudenta like :indeks")
	List<ObnovaGodine> findRenewedYearsByIndex(StudentskiIndeks indeks); 
	
	@Query("select distinct og from ObnovaGodine og left join fetch og.nepolozeniPredmeti where og.indeksStudenta.student like :s")
	List<ObnovaGodine> loadAllObnove(Student s);	
	
}
