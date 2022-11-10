package CodeAssessment.MovieDetails;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import CodeAssessment.utils.ReadProperties;


public class ImdbPage {
	
	/*
	 This method gets us the release date and origin country of the movie from the Imdb
	 getting url and movie name from the test data properties file
	 getting locators from the Object properties file 
	 returning the results in a list 
	 */
      public List<String> getMovieDetails() throws IOException {
    	  List<String> movieInfo = new ArrayList<String>();
    	  String userDir = System.getProperty("user.dir");
    	  Properties prop = ReadProperties.readPropertiesFile(userDir+"\\TestData\\TestData.properties");
    	  Properties prop2 = ReadProperties.readPropertiesFile(userDir+"\\ObjectRepo\\Object.properties");
    	  String url = prop.getProperty("url");
    	  String movie = prop.getProperty("MovieName");
    	  String search = prop2.getProperty("searchBox");
    	  String suggestions = prop2.getProperty("suggestionBox");
    	  String release = prop2.getProperty("releaseDate");
    	  String originCountry=prop2.getProperty("country");
    	  System.setProperty("webdriver.chrome.driver", userDir+"\\drivers\\chromedriver.exe");
    	  WebDriver driver=new ChromeDriver();
    	  driver.manage().window().maximize();
    	  driver.navigate().to(url);
    	  driver.findElement(By.id(search)).sendKeys(movie);
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(suggestions)));
    	  driver.findElement(By.xpath("//div[contains(text(), '"+movie+"')]")).click();
    	  String releaseDate = driver.findElement(By.xpath(release)).getText();
    	  movieInfo.add(releaseDate);
    	  String country = driver.findElement(By.xpath(originCountry)).getText();
    	  movieInfo.add(country);
    	  driver.quit();
    	  return movieInfo;
      }
}
