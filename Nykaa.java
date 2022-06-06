package Week5Day5Assignment2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Download the Driver And Setup the Path
        WebDriverManager.chromedriver().setup();
        
        //launch the browser
        ChromeOptions option=new ChromeOptions();
        option.addArguments("--disable-notification");
        ChromeDriver driver=new ChromeDriver();
        
        //get URL
        driver.get("https://www.nykaa.com/");
        
        //maximize the window
        driver.manage().window().maximize();
       
        //implicity wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement element = driver.findElement(By.xpath("//a[text()='brands']"));
        
        // Mouse over on Brands and Search L'Oreal Paris
        Actions build=new Actions(driver);
        build.moveToElement(element).perform();
        driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
        
        // Click L'Oreal Paris
        driver.findElement(By.linkText("L'Oreal Paris")).click();
        
        // Check the title contains L'Oreal Paris
        System.out.println(driver.getTitle());
        
        //Click sort By 
        driver.findElement(By.xpath("//button[@class=' css-n0ptfk']//span")).click();
        
        //select customer top rated
        driver.findElement(By.xpath("//label[@class='control control-radio']//span[text()='customer top rated']")).click();
        
        //Click Category and 
        driver.findElement(By.xpath("//div[@id='first-filter']//span[text()='Category']")).click();
        
        Thread.sleep(10);
        //click Hair
        driver.findElement(By.xpath("//li[@class='css-1do4irw']//span[text()='Hair']")).click();
        
        Thread.sleep(10);
        //Click haircare
        driver.findElement(By.xpath("//li[@class='css-1do4irw']//span[text()='Hair Care']")).click();
        
        //Shampoo
        driver.findElement(By.xpath("//label[@class='control control-checkbox']//span[text()='Shampoo']")).click();
        
        //Click->Concern
        driver.findElement(By.xpath("//div[@class='css-w2222k']//span[text()='Concern']")).click();
        
        //Color Protection
        driver.findElement(By.xpath("//label[@class='control control-checkbox']//span[text()='Color Protection']")).click();
        
        //check whether the Filter is applied with Shampoo
        boolean displayed = driver.findElement(By.xpath("(//div[@class='css-rtde4j']//span)[2]")).isDisplayed();
        System.out.println("Displayed in Filtered box is "+ displayed);
        
        //Click on L'Oreal Paris Colour Protect Shampoo
        driver.findElement(By.xpath("(//a[@class='css-qlopj4']//div[@class='css-xrzmfa'])[1]")).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> openwindow= driver.getWindowHandles();
        List<String> childwindow=new ArrayList<String>(openwindow);
        
        //SwitchTo child window
        driver.switchTo().window(childwindow.get(1));
        
        //GO to the new window and select size as 175ml
        WebElement drop = driver.findElement(By.xpath("//div[@class='css-11wjdq4']//select"));
        Select dropdown=new Select(drop);
        dropdown.selectByIndex(1);
        
        //Print the MRP of the product
        String Mrp = driver.findElement(By.xpath("(//div[@class='css-1d0jf8e']//span)[4]")).getText();
        String Mrp1 = Mrp.replaceAll("\\W","");
        System.out.println("MRP of the Product : "+" "+Mrp1);
        
        //Click on ADD to BAG
        driver.findElement(By.xpath("(//button[@class=' css-12z4fj0']//span)[2]")).click();
        //Go to Shopping Bag 
        driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']//button")).click();
        
        //SwitchToFrame
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
        driver.switchTo().frame(frame1);
       
        //Print the Grand Total amount
        String GrandTotal0 = driver.findElement(By.xpath("(//div[@class='table-row ']//div)[2]")).getText();
        String GrandTotal = GrandTotal0.replaceAll("\\W", "");
        System.out.println("Grant total of the product:"+" "+ GrandTotal);
        
        //Click Proceed
        driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']//span[text()='Proceed']")).click();
        
        //SwichTo ParentWindow
        driver.switchTo().defaultContent();
        
        // Click on Continue as Guest
        driver.findElement(By.xpath("//button[@class='btn full big']")).click();
        
        // Check if this grand total is the same in step 14
        String GrandTotal1 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//span")).getText();
         System.out.println(GrandTotal1);
        if(GrandTotal.equalsIgnoreCase(GrandTotal1)) {
        	System.out.println("Both Grand Totals are equal");
        }
        	
        	else {
        		System.out.println("Both Grand Totals are not equal");
        	}
        	
        Thread.sleep(10);
        
        //Close all windows
        driver.quit();
        }

}
