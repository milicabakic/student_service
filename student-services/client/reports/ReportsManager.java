package studsluzba.client.reports;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import studsluzba.client.MainViewManager;
import win.zqxu.jrviewer.JRViewerFX;

@Component
public class ReportsManager {
	
	@Autowired
	MainViewManager mainViewManager;
	
	
	public void openReport(Collection<?> input, Map<String,Object> params, String jasperFileName) {
		JRDataSource dataSource = new JRBeanCollectionDataSource(input);		
		
		JasperPrint document;
		try {
			document = JasperFillManager
					.fillReport(this.getClass()
							.getResourceAsStream("/jasper/"+jasperFileName+".jasper"), params, dataSource);
			JRViewerFX.preview(mainViewManager.getMainStage(), document);
		} catch (JRException e) {			
			e.printStackTrace();
		}
	}

}
