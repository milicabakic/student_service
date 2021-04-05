package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Nastavnik;
import studsluzba.model.PredajePredmet;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.StudentskiIndeks;

public interface PredajePredmetRepository extends CrudRepository<PredajePredmet, Integer> {

	@Query("select si.predmetiKojeSlusa from StudentskiIndeks si where "
			+ "si.id like :idIndeksa")
	List<PredajePredmet> findProvidedSubjectsByIndex(int idIndeksa);
	
	@Query("select distinct pp from PredajePredmet pp left join fetch pp.studentiNaPredmetu")
	List<PredajePredmet> loadAllFetchStudenti();
	
	
	@Query("select distinct pp from PredajePredmet pp left join fetch pp.studentiNaPredmetu s where s like :indeks")
	List<PredajePredmet> loadAllForStudent(StudentskiIndeks indeks);
	
	@Query("select distinct pp from PredajePredmet pp left join fetch pp.predispitneObaveze where pp like :predmet")
	PredajePredmet loadPredajePredmetWithObaveze(PredajePredmet predmet);
	
	@Query("select distinct pp from PredajePredmet pp left join fetch pp.studentiNaPredmetu where pp like :predmet")
	PredajePredmet loadPredmetWithStudents(PredajePredmet predmet);
	
	@Query("select pp.nastavnik from PredajePredmet pp where "
			+"pp.predmet like :predmet and "
			+ "pp.godina like :godina")
	Nastavnik findNastavnik(Predmet predmet, SkolskaGodina godina);
	
}
