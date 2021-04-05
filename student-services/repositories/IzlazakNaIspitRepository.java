package studsluzba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.IzlazakNaIspit;
import studsluzba.model.Predmet;
import studsluzba.model.Student;
import studsluzba.model.StudentskiIndeks;

public interface IzlazakNaIspitRepository extends CrudRepository<IzlazakNaIspit, Integer> {

	
    @Query("select avg(i.ocena) from IzlazakNaIspit i where "
    		+ "i.ispit like :ispit")
	double findAvgMarkOnExam(Ispit ispit);


    @Query("select count(i) from IzlazakNaIspit i where "
    		+ "i.ispit.predmet like :p and "
    		+ "i.studentskiIndeks.student like :s")
    int countIzlazakNaIspitForStudent(Student s, Predmet p);
    
    @Query("select i from IzlazakNaIspit i where "
    		+ "i.ispit like :ispit "
    		+ "order by i.studentskiIndeks.studProgram.naziv asc, i.studentskiIndeks.godina asc, i.studentskiIndeks.broj asc")
    List<IzlazakNaIspit> sortStudents(Ispit ispit);
    
    @Query("select i.studentskiIndeks.student from IzlazakNaIspit i where "
    		+ "i.ispit.ispitniRok like :rok and "
    		+ "i.ispit.predmet like :predmet")
    List<Student> findStudentsForIzlazakNaIspit(IspitniRok rok, Predmet predmet);
    
    @Query("select i.studentskiIndeks from IzlazakNaIspit i where "
    		+ "i.ispit.ispitniRok like :rok and "
    		+ "i.ispit.predmet like :predmet")
    List<StudentskiIndeks> findIndeksiForIzlazakNaIspit(IspitniRok rok, Predmet predmet);
    
    @Query("select sa.ukupanBrojPoena from IzlazakNaIspit sa where sa.studentskiIndeks like :si and sa.ispit like :i")
    int findPoeni(StudentskiIndeks si, Ispit i);
    
    @Query("select sa.ocena from IzlazakNaIspit sa where sa.studentskiIndeks like :si and sa.ispit like :i")
    int findOcena(StudentskiIndeks si, Ispit i);
    
    @Query("select sa.napomena from IzlazakNaIspit sa where sa.studentskiIndeks like :si and sa.ispit like :i")
    String findNapomena(StudentskiIndeks si, Ispit i);
}
