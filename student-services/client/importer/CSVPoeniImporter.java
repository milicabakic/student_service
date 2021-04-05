package studsluzba.client.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.PredajePredmet;
import studsluzba.model.PredispitnaObaveza;
import studsluzba.model.PredispitniPoeni;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.StudentskiIndeks;
import studsluzba.services.IspitService;
import studsluzba.services.PredajePredmetService;
import studsluzba.services.PredmetService;
import studsluzba.services.SkolskaGodinaService;
import studsluzba.services.StudIndeksService;
import studsluzba.services.StudentService;

@Component
public class CSVPoeniImporter {

	@Autowired StudentService studService;
	
	@Autowired StudIndeksService studIndeksService;
	
	@Autowired 
	PredmetService predmetService;
	
	@Autowired
	SkolskaGodinaService godinaService;
	
	@Autowired
	PredajePredmetService predajePredmetService;
	
	@Autowired
	IspitService ispitService;
	
	
	public String importCSV(File fajl) {
		Scanner sc = null;
		StringBuilder poruka = new StringBuilder();
		
		try {
			sc = new Scanner(fajl,"UTF-8");
						
			//ucitavanje predmeta
			String line1 = sc.nextLine();
			String[] prviDeo = line1.split(",");
			String nazivPredmeta = prviDeo[0];
			Predmet predmet = predmetService.savePredmet(nazivPredmeta);
			
			//ucitavanje godine
			String line2 = sc.nextLine();
			String[] drugiDeo = line2.split(",");
			String[] god = drugiDeo[0].split("/");
			int godPocetna = Integer.parseInt(god[0]);
			int godZavrsna = Integer.parseInt(god[1]);
			SkolskaGodina skolskaGodina = godinaService.saveSkolskaGodina(godPocetna, godZavrsna);
			
			//ucitavanje opstih stvari
			String line3 = sc.nextLine();
			String[] treciDeo = line3.split(",");
			List<PredispitnaObaveza> lista = new ArrayList<PredispitnaObaveza>();
			lista.add(predajePredmetService.savePredispitnaObaveza(treciDeo[5], 25));  //kolokvijum
			lista.add(predajePredmetService.savePredispitnaObaveza(treciDeo[6], 25));  //test
			lista.add(predajePredmetService.savePredispitnaObaveza(treciDeo[7], 10));  //kvizovi
			
			PredajePredmet predajePredmet = predajePredmetService.savePredajePredmet(skolskaGodina, predmet, lista);
			
			IspitniRok rokJun = ispitService.saveIspitniRok(treciDeo[8], skolskaGodina);
			IspitniRok rokJul = ispitService.saveIspitniRok(treciDeo[9], skolskaGodina);
			IspitniRok rokAvg = ispitService.saveIspitniRok(treciDeo[10], skolskaGodina);
			IspitniRok rokSept = ispitService.saveIspitniRok(treciDeo[11], skolskaGodina);
			
			Ispit ispitJun = ispitService.saveIspit(predmet, rokJun);
			Ispit ispitJul = ispitService.saveIspit(predmet, rokJul);
			Ispit ispitAvg = ispitService.saveIspit(predmet, rokAvg);
			Ispit ispitSept = ispitService.saveIspit(predmet, rokSept);
			
			
			//ucitavanje studenata, indeksa...
			int brojSacuvanihStudenata = 0;
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] delovi = line.split(",");
				String studProgram = delovi[0];
				int broj = Integer.parseInt(delovi[1]);
				int godina = Integer.parseInt(delovi[2]);
				String prezime = delovi[3];
				String ime = delovi[4];
	//			StudentskiIndeks si = studIndeksService.findStudIndeks(studProgram, broj, godina);
				StudentskiIndeks si = null;
				if(si == null) {  
					si = studService.saveStudentAndIndex(ime, prezime, studProgram, broj, godina, predajePredmet);
					brojSacuvanihStudenata++; 
				}
				
				if(delovi.length == 5) {
					for(int i=0; i<3; i++) 
						studService.savePredispitniPoeniForStudent(si, lista.get(i), predajePredmet, 0);
					
				}
				else {
					//upis predispitnih poena
					List<PredispitniPoeni> poeniList = new ArrayList<PredispitniPoeni>();
					int ukupnePredispitne = 0;
					for(int i=0; i<3; i++) {
						if(delovi.length < 6 + i) 
							break;
						
					    float osvojeniPoeni;
					
						try {
							osvojeniPoeni = Float.parseFloat(delovi[6+i-1]);
						}
						catch(Exception e) {
							osvojeniPoeni = 0;
						}
						
						poeniList.add(studService.savePredispitniPoeniForStudent(si, lista.get(i), predajePredmet, osvojeniPoeni));
						ukupnePredispitne += osvojeniPoeni;
					}
					studService.saveIndeksWithPoeni(si, poeniList);		
										
					//upisivanje ispita
					for(int i=0; i<4; i++) {
						if(delovi.length < 9 + i)
							break;
						
						boolean ponistava = false;
						if(delovi.length > 9 + i)
							ponistava = true;
						
						float poeniIspit;
						if(delovi[9+i-1].isEmpty())
							poeniIspit = 0;
						else
							poeniIspit = Float.parseFloat(delovi[9+i-1]);
						
						if(i==0) {
							ispitService.savePrijavljenIspit(si, ispitJun, true);
							ispitService.saveIzlazakNaIspit(si, ispitJun,poeniIspit,ponistava, 
									ukupnePredispitne, poeniList);
						}
						else if(i==1) {
							ispitService.savePrijavljenIspit(si, ispitJul, true);
							ispitService.saveIzlazakNaIspit(si, ispitJul,poeniIspit,ponistava,
									ukupnePredispitne, poeniList);
                        }
						else if(i==2) {
							ispitService.savePrijavljenIspit(si, ispitAvg, true);
							ispitService.saveIzlazakNaIspit(si, ispitAvg,poeniIspit,ponistava,
									ukupnePredispitne, poeniList);
						}
						else {
							ispitService.savePrijavljenIspit(si, ispitSept, true);
							ispitService.saveIzlazakNaIspit(si, ispitSept,poeniIspit,ponistava,
									ukupnePredispitne, poeniList);
						}
						
						//ako nije vise izlazio i ako ima dovoljno poena, cuva se polozen predmet
						if((delovi.length == 9 + i) && (ukupnePredispitne+poeniIspit >= 50))
							ispitService.savePolozenPredmet(si, ukupnePredispitne+poeniIspit, 
									predmet, false);	
							
					}
				}
			}
			poruka.append("Broj sacuvanih studenata: ");
			poruka.append(brojSacuvanihStudenata);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		
		}
		return poruka.toString();
	}
}
