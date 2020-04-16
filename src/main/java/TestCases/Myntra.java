package TestCases;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



public class Myntra {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.myntra.com/";
//		To Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mouse hover on Women
		Actions act = new Actions(driver);
		WebElement women = driver.findElement(By.xpath("(//a[@class='desktop-main'])[2]"));
		act.moveToElement(women).perform();
		System.out.println("Done mouse hover menu for Women");
		Thread.sleep(5000);

		// Mouser hover on Jackets and Coats
		WebElement jackets = driver.findElement(By.xpath("(//a[@class='desktop-categoryLink'])[72]"));
		act.moveToElement(jackets).click().build().perform();
		System.out.println("Done mouse hover  sub menu on Jackets & Coatings");
		Thread.sleep(5000);

		// To Find the total count of an item
		String tcount = driver.findElement(By.className("title-count")).getText();
		String jctcnt = tcount.replaceAll("\\D", "");
		int totalcnt = Integer.parseInt(jctcnt);
		System.out.println("Total Count are:" + jctcnt);

		// To find the number of jackets in the categories

		String jacket = driver.findElement(By.className("categories-num")).getText();
		String Jackcount = jacket.replaceAll("\\D", "");
		Integer.parseInt(Jackcount);
		System.out.println("Jacket Count are:" + Jackcount);

		// To find the number of coats in the categories
		String coat = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String count = coat.replaceAll("\\D", "");
		Integer.parseInt(count);
		System.out.println("Cooats count are:" + count);

		Integer catfct = Integer.parseInt(jctcnt) + Integer.parseInt(count);

		if (catfct == totalcnt) {
			System.out.println("Count is Matched");

		} else {
			System.out.println("Count is Not Matched");
		}
		// To check the Coats
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[2]")).click();
         Thread.sleep(3000);
		// To click the + more option in the brand section.
		WebElement brand = driver.findElement(By.className("brand-more"));
		brand.click();
		// To search the mango
		WebElement search = driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']"));
		search.sendKeys("Mango");
		WebElement checkbox = driver.findElement(By.xpath("//label[@class=' common-customCheckbox']"));
		checkbox.click();
		Thread.sleep(2000);
		// To close the pop-up
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();

		List<WebElement> mango = driver.findElements(By.linkText("MANGO"));
		for (WebElement mangotext : mango) {
			String text = mangotext.getText();
			if (text.equals("Mango")) {
				System.out.println("Branded names are equal");
			} else {
				System.out.println("Branded names are not equal");
			}
		}
		// Sort by Better Discount
		WebElement discount = driver
				.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']"));
		act.moveToElement(discount).perform();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();

		Thread.sleep(3000);
		// Find the price of first displayed item
		String bestdiscout = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();
		System.out.println("The price of first displayed item" + bestdiscout);

		// Mouse over on size of the first item
		WebElement disprice = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]"));
		act.moveToElement(disprice).perform();

		// Click on WishList Now
		driver.findElement(By.xpath("//span[@class='product-actionsButton product-wishlist product-prelaunchBtn']"))
				.click();
		System.out.println("Myntra Test Case Completed");
		// Close Browser
		driver.close();

	}
}
