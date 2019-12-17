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

public class Page_loading {
	
	WebDriver driver1;
	ReadExcel Excel1 = new ReadExcel();
	ExtentReport EReport1 = new ExtentReport();

@BeforeTest 
public void setup() throws IOException, ParseException 
{
			EReport1.setUpReport();	
			WebDriverManager.chromedriver().setup();
			driver1 = new ChromeDriver();

			//driver.get("https://vast-dawn-73245.herokuapp.com/");
			driver1.get(Excel1.readExcel(0, 0,".\\data\\Input_data.xlsx","testdata"));
}


@Test
public void Propine_Addition() throws IOException, ParseException
	{
	//Page Loading Testing//
		EReport1.startTestCase("propineCalculator_Loading");
		
		WebDriverWait wait = new WebDriverWait(driver1,50);
		WebElement propineCalculator;
		propineCalculator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src=\"/img/logo.png\"]")));

		
		String webTitle = driver1.getTitle();
		
		if (webTitle.equals("Propine Addition Calculator"))
		{
			EReport1.logEventsPass("propineCalculator Loading pass");
			}else
				{
				EReport1.logEventsFail("propineCalculator Loading failed");
				}
}
@AfterTest
public void tearDown() {
	EReport1.createFinalReport();
	driver1.close();
	driver1.quit();
}
}
