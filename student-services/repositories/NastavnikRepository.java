package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Nastavnik;

public interface NastavnikRepository extends CrudRepository<Nastavnik, Integer> {
	
	@Query("select n from Nastavnik n where n.ime like :ime")
	Nastavnik findNastavnikByIme(String ime);

}
