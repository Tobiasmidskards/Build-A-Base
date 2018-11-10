package database;

public class Person
{
	private String nconst;
	private String name;
	private String birth;
	private String death;
	private String profession;
	private String titles;

	public Person(String nconst, String name, String birth, String death, String profession, String titles)
	{
		this.nconst = nconst;
		this.name = name;
		this.birth = birth;
		this.death = death;
		this.profession = profession;
		this.titles = titles;
	}

	public String getNconst()
	{
		return this.nconst;
	}

	public String getName()
	{
		return this.name;
	}

	public String getBirth()
	{
		return this.birth;
	}

	public String getDeath()
	{
		return this.death;
	}

	public String getProfession()
	{
		return this.profession;
	}

	public String getTitles()
	{
		return this.titles;
	}
}