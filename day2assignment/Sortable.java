package week4.day2assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/sortable/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Actions builder =new Actions(driver);
		driver.switchTo().frame(0);
		WebElement item2 = driver.findElement(By.xpath("//li[text()='Item 2']"));
		WebElement item4 = driver.findElement(By.xpath("//li[text()='Item 4']"));
		WebElement item5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		WebElement item7 = driver.findElement(By.xpath("//li[text()='Item 7']"));
		
		builder.clickAndHold(item2).moveToElement(item5).release().perform();
		builder.clickAndHold(item7).moveToElement(item4).release().perform();
		

	}

}
