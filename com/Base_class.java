package Mini_Project.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Base_class {

	public static WebDriver s;

	public static String value;

	public static WebDriver getBrowser(String type) {

		if (type.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");

			s = new ChromeDriver();

		} else if (type.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		}
		s.manage().window().maximize();

		return s;

	}

	public static void clickonElement(WebElement element) {

		element.click();

	}

	public static void inputvalueElement(WebElement element, String value) {

		element.sendKeys(value);
	}

	public static void url(String url) {

		s.get(url);
	}

	public static void Scroll(String type) {
		JavascriptExecutor js = (JavascriptExecutor) s;

		if (type.equalsIgnoreCase("scrolldown")) {
			js.executeScript("window.scrollIntoView", "");
		} else if (type.equalsIgnoreCase("scrollup")) {
			js.executeScript("window.scrollBy(0,-500)", "");

		}
	}

	public static void screenShort(String location) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) s;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File path = new File(location);
		FileUtils.copyFile(source, path);

	}

	public static void sleep(int milliseconds) throws InterruptedException {

		Thread.sleep(milliseconds);

	}

	public static void frames(String type, String value, WebElement element) {

		if (type.equalsIgnoreCase("index")) {

			int num = Integer.parseInt(value);

			s.switchTo().frame(num);
		} else if (type.equalsIgnoreCase("id")) {
			s.switchTo().frame(value);
		} else if (type.equalsIgnoreCase("element")) {
			s.switchTo().frame(element);
		} else if (type.equalsIgnoreCase("defaultcontent")) {
			s.switchTo().defaultContent();
		}

	}

	public static void dropdown(String type, WebElement element, String value) {

		Select s = new Select(element);

		if (type.equalsIgnoreCase("value")) {

			s.selectByValue(value);
		} else if (type.equalsIgnoreCase("index")) {

			int index = Integer.parseInt(value);

			s.selectByIndex(index);
		} else if (type.equalsIgnoreCase("text")) {

			s.selectByVisibleText(value);
		}

	}

	public static String particular_data(String pathname, int row_index, int cell_index) throws IOException {

		File f = new File(pathname);

		FileInputStream fis = new FileInputStream(f);

		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheetAt = wb.getSheetAt(0);

		Row row = sheetAt.getRow(row_index);

		Cell cell = row.getCell(cell_index);

		CellType cellType = cell.getCellType();

		if (cellType.equals(cellType.STRING)) {

			value = cell.getStringCellValue();

		} else if (cellType.equals(cellType.NUMERIC)) {
			
			double cellValue = cell.getNumericCellValue();
			
			value =String.valueOf(cellValue);

		}
		return value;

	}
	

}
