package vtiger.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author Akash Deb
 *
 */
public class ExcelUtility {
	/**
	 * 
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static String fetchStringDataFromExcelSheet(String sheetName, int row, int cell)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
	}
	/**
	 * 
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static long fetchNumericDataFromExcelSheet(String sheetName, int row, int cell)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		return (long) WorkbookFactory.create(fis).getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
	}
	/**
	 * 
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static boolean fetchBooleanDataFromExcelSheet(String sheetName, int row, int cell)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(row).getCell(cell).getBooleanCellValue();
	}
	
	public static String fetchDateDataFromExcelSheet(String sheetName, int row, int cell)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(row).getCell(cell).getLocalDateTimeCellValue().toString().substring(0, 10);
	}
	

}
