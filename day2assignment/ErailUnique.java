package week4.day2assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailUnique {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://erail.in/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(5000);

		driver.findElement(By.xpath("//td[@id='tdDateOnly']/input")).click();
		
		//clear and type in the source station 
		WebElement from = driver.findElement(By.xpath("//input[@id='txtStationFrom']"));
		from.clear();
		from.sendKeys("MSB");
		from.sendKeys(Keys.TAB);
		
		
		
//		clear and type in the destination station
		
		WebElement to = driver.findElement(By.xpath("//input[@id='txtStationTo']"));
		to.clear();
		to.sendKeys("CBE");
		to.sendKeys(Keys.TAB);
		

		
//		Find all the train names using xpath and store it in a list
		int rowCount = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr")).size();
		List<String> listnames= new ArrayList<String>();
		
		for(int i=1;i<=rowCount;i++) {
			String trainname=driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+ i +"]/td[2]/a")).getText();
			listnames.add(trainname);
			
		}
		int size = listnames.size();
		System.out.println("Total Number of trains:"+size);
		System.out.println(listnames);
		
		Set<String>uniqueTrains=new HashSet<String>(listnames);
		System.out.println(uniqueTrains.size());
		

	}

}
