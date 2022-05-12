package week4.day2;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * //Pseudo Code * 
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login" * 
 * 2. Enter UserName and Password Using Id Locator
 * 3. Click on Login Button using Class Locator
 * 4. Click on CRM/SFA Link
 * 5. Click on contacts Button
 * 6. Click on Merge Contacts using Xpath Locator
 * 7. Click on Widget of From Contact
 * 8. Click on First Resulting Contact
 * 9. Click on Widget of To Contact
 * 10. Click on Second Resulting Contact
 * 11. Click on Merge button using Xpath Locator
 * 12. Accept the Alert
 * 13. Verify the title of the page
 */
public class MergeContact_TestLeafWebpageWindowsAndAlerts {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[contains(@href,'mergeContactsForm')]")).click();
		driver.findElement(By.xpath("(//img[contains(@src,'/images/fieldlookup.gif')])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();

		ArrayList<String> listOfHandles = new ArrayList<String>(windowHandles);
		String window1 = listOfHandles.get(0);
		String window2 = listOfHandles.get(1);
		System.out.println(window1);
		System.out.println(window2);
		System.out.println(driver.getCurrentUrl());

		driver.switchTo().window(window2);
		driver.manage().window().maximize();
		WebElement table = driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]"));
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr[1]"));
		List<WebElement> columns = new ArrayList<WebElement>();
		System.out.println(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			columns = rows.get(i).findElements(By.tagName("a"));
			System.out.println(columns.size());
			System.out.println(columns.toString());
			for (int j = 0; j < columns.size(); j++)
			{
				if (j == 0)
				{
					System.out.println(columns.get(j).getText());
					columns.get(j).click();
					break;
				}
			}
			break;
		}
		driver.switchTo().window(window1);



		driver.findElement(By.xpath("(//img[contains(@src,'/images/fieldlookup.gif')])[2]")).click();
		windowHandles= driver.getWindowHandles(); 
		listOfHandles = new ArrayList<String>(windowHandles);
		window1 = listOfHandles.get(0);
		window2 =listOfHandles.get(1); 
		System.out.println(window1);
		System.out.println(window2);
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(window2);
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize(); 
		WebElement table2 =driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]"));
		List<WebElement> rows2 = table2.findElements(By.xpath("(./tbody/tr)[1]"));
		List<WebElement> columns2 = new ArrayList<WebElement>();
		System.out.println(rows2.size()); 
		for(int i=0;i<rows2.size();i++)
		{ 
			columns2= rows2.get(i).findElements(By.tagName("a"));//tbody/tr/td/div/a
			System.out.println(columns2.size()); 
			for(int j=0;j<columns2.size();j++) 
			{
				if(j==0) 
				{
					System.out.println(columns2.get(j).getText());
					columns2.get(j).click();
					break;
				}
			}
			break; 
		} 
		driver.switchTo().window(window1);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		String title = driver.getTitle();
		System.out.println(title);
		if(title.equals("View Contact | opentaps CRM"))
		{
			System.out.println("Title matches");		
		}
		else
			System.out.println("Title mismatch");
	}

}