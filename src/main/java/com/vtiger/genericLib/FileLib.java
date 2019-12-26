package com.vtiger.genericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author kamlesh
 *
 */
public class FileLib {
	/**
	 * reaad the dta from commondata properties file
	 * @throws IOException 
	 * @throws Throwable 
	 * 
	 */
	public String getPropertyValue(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String value=pObj.getProperty(key);
		return value;
	}
	/*public String getPropertyValue(String key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String value=pObj.getProperty(key);
		
		return value;
	}*/
	
	/**
	 * 
	 * read the data from excel
	 * @throws Throwable 
	 * @throws Throwable 
	 * @throws EncryptedDocumentException 
	 */
	public String getExcelData(String sheetName,int rowNum,int colNum) throws EncryptedDocumentException, Throwable
	{
		FileInputStream excelPath=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(excelPath);	
		Sheet sh=wb.getSheet(sheetName);
     	Row row=sh.getRow(rowNum);
		String data=row.getCell(colNum).getStringCellValue();
		
		return data;
		
	}

}
