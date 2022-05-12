package week4.day2;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLeafWebpageDropDownImplementation {

	public static void main(String[] args)
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.leafground.com/pages/Dropdown.html");
		WebElement dropDown1= driver.findElement(By.id("dropdown1"));
		Select selectByIndex = new Select(dropDown1);
		selectByIndex.selectByIndex(1);
		WebElement dropDown2= driver.findElement(By.name("dropdown2"));
		Select selectByText = new Select(dropDown2);
		selectByText.selectByVisibleText("Selenium");
		WebElement dropDown3= driver.findElement(By.id("dropdown3"));
		Select selectByValue = new Select(dropDown3);
		selectByValue.selectByValue("2");
		WebElement dropDown4= driver.findElement(By.className("dropdown"));
		Select selectByOption = new Select(dropDown4);
		List<WebElement> options = selectByOption.getOptions();
		for(WebElement e : options)
		{
			if(e.getText().equals("Selenium"))
				selectByOption.selectByVisibleText(e.getText());
		}
		driver.findElement(By.xpath("(//select)[5]")).sendKeys("Selenium",Keys.ENTER);
		WebElement dropDown6= driver.findElement(By.xpath("(//select)[6]"));
		Select selectmultiple = new Select(dropDown6);
		selectmultiple.selectByIndex(1);
		selectmultiple.selectByIndex(2);
		driver.close();
	}

}