package week4.day2assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nyka {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		//1) Go to https://www.nykaa.com/
		driver.get(" https://www.nykaa.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
//			2) Mouseover on Brands and Search L'Oreal Paris
		Actions builder=new Actions(driver);
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brands).perform();
		Thread.sleep(2000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		
//			3) Click L'Oreal Paris
		driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]")).click();
		
//			4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		System.out.println(title);
		
//			5) Click sort By and select customer top rated
		driver.findElement(By.xpath(" //button[@class=' css-p2rfnw']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
//			6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
//			7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
//			8)check whether the Filter is applied with Shampoo
		WebElement checkfilter = driver.findElement(By.xpath("//span[@class='filter-value' and text()='Shampoo']"));
		if(checkfilter.getText()=="Shampoo") {
			System.out.println("Filter is verified successfully");
		}
//			9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains (text(),'Colour Protect Shampoo')]")).click();
		
//			10) GO to the new window and select size as 175ml
		Set<String> windows = driver.getWindowHandles();
		List<String> list=new ArrayList<String>(windows);
		String string = list.get(1);
		driver.switchTo().window(string);
		
//			11) Print the MRP of the product
		WebElement mrp = driver.findElement(By.xpath("//span[contains(text(),'150')]"));
		String MRP = mrp.getText();
		System.out.println(MRP);
		
//			12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		
//			13) Go to Shopping Bag 
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		
		//switch to frame
		driver.switchTo().frame(0);
		
//			14) Print the Grand Total amount
		WebElement grandtotal = driver.findElement(By.xpath("//div[@class='value medium-strong']"));
		String total = grandtotal.getText();
		System.out.println(total);
		
//			15) Click Proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
//			16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
//			17) Check if this grand total is the same in step 14
		String finaltotal = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div[2]")).getText();
		System.out.println(finaltotal);
		if(total.equals(finaltotal)) {
			System.out.println("Grand Total is verified");
			
		}
//			18) Close all windows
		driver.close();
	
	}

}
