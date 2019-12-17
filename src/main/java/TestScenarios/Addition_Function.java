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


public class Addition_Function {
	
	WebDriver driver;
	ReadExcel Excel = new ReadExcel();
	ExtentReport EReport = new ExtentReport();

	
@BeforeTest 
public void setup() throws IOException, ParseException 
{
			EReport.setUpReport();	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.get(Excel.readExcel(0, 0,".\\data\\Input_data.xlsx","testdata"));
}

@Test
public void Propine_Addition() throws IOException, ParseException
{
	EReport.startTestCase("propineCalculator_Loading");
	
	WebDriverWait wait = new WebDriverWait(driver,50);
	WebElement propineCalculator;
	propineCalculator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src=\"/img/logo.png\"]")));

	// Enter input values from Excel sheet//
	driver.findElement(By.name("firstNumber")).sendKeys(Excel.readExcel(2, 0,".\\data\\Input_data.xlsx","testdata"));

	driver.findElement(By.name("secondNumber")).sendKeys(Excel.readExcel(2, 1,".\\data\\Input_data.xlsx","testdata"));

	driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form/input")).click();
	
	// Verify Expected result with Actual Result//
	WebElement result = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
	String ExpResult = Excel.readExcel(2, 2,".\\data\\Input_data.xlsx", "testdata");
	//String ExpResult = "30";		
	if (result.equals(ExpResult))
	{
		EReport.logEventsPass("Addition_function pass");
		}else
			{
			EReport.logEventsFail("Addition_function failed");
			}
}

@AfterTest
public void tearDown() {
	EReport.createFinalReport();
	driver.close();
	driver.quit();
}
}
