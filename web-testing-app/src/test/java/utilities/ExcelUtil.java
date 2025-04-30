package utilities;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtil {

	@DataProvider(name = "positiveTestCases")
	public Object[][] invalidCredentials(Method m) throws IOException {

		File src = new File("your excel file location");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int rowCount = sheet.getLastRowNum();
		int rows = rowCount + 1;
		int cellCount = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][cellCount];

		// Object[][] data = new Object[rowCount][cellCount];
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < cellCount; j++) {
				Cell cell = row.getCell(j);
				if (cell.getCellType() == CellType.NUMERIC) {
					data[i - 1][j] = String.valueOf(cell.getNumericCellValue());

				} else if (cell.getCellType() == CellType.STRING) {
					data[i - 1][j] = cell.getStringCellValue();

				} else {
					data[i][j] = " ";
				}
				System.out.println(data[i - 1][j]);
			}
		}

		wb.close();
		fis.close();

		return data;
	}

}
