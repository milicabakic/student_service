package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;

public interface StudentskiIndeksRepository extends CrudRepository<StudentskiIndeks, Integer> {

	
	@Query("select si.student from StudentskiIndeks si where "
			+ "si like :indeks")
	List<Student> findStudentsByIndex(StudentskiIndeks indeks);

	
	@Query("select si.student from StudentskiIndeks si where "
			+"si.broj = :broj and si.studProgram.skraceniNaziv like :skraceni and "
			+ "si.godina = :godina")
	List<Student> findStudentByIndexString(String skraceni, int godina, int broj);
	
	
	@Query("select distinct si from StudentskiIndeks si left join fetch si.predmetiKojeSlusa")
	List<StudentskiIndeks> loadlAllWithPredmetiKojeSlusa();

	
	@Query("select si from StudentskiIndeks si left join fetch si.upisaneGodine ug where si like :index")
	StudentskiIndeks findStudIndeksForUpisGodine(StudentskiIndeks index);
	
	@Query("select si from StudentskiIndeks si left join fetch si.obnovljeneGodine og where si like :index")
	StudentskiIndeks findStudIndeksForObnovaGodine(StudentskiIndeks index);
	
	@Query("select si from StudentskiIndeks si where "
			+"si.broj = :broj and si.studProgram.skraceniNaziv like :skraceni and "
			+ "si.godina = :godina")
	StudentskiIndeks findIndex(String skraceni, int godina, int broj);
	
	@Query("select distinct si from StudentskiIndeks si left join fetch si.polozeniPredmeti where si like :indeks")
	StudentskiIndeks findIndeksWithPolozeniPredmeti(StudentskiIndeks indeks);
	
	@Query("select distinct si from StudentskiIndeks si left join fetch si.izlasciNaIspit where si like :indeks")
	StudentskiIndeks findIndeksWithIzlasciNaIspit(StudentskiIndeks indeks);
	
	@Query("select distinct si from StudentskiIndeks si left join fetch si.predmetiKojeSlusa where si like :indeks")
	StudentskiIndeks findIndeksWithPredmetiKojeSlusa(StudentskiIndeks indeks);
	
	@Query("select distinct si from StudentskiIndeks si left join fetch si.predispitniPoeni where si like :indeks")
	StudentskiIndeks findIndeksWithPoeniList(StudentskiIndeks indeks);
}
