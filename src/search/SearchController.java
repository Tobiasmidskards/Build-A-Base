package search;

import java.util.Scanner;
import java.io.*;

public class SearchController
{
	private Scanner fileScanner;

	public SearchController()
		throws FileNotFoundException
	{
	}

	public String getTitle(String tconst)
	{
		String[] entry;
		String result = "";
		boolean searchFinished = false;
		int linesRead = 0;

		try
		{
			File table = new File("resources/titlebasics.tsv");

			if (!table.canRead())
			{
				System.out.println("Cannot read from table: 'resources/titlebasics.tsv'");
			}
			else
			{
				fileScanner = new Scanner(table, "UTF-8");

				while(!searchFinished)
				{
					if (fileScanner.hasNextLine())
					{
						entry = fileScanner.nextLine().split("\t");

						linesRead++;

						if (tconst.equals(entry[0]))
						{
							result = "("+ entry[5] + ") " + entry[2]; // (YEAR) Title
							searchFinished = true;
						}
					}
					else
					{
						System.out.println("Line does not exist. Lines read before stopping: " + linesRead);
						searchFinished = true;
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		return result;
	}

	public String[] getRatingAndVotes(String tconst)
	{
		String[] entry;
		String[] result = new String[2];
		boolean searchFinished = false;
		int linesRead = 0;

		try
		{
			File table = new File("resources/titleratings.tsv");

			if (!table.canRead())
			{
				System.out.println("Cannot read from table: 'resources/titleratings.tsv'");
			}
			else
			{
				fileScanner = new Scanner(table, "UTF-8");

				while(!searchFinished)
				{
					if (fileScanner.hasNextLine())
					{
						entry = fileScanner.nextLine().split("\t");

						linesRead++;

						if (tconst.equals(entry[0]))
						{
							result[0] = entry[1];
							result[1] = entry[2];
							searchFinished = true;
						}
					}
					else
					{
						System.out.println("Line does not exist. Lines read before stopping: " + linesRead);
						searchFinished = true;
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		return result;
	}

	public String[] searchPerson(String name) {
		// find person in namebasics.txt
		// lookup titles from tconst.
		// Find titles in titlebasics.txt
		// show line[2] for primarytitle
		
		String[] entry;
		String[] result = new String[6];
		String[] movies;

		boolean searchFinished = false;

   		try
   		{
			File table = new File("resources/namebasics.tsv");

    		if (!table.canRead())
    		{
    			System.out.println("Cannot read from table: 'resources/namebasics.tsv'");
    		}
    		else
    		{
    			fileScanner = new Scanner(table, "UTF-8");
				
				while(!searchFinished)
				{
        			if (fileScanner.hasNextLine())
        			{
             			entry = fileScanner.nextLine().split("\t");
						
						if (name.toLowerCase().equals(entry[1].toLowerCase())) //make case insensitive to help
						{
							result[0] = entry[0]; // nconst
							result[1] = entry[1]; // PrimaryName
							result[2] = entry[2]; // Birth
							result[3] = entry[3]; // Death
							result[4] = entry[4].replace(",", ", "); // Profession
							result[5] = "";

							movies = entry[5].split(","); // Movie Titles

							if (result[3].contains("\\N"))
							{
								result[3] = "-";
							}

							for (String m : movies)
							{
								result[5] += "- " + getTitle(m) + "\n";
							}

							searchFinished = true;
						}
        			}
        			else
        			{
						searchFinished = true;
        			}
				}
    		}
    	}

    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

		return result;
	}

	public String[] searchTitle(String title)
	{
		String[] entry;
		String[] result = new String[11];
		String[] movies;

		boolean searchFinished = false;

   		try
   		{
			File table = new File("resources/titlebasics.tsv");

    		if (!table.canRead())
    		{
    			System.out.println("Cannot read from table: 'resources/titlebasics.tsv'");
    		}
    		else
    		{
    			fileScanner = new Scanner(table, "UTF-8");
				
				while(!searchFinished)
				{
        			if (fileScanner.hasNextLine())
        			{
             			entry = fileScanner.nextLine().split("\t");
						
						if (title.toLowerCase().equals(entry[2].toLowerCase()) || title.toLowerCase().equals(entry[3].toLowerCase())) //make case insensitive to help.. also check both primary and original title
						{
							result[0] = entry[0]; // tconst
							result[1] = entry[1]; // titleType
							result[2] = entry[2]; // primaryTitle
							result[3] = entry[3]; // originalTitle
							result[4] = entry[4]; // isAdult
							result[5] = entry[5]; // startYear
							result[6] = entry[6]; // endYear
							result[7] = entry[7]; // runTimeMinutes
							result[8] = entry[8].replace(",", ", "); // genres

							if (result[4].equals("0")) //translate 0/1 to yes/no in terms of "isAdult"
							{
								result[4] = "No";
							}
							else
							{
								result[4] = "Yes";
							}

							if (result[6].contains("\\N")) //replace \N with -
							{
								result[6] = "-";
							}

							String[] ratingAndVote = getRatingAndVotes(result[0]);
							result[9] = ratingAndVote[0];
							result[10] = ratingAndVote[1];

							searchFinished = true;
						}
        			}
        			else
        			{
						searchFinished = true;
        			}
				}
    		}
    	}

    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

		return result;
	}
}