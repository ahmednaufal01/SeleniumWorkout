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

public class Nykaas{
	public static void main(String[] args) throws InterruptedException {
		String url = "https://www.nykaa.com/";
//		To Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get(url);
		String parentwindow = driver.getWindowHandle();
		System.out.println("The parent window id is:" + parentwindow);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		2) Mousehover on Brands and Mousehover on Popular
		Actions ac = new Actions(driver);
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		ac.moveToElement(brand).perform();
		System.out.println("Mouse hover done on Brands");
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		ac.moveToElement(popular).click().build().perform();
		System.out.println("Mouse hover done on Popular");

		// Click Loreal paris
		WebElement lparis = driver.findElement(By.xpath("//li[@class='brand-logo menu-links'][5]//img"));
		lparis.click();

//		  Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> cwin = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(cwin);
		driver.switchTo().window(ls.get(1));
		String title = driver.getTitle();

		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title is matched");
		} else {
			System.out.println("Title is not matched");
		}
		Thread.sleep(5000);

		// Click sort By and select customer top rated
		try {
			WebElement sortby = driver.findElement(By.xpath("//span[@class='pull-left']"));
			sortby.click();
			WebElement crt = driver.findElement(By.xpath("//span[text()='customer top rated']"));
			crt.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(3000);

		// Click Category and click Shampoo
		WebElement category = driver.findElement(By.xpath("//div[text()='Category']"));
		category.click();
		WebElement shampoo = driver.findElement(By.xpath("(//label[@for='chk_Shampoo_undefined']//span)[1]"));
		shampoo.click();
		Thread.sleep(2000);

		// check whether the Filter is applied with Shampoo
		String filter = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		if (filter.contains("Shampoo"))
			System.out.println("Filter applied: " + filter);

		// Click on L'Oreal Paris Colour Protect Shampoo
		WebElement loreal = driver.findElement(By.xpath("(//div[@class='m-content__product-list__title']//span)[5]"));
		loreal.click();

		// GO to the new window and select size as 175ml
		Set<String> lwin = driver.getWindowHandles();
		List<String> ls1 = new ArrayList<String>(lwin);
		driver.switchTo().window(ls1.get(2));
		String title2 = driver.getTitle();
		System.out.println("The third Window title name:" + title2);
		WebElement ml = driver.findElement(By.xpath("(//span[@class='size-pallets'])[2]"));
		ml.click();

		// Print the MRP of the product
		WebElement price = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]"));		
		String text = price.getText();
		System.out.println("The price of the product is:" +text);

		// Click on ADD to BAG
		WebElement addbag = driver.findElement(By.xpath("(//div[@class='pull-left']/div)[1]"));
		addbag.click();

		// Go to Shopping Bag
		WebElement shopbag = driver.findElement(By.xpath("//div[@class='AddBagIcon']"));
		shopbag.click();
		Thread.sleep(3000);

		// Print the Grand Total amount
		WebElement grand = driver.findElement(By.xpath("(//div[@class='value'])[4]"));
		String text2 = grand.getText();
		System.out.println("The Grand amount is:" + text2);

		// Click Proceed
		WebElement proceed = driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']"));
		proceed.click();
		// Click on Continue as Guest
		WebElement guest = driver.findElement(By.xpath("//button[@class='btn full big']"));
		guest.click();

//		Print the warning message (delay in shipment)
		WebElement message = driver.findElement(By.xpath("//div[@class='message']"));
		String text3 = message.getText();
		System.out.println(text3);
		
		System.out.println("Nykaa TestCases Completed");
		
		driver.quit();
	}
	
}