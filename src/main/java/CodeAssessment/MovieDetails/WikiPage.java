package CodeAssessment.MovieDetails;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CodeAssessment.utils.ReadProperties;

public class WikiPage {
	/*
	 This method gets us the release date and origin country of the movie from the Wikipedia
	 getting url and movie name from the test data properties file
	 getting locators from the Object properties file 
	 returning the results in a list 
	 */
    public List<String> getMovieDetails() throws IOException {
    List<String> movieInfo = new ArrayList<String>();
      String userDir = System.getProperty("user.dir");
      Properties prop = ReadProperties.readPropertiesFile(userDir+"\\TestData\\TestData.properties");
      Properties prop2 = ReadProperties.readPropertiesFile(userDir+"\\ObjectRepo\\Object.properties");
	  String url = prop.getProperty("wikiurl");
	  String movie = prop.getProperty("MovieName");
	  String search = prop2.getProperty("searchWiki");
	  String suggestions = prop2.getProperty("suggestionBoxWiki");
	  String releaseDate = prop2.getProperty("releaseWiki");
	  String originCountry = prop2.getProperty("countryWiki");
      System.setProperty("webdriver.chrome.driver", userDir+"\\drivers\\chromedriver.exe");
  	  WebDriver driver=new ChromeDriver();
  	  driver.manage().window().maximize();
  	  driver.navigate().to(url);
	  driver.findElement(By.id(search)).sendKeys(movie);
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(suggestions)));
	  driver.findElement(By.xpath("//*[contains(text(), '"+movie+"')]")).click();
	  String release = driver.findElement(By.xpath(releaseDate)).getText();
	  String country= driver.findElement(By.xpath(originCountry)).getText();
	  driver.quit();
	  movieInfo.add(release);
	  movieInfo.add(country);
	  return movieInfo;
    }
}
