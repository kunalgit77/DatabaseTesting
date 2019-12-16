import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class JdbcConnection {

	public static void main(String[] args) throws SQLException {
         
		String host = "localhost";
		String portNo = "3307";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + portNo + "/freecrm", "root", "kunal");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM crmdatabase WHERE Name = 'Kunal'");
		while(rs.next())
		{
			System.setProperty("webdriver.chrome.driver", "D:\\selenium 3.14\\chrome78v\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://freecrm.com/");
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//span[contains(text(),'Log In')]")).click();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.findElement(By.name("email")).sendKeys(rs.getString("UserName"));
			driver.findElement(By.name("password")).sendKeys(rs.getString("Password"));
			//driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//i[@class='settings icon']"))).click().build().perform();
			driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();

		/*System.out.println(rs.getString("Name"));
		System.out.println(rs.getInt("Id"));
		System.out.println(rs.getInt("Age"));
		System.out.println(rs.getString("Location"));*/
		}


	}

}
