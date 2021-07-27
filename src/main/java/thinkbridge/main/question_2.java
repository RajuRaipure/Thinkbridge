package thinkbridge.main;

import java.util.Map;


import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.By;


import thinkbridge.common.common;

public class question_2 extends common
{
	public static Map<Object,Object> xmlMap;
	@Before
	public  void setTestResults()
	{
			
		String dataFile="C:\\Users\\rraipure\\eclipse-workspace\\thinkbridge\\Resources\\data.yml";
		xmlMap=init(dataFile);				
		driver=browserInfo(getData(xmlMap, "BrowserInfo", "BrowserDriver"));	
		
	}
	@Test()
	public  void question_2_Test() throws InterruptedException  	
	{				
		driver=navigateToUrl(getData(xmlMap, "ThinkbridegURL", "URL"),driver);
		driver=verifyDropDown(driver,xmlMap);
		driver.findElement(By.name("name")).sendKeys("Raju Raipure");
		driver.findElement(By.id("orgName")).sendKeys("Thinkbridge Raju Raipure");
		driver.findElement(By.id("singUpEmail")).sendKeys("raj.rai786@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'I agree to the')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Get Started')]")).click();		
		Thread.sleep(10000);
		String welcomText=driver.findElement(By.xpath("//form[@name='signUpForm']/div/span")).getText();
		System.out.println(welcomText);
		if(welcomText.contains("A welcome email has been sent. Please check your email."))
		{
			System.out.println("Test case pass. Email sent for confirmation");
		}
		else
		{
			System.out.println("Test case fail. Email is not sent for confirmation");
		}
	}	
	
	@After
	public  void tearDown() 
	{		
			driver.quit();
	}

}
