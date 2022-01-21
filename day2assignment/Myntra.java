package week4.day2assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

//		1.Load the uRL https://www.amazon.in/
		driver.get(" https://www.myntra.com/");

		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Actions builder=new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		builder.moveToElement(men).perform();
		Thread.sleep(2000);
		
		//click on jackets
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		Thread.sleep(2000);
//		4) Find the total count of item 
		String totalcount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String replaceAll=totalcount.replaceAll("[^0-9]","");
		int total = Integer.parseInt(replaceAll);
		System.out.println("Total number of jackets"+total);
		
		
//		5) Validate the sum of categories count matches
		String jacketcount = driver.findElement(By.xpath("//span[@class='categories-num']")).getText();
		String replaceAll1=jacketcount.replaceAll("[^0-9]","");
		int jacket1 = Integer.parseInt(replaceAll1);
		
		String rainjacket = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String replaceAll2=rainjacket.replaceAll("[^0-9]","");
		int raincount1 = Integer.parseInt(replaceAll2);

		int jacketraincoat=jacket1+raincount1;
		System.out.println(jacketraincoat);
		
		if(total==jacketraincoat) {
			System.out.println("total count of jackets are same as sum of jacket and rain jacket");
		}
		else {
			System.out.println("total count of jackets are not same as sum of jacket and rain jacket");
			
		}
	
//		6) Check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		
//		7) Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		Thread.sleep(2000);
		
//		8) Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		driver.findElement(By.xpath("//span[@class='FilterDirectory-count']/following-sibling::div")).click();
		
//		9) Close the pop-up x
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
//		10) Confirm all the Coats are of brand Duke
//		    Hint : use List 
		List<WebElement> dukecoats = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		int count=0;
		
		for (WebElement coat : dukecoats) {
			String coatname = coat.getText();
			if(coatname.equals("Duke")) {
			count++;
		}
	}
		Thread.sleep(500);
		int size = dukecoats.size();
		System.out.println(size);
		if (count == dukecoats.size())
		{
			System.out.println("Yes, all brands are Duke");
		}
		else
		{
			System.out.println("No, all brands are not Duke");
		}
		Thread.sleep(2000);
//		11) Sort by Better Discount
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='sort-list']/li[3]/label")).click();
		Thread.sleep(1000);

		
//		12) Find the price of first displayed item
		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of first displayed item:"+price);
		
//		Click on the first product
		driver.findElement(By.xpath("//img[@class='img-responsive']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String>listwindow=new ArrayList<String>(windowHandles);
		String string = listwindow.get(1);
		String parent=listwindow.get(0);
		driver.switchTo().window(string);
		
//		13) Take a screen shot
		Thread.sleep(3000);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./images/Myntra.png");
		FileUtils.copyFile(source, destination);
		
//		14) Click on WishList Now
		driver.findElement(By.xpath("//span[contains(text(),'WISHLIST')]")).click();
		
//		15) Close Browser
		driver.close();
		driver.switchTo().window(parent);
		driver.close();
		
		

		
		

	}

}

