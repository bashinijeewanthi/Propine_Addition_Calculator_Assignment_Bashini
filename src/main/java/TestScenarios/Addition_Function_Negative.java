package TestScenarios;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;
import ExtentReport.ExtentReport;
import ReadExcel.ReadExcel;


public class Addition_Function_Negative {
	WebDriver driver;
	ReadExcel ExcelF = new ReadExcel();
	ExtentReport EReport = new ExtentReport();

	
@BeforeTest 
public void setup() throws IOException, ParseException 
{
			EReport.setUpReport();	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(ExcelF.readExcel(0, 0,".\\data\\Input_data.xlsx","testdata"));
}

@Test
public void Propine_Addition_Negative() throws IOException, ParseException
{
	EReport.startTestCase("propineCalculator_Loading");
	
	WebDriverWait wait = new WebDriverWait(driver,50);
	WebElement propineCalculator;
	propineCalculator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src=\"/img/logo.png\"]")));

	// Enter input values from Excel sheet//
	driver.findElement(By.name("firstNumber")).sendKeys(ExcelF.readExcel(3, 0,".\\data\\Input_data.xlsx","testdata"));

	driver.findElement(By.name("secondNumber")).sendKeys(ExcelF.readExcel(3, 1,".\\data\\Input_data.xlsx","testdata"));

	driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/input")).click();
	
	// Verify Expected result with Actual Result//
	WebElement result1 = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
	String ExpResult1 = ExcelF.readExcel(3, 2,".\\data\\Input_data.xlsx", "testdata");
		
	if (result1.equals(ExpResult1))
	{
		EReport.logEventsPass("Addition_function_Negative pass");
		}else
			{
			EReport.logEventsFail("Addition_function_Negative failed");
			}
}

@AfterTest
public void tearDown() {
	EReport.createFinalReport();
	driver.close();
	driver.quit();
}
}
