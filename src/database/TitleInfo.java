package database;

public class TitleInfo {

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

	public TitleInfo() {
		this.tconst = "\\N";
		this.titleType = "\\N";
		this.primaryTitle = "\\N";
		this.originalTitle = "\\N";
		this.isAdult = "\\N";
		this.startYear = "\\N";
		this.endYear = "\\N";
		this.runtime = "\\N";
		this.genres = "\\N";
		this.rating = "\\N";
		this.votes = "\\N";
	}

	public String getTconst()
	{
		return this.tconst;
	}

	public void setTconst(String tconst)
	{
		this.tconst = tconst;
	}

	public String getTitleType()
	{
		return this.titleType;
	}

	public void setTitleType(String titleType)
	{
		this.titleType = titleType;
	}

	public String getPrimaryTitle()
	{
		return this.primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle)
	{
		this.primaryTitle = primaryTitle;
	}

	public String getOriginalTitle()
	{
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle)
	{
		this.originalTitle = originalTitle;
	}

	public String getIsAdult()
	{
		return this.isAdult;
	}

	public void setIsAdult(String isAdult)
	{
		this.isAdult = isAdult;
	}

	public String getStartYear()
	{
		return this.startYear;
	}

	public void setStartYear(String startYear)
	{
		this.startYear = startYear;
	}

	public String getEndYear()
	{
		return this.endYear;
	}

	public void setEndYear(String endYear)
	{
		this.endYear = endYear;
	}

	public String getRuntime()
	{
		return this.runtime;
	}

	public void setRuntime(String runtime)
	{
		this.runtime = runtime;
	}

	public String getGenres()
	{
		return this.genres;
	}

	public void setGenres(String genres)
	{
		this.genres = genres;
	}

	public String getRating()
	{
		return this.rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public String getVotes()
	{
		return this.votes;
	}

	public void setVotes(String votes)
	{
		this.votes = votes;
	}

}
