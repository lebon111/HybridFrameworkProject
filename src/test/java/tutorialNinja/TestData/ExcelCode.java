package tutorialNinja.TestData;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;



public class ExcelCode {
	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	
	
	@DataProvider (name = "TNLogin")
	public Object[][] getTNExcelLoginData() throws Exception {
		Object[][] data = ExcelCode.readFromExcelSheetTNLogin("LoginTN");  
	return data;
	}
	



	private static Object[][] readFromExcelSheetTNLogin(String sheetName) throws Exception {
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\tutorialNinja\\TestData\\ExcelData.xlsx");
		workbook = new XSSFWorkbook(ip);
		sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][cols];
		for(int i=0; i<rows; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
					
				}
				
			}
		}	
		
return data;
	}
	@DataProvider(name = "TNRegister")
	public Object[][] getTNExcelRegisterData() throws Exception {
		Object[][] data = ExcelCode.readFromExcelSheetTNRegister("RegisterTN");
		return data;
	}
	
	public static Object[][] readFromExcelSheetTNRegister(String sheetName) throws Exception {
		//5. Create the Object of FileInputStream class and pass the path of the Excel sheet in its Constructor
			 ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\tutorialNinja\\TestData\\ExcelData.xlsx");
		//6. Create the Object of XSSFWorkbook class
			 workbook = new XSSFWorkbook(ip);
		//7. use the workbook reference to address individual sheet
			sheet = workbook.getSheet(sheetName);
		//8. identify the rows and cols of the sheet
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
		//9. Create a 2-Dimensional Object Array and then return the Object Array
			Object[][] data = new Object[rows][cols];
			for(int i=0 ; i<rows ; i++) {
				XSSFRow row = sheet.getRow(i+1);
				for(int j=0 ; j<cols ; j++) {
						XSSFCell cell = row.getCell(j);
						CellType celltype = cell.getCellType();
						
						switch(celltype) {
						case STRING:
							data[i][j] = cell.getStringCellValue();
							break;
							
						case NUMERIC:
							data[i][j] = Integer.toString((int)cell.getNumericCellValue());
							break;
							
						case BOOLEAN:
							data[i][j] = cell.getBooleanCellValue();
							break;
						}
				}
			}
			
			return data;
		
		}
	
	


}

