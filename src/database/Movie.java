package database;

public class Movie {

	private String tconst;
	private String titleType;
	private String primaryTitle;
	private String originalTitle;
	private String isAdult;
	private String startYear;
	private String endYear;
	private String runtime;
	private String genres;
	private String rating;
	private String votes;

	public Movie(String tconst, String titleType, String primaryTitle, String originalTitle, String isAdult, String startYear, String endYear, String runtime, String genres, String rating, String votes) {
		this.tconst = tconst;
		this.titleType = titleType;
		this.primaryTitle = primaryTitle;
		this.originalTitle = originalTitle;
		this.isAdult = isAdult;
		this.startYear = startYear;
		this.endYear = endYear;
		this.runtime = runtime;
		this.genres = genres;
		this.rating = rating;
		this.votes = votes;
	}

	public String getTconst()
	{
		return this.tconst;
	}

	public String getTitleType()
	{
		return this.titleType;
	}

	public String getPrimaryTitle()
	{
		return this.primaryTitle;
	}

	public String getOriginalTitle()
	{
		return this.originalTitle;
	}

	public String getIsAdult()
	{
		return this.isAdult;
	}

	public String getStartYear()
	{
		return this.startYear;
	}

	public String getEndYear()
	{
		return this.endYear;
	}

	public String getRuntime()
	{
		return this.runtime;
	}

	public String getGenres()
	{
		return this.genres;
	}

	public String getRating()
	{
		return this.rating;
	}

	public String getVotes()
	{
		return this.votes;
	}

}
