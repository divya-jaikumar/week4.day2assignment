package week4.day2assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/resizable/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Actions builder =new Actions(driver);
		driver.switchTo().frame(0);
		
		WebElement resize = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		resize.click();
		Thread.sleep(5000);
		Point location = resize.getLocation();
		int x = location.getX();
		int y = location.getY();
		Thread.sleep(2000);
		builder.dragAndDropBy(resize, x,y).perform();

		
	}

}
