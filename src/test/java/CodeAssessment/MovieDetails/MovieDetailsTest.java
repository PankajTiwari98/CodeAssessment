package CodeAssessment.MovieDetails;

import java.io.IOException;
import java.util.List;
import java.util.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import CodeAssessment.MovieDetails.*;

public class MovieDetailsTest {
	
	ImdbPage imdb = new ImdbPage();
	WikiPage wiki = new WikiPage();
	@Test
	public void verifyMovieDetails() throws InterruptedException, IOException {
		List<String> detailsFromImdb = imdb.getMovieDetails();
		List<String> detailsFromWiki = wiki.getMovieDetails();
		String releaseImdb = detailsFromImdb.get(0).replaceAll("\\(.*?\\) ?", "");
		String[] releaseFormat = detailsFromWiki.get(0).split(" ");
		String releaseWiki = releaseFormat[1] + " " + releaseFormat[0] + ", " + releaseFormat[2]+" "; 
		Assert.assertEquals(detailsFromImdb.get(1), detailsFromWiki.get(1));
		Assert.assertEquals(releaseImdb, releaseWiki);
	}
}
