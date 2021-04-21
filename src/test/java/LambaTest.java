import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;




public class LambaTest {
	
	@Test
	public void amazon_lg() throws org.openqa.selenium.WebDriverException
	{
		//String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "/Users/shayan/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("LG washing machine");
		
		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[1]")).click();		
		//driver.findElement(By.xpath("//i[@class='a-icon a-icon-dropdown']")).click()		
		String xpaths_of_prices = "//*[@class='a-price-whole']";	
		List<WebElement> prices = driver.findElements(By.xpath(xpaths_of_prices));		
		double[] values = new double[prices.size()];
		String[] values_texts = new String[prices.size()];
		int index = 0;
		for (WebElement price : prices)
		{
			values_texts[index]= price.getText().replace(",","");
			values[index]=Double.parseDouble(price.getText().replace(",",""));
			index=index+1;
		}		
		int i,j;
		double c=0;
		for(i=1; i<values.length;i++)
		{
			for(j=0;j<values.length-i;j++)
			{
				if(values[j]<values[j+1])
				{
					c=values[j];
					values[j]=values[j+1];
					values[j+1]=c;
				}
				c=0;
			}
		}		
		
		String xpath_of_names="//span[@class='a-size-medium a-color-base a-text-normal']";
		
		List<WebElement> names = driver.findElements(By.xpath(xpath_of_names));
		int k=0;
		System.out.println("Price                           Name");
		for(i=0; i<values_texts.length;i++)
		{
			k=get_index_of_element(values_texts, values[i]);
			System.out.println(values[i]+"   "+names.get(k).getText());
			
		}
		driver.quit();
		
	}
	public int get_index_of_element(String []ar, double element)
	{
		int index=0;
		for(int i=0;i<ar.length;i++)
		{
			if(Double.parseDouble(ar[i])==element)
			{
				index = i;
				break;
			}
		}
		return index;
	}
}


