package thinkbridge.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;




public class common 
{
	public static WebDriver driver;
	public static String browserName;
	public static ArrayList<String> ar; 
	public static Object object;
	public static Map<Object,Object> map;
	public static WebDriver browserInfo(String browser)
	{
		if(browser.equalsIgnoreCase("FireFox"))
		{
			driver=new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");			
			
			driver=new ChromeDriver();			
		}
		else
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer64.exe");			
			driver = new InternetExplorerDriver();
		}
		return driver;
		
	}
	@SuppressWarnings("unchecked")
	public static  String getData(Map<Object,Object> map,String mappingHeader, String sequence) 
	{
		String expectedValue = null;
		try 
		{
			for(int i =0; i<map.size(); i++)
			{
				if (map.get(mappingHeader) != null)
				{
					ar = (ArrayList<String>)(map.get(mappingHeader));
					for(int j =0; j< ar.size(); j++)
					{
						String str = ar.get(j);
						String[] split = str.split("::");
						String key = split[0];
						String val = split[1];
						if(key.equals(sequence))
						{
							if(val.equalsIgnoreCase("BLANK"))
								expectedValue = "";
							else 
								expectedValue = val;

							break;
						}
						//break;
					}
				}
			}
		} 
		catch (Throwable t) 
		{
			t.printStackTrace();
		}
		return expectedValue;
	}
	public static Map init(String file)  
	{
		try
		{
			InputStream inputStream = new FileInputStream(new File(file));
			Yaml yaml = new Yaml(new Constructor(Object.class));
			object = yaml.load(inputStream);
			map = (Map)object;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	public static WebDriver navigateToUrl(String url, WebDriver driver)
	{
		driver.navigate().to(url);
		return driver;
	}
	
	public WebDriver verifyDropDown(WebDriver driver, Map<Object, Object> xmlMap) 
	{
		driver.findElement(By.id("language")).click();
		List<WebElement> eleBW=driver.findElements(By.xpath("//ul/li[@id='ui-select-choices-1']/div"));
		System.out.println(eleBW.size());
		if((eleBW.size())>0)
		{				
			for ( WebElement ce: eleBW) 
			{ 											
				if((ce.getText().equalsIgnoreCase("English"))||(ce.getText().equalsIgnoreCase("Dutch")))						
				{					
					System.out.println("Test case passed .Language as expected is -"+ce.getText());																																										
				}				
			}								
		}
		else
		{
			System.out.println("Test case failed. Language drop down is missing");		
		}
		return driver;
	}

}
