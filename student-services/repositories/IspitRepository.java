package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.Predmet;

public interface IspitRepository extends CrudRepository<Ispit, Integer> {
	
	@Query("select i from Ispit i where i.predmet like :p and i.ispitniRok like :r")
	Ispit findIspitByPredmetNastavnikIspitniRok(Predmet p, IspitniRok r);
	

}
