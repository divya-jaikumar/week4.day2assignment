package week4.day2assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		
	//	1. Launch https://www.snapdeal.com/

		driver.get("https://www.snapdeal.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
//		
//		2. Go to Mens Fashion
		Actions builder = new Actions(driver);
		WebElement mens = driver.findElement(By.xpath("//span[@class='catText']"));
		builder.moveToElement(mens).perform();
		Thread.sleep(5000);
		
//			3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
//			4. Get the count of the sports shoes
		String Sportsshoes = driver.findElement(By.xpath("//div[@class='child-cat-name selected']/following-sibling::div")).getText();
		System.out.println("Count of Sport shoes:"+" "+Sportsshoes);
		
		
//			5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
//			6. Sort by Low to High
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click();
		
//			8.Select the price range (900-1200)
		WebElement pricefrom = driver.findElement(By.xpath("//input[@class='input-filter']"));
		pricefrom.click();
		pricefrom.clear();
		pricefrom.sendKeys("900");
		pricefrom.sendKeys(Keys.TAB);
		
		WebElement priceto = driver.findElement(By.xpath("//input[@name='toVal']"));
		//priceto.click();
		priceto.clear();
		priceto.sendKeys("1200");
		priceto.sendKeys(Keys.TAB);
		
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		
//			9.Filter with color Navy 
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@class='view-more-button btn btn-line btn-theme-secondary viewMoreFilter']")).click();
		driver.findElement(By.xpath("//span[@class='filter-color-tile Navy ']")).click();
		Thread.sleep(1000);
		
//			10 verify the all applied filters 
		
//			11. Mouse Hover on first resulting Training shoes
		Actions builder1 = new Actions(driver);
		WebElement shoes = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		builder1.moveToElement(shoes).perform();
		
//			12. click QuickView button
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		Thread.sleep(1000);
		
//			13. Print the cost and the discount percentage
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println(cost);
		String percent = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println(percent);
		
//			14. Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./images/Snapdeal.png");
		FileUtils.copyFile(source, destination);

//			15. Close the current window
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		
//			16. Close the main window
		driver.close();


	}

}
