package week4.day2;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWindowsTestLeafWebpage {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.leafground.com/pages/Window.html");
		/*Set<String> windowHandles = driver.getWindowHandles();
		 * List<String> windows=new ArrayList<>(windowHandles); String windows1=
		 * windows.get(0); String windows2= windows.get(1);
		 * driver.switchTo().window(windows2); driver.close();
		 * driver.switchTo().window(windows1);
		 */
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> handles = driver.getWindowHandles();
		System.out.println("Number of windows "+handles.size());
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows=new ArrayList<String>(windowHandles);
		String[] window=new String[10];
		for(int i=0;i<windows.size();i++)
		{
			window[i]=windows.get(i);
			System.out.println(window[i]);
			driver.switchTo().window(window[i]);
			System.out.println(driver.getCurrentUrl());
			if(i!=(windows.size()-1))
			{
				driver.close();
			}
		}
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		driver.quit();
	}

}