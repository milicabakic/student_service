package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.PredajePredmet;
import studsluzba.model.PredispitniPoeni;
import studsluzba.model.StudentskiIndeks;

public interface PredispitniPoeniRepository extends CrudRepository<PredispitniPoeni, Integer> {

	
	@Query("select p from PredispitniPoeni p where "
			+ "p.predmetKojiSlusa like :p"  + " and "
			+ "p.indeks like :indeks")
	List<PredispitniPoeni> getPredispitniPoeni(PredajePredmet p, StudentskiIndeks indeks);

}
