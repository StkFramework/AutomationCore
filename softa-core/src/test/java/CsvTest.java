

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.softtek.automation.functions.CSVFunctions;



public class CsvTest {
	private CSVFunctions<CsvTest> csvFunction = new CSVFunctions<CsvTest>();
	
	@Ignore
	public void getAllInformation() throws Exception {
		List<String[]> list = csvFunction.getInfo("C://Test.csv");
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.print(string+" ");
			}
			System.out.println();
		}
		
	}
	
	@Ignore
	public void getAllInformationWithSeparator() throws Exception {
		List<String[]> list = csvFunction.getInfo("C://Test.csv", ',');
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.print(string+" ");
			}
			System.out.println();
		}
		
	}
	
	@Ignore
	public void updateCsv() throws Exception {
		csvFunction.updateCSV("C://Test.csv", "Test", 4, 6);
	}
	
	@Test
	public void getSpecificCell() throws Exception {
		String getValue = csvFunction.obtainCSVValue("C://Test.csv", 4, 6);
		System.out.println(getValue);
	}

}
