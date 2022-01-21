package week4.day2assignment;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/draggable/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions builder =new Actions(driver);
		driver.switchTo().frame(0);
		
		WebElement drag = driver.findElement(By.id("draggable"));
		Thread.sleep(5000);
		Point location = drag.getLocation();
		int x = location.getX();
		int y = location.getY();
		Thread.sleep(2000);
		builder.dragAndDropBy(drag, x,y).perform();
		
		

	}

}
