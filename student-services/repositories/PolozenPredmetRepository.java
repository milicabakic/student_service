package studsluzba.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.PolozenPredmet;
import studsluzba.model.Predmet;
import studsluzba.model.StudentskiIndeks;

public interface PolozenPredmetRepository extends CrudRepository<PolozenPredmet, Integer> {

	
	@Query("select pp from PolozenPredmet pp where "
			+ "pp.indeksStudenta like :indeks")
	List<PolozenPredmet> findPassedSubjectsByIndex(StudentskiIndeks indeks);
	
	
	@Query("select avg(pp.ocena) from PolozenPredmet pp where "
			+ "pp.predmet like :p and "
			+ "(pp.godina >= :god1 and pp.godina <= :god2)")
	int avgMarkForYearInterval(int god1, int god2, Predmet p);
	
}
