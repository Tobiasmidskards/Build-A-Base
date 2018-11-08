import java.util.List;
import java.time.LocalDateTime;

public class Movie {

	private String title;
	private LocalDateTime year;
	private int rating;
	private List<String> actors;
	private List<String> category;

	public Movie(String title, LocalDateTime year, int rating, List<String> actors, List<String> category) {
		this.title = title;
     	this.year = year;
     	this.rating = rating;
     	this.actors = actors;
     	this.category = category;
	}

}