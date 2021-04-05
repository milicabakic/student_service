package studsluzba.coders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Šifarnici koji se čitaju iz tektualnog fajla gde je jedan red šifra u šifarniku
 * primer: države, mesta
 * @author bojanads
 *
 */
public class TextFileSimpleCoder extends Coder<SimpleCode> {
	
	private String filePath;
	
	public TextFileSimpleCoder(String filePath) {
		this.filePath = filePath;
		loadCodes();
	}
	
	@Override
	protected void loadCodes() {
		if(codes!= null) return;
		codes = new ArrayList<SimpleCode>();
		Resource resource = new ClassPathResource(filePath);
		Scanner scanner = null;
		try {
			File finput = resource.getFile();
			scanner = new Scanner(finput, "utf-8");
			while(scanner.hasNext()) {
				String code = scanner.nextLine();
				codes.add(new SimpleCode(code));
			}
		} catch (IOException e) {
			// TODO log
			e.printStackTrace();
		}finally {
			scanner.close();
		}
		
	}

}
