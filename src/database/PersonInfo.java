package database;

public class PersonInfo
{
	private String nconst;
	private String name;
	private String birth;
	private String death;
	private String profession;
	private String titles;

	public PersonInfo()
	{
		this.nconst = "\\N";
		this.name = "\\N";
		this.birth = "\\N";
		this.death = "\\N";
		this.profession = "\\N";
		this.titles = "\\N";
	}

	public String getNconst()
	{
		return this.nconst;
	}

	public void setNconst(String nconst)
	{
		this.nconst = nconst;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBirth()
	{
		return this.birth;
	}

	public void setBirth(String birth)
	{
		this.birth = birth;
	}

	public String getDeath()
	{
		return this.death;
	}

	public void setDeath(String death)
	{
		this.death = death;
	}

	public String getProfession()
	{
		return this.profession;
	}

	public void setProfession(String profession)
	{
		this.profession = profession;
	}

	public String getTitles()
	{
		return this.titles;
	}

	public void setTitles(String titles)
	{
		this.titles = titles;
	}
}