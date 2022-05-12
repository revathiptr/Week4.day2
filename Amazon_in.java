package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*Amazon:
1.Load the URL https://www.amazon.in/
2.search as oneplus 9 pro 
3.Get the price of the first product
4. Print the number of customer ratings for the first displayed product
5. Click the first text link of the first image
6. Take a screen shot of the product displayed
7. Click 'Add to Cart' button
8. Get the cart subtotal and verify if it is correct.
9.close the browser*/
public class Amazon_in {

	public static void main(String[] args) throws IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		List<WebElement> rates = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		//System.out.println(rates.size());
		String rateOfProduct1=null;
		for(int i=0;i<rates.size();i++)
		{
			//String text = e.getText();
			//System.out.println(text.isEmpty());
			if(i==0)
			{
				rateOfProduct1 = rates.get(i).getText();
				System.out.println("Price of the first Product: "+ rateOfProduct1);
			}
		}
		//List<WebElement> ratings = driver.findElements(By.className("a-icon-alt"));
		//picking rating of all the products in a list
		///////List<WebElement> ratings1 =driver.findElements(By.xpath("//span[@class='a-icon-alt']"));
		List<WebElement> ratings1 =driver.findElements(By.xpath("//div[@class='a-row a-size-small']/span[1]"));
		//System.out.println(ratings1.size()); 
		for(int i=0;i<ratings1.size();i++) 
		{
			String text = ratings1.get(i).getAttribute("aria-label");
			//System.out.println(text.isEmpty());
			//System.out.println(text);
			if(i==0) 
			{ 
				System.out.println("Cx rating for first product: "+text); 
			} 
		}
		 
		List<WebElement> mobileNames = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		//System.out.println(mobileNames.size());
		for(int i=0;i<mobileNames.size();i++)
		{
			if(i==0)
			{
				System.out.println(mobileNames.get(i).getText());
				mobileNames.get(i).click();
			}
		}
		File sourceImage = driver.getScreenshotAs(OutputType.FILE);
		File destImage = new File("./images/IMG001.png");
		FileUtils.copyFile(sourceImage, destImage);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(windowHandles);
		String[] windows = new String[10];
		for(int i=0;i<handles.size();i++)
		{
			windows[i]=handles.get(i);
			System.out.println(windows[i]);
			if(i==1)
			{
				driver.switchTo().window(windows[i]);
				driver.findElement(By.id("add-to-cart-button")).click();
				WebElement total = driver.findElement(By.xpath("(//span[@id='attach-accessory-cart-subtotal'])[1]"));
				total.click();
				String subTotal = total.getText();
				System.out.println("subtotal: "+subTotal);
				System.out.println("First product price: "+rateOfProduct1);
				if(subTotal.contains(rateOfProduct1)) 
				{
					System.out.println("Product pricing match");
				} 
				else
					System.out.println("Product pricing mismatch");
				 
				driver.close();
				driver.switchTo().window(windows[i-1]);
			}
		}
		//driver.findElement(By.id("nav-logo-sprites")).click();
		driver.close();
	}

}