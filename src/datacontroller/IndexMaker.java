package datacontroller;

import java.io.*;

public class IndexMaker
{
	public IndexMaker()
	{
	}

	public void CreateIndex(String filter, int filterIndex, String tableName, int columnToIndex)
	{
		try
		{
			File table = new File("resources/" + tableName + ".tsv");
			File indexedFile = new File("resources/" + tableName + ".index.tsv");

			if (!table.canRead())
			{
				System.out.printf("Creation of index table failed because table could not be read: '%s'\n", tableName);
				return;
			}

			if (indexedFile.exists()) //always remove old and possibly outdated index table
			{
				indexedFile.delete();
			}

			indexedFile.createNewFile();

			RandomAccessFile randomAccessFile = new RandomAccessFile(table, "r");
			PrintWriter printWriter = new PrintWriter(indexedFile, "UTF-8");
			long offset = 0;
			String[] row;
			String lineRead = randomAccessFile.readLine();

			while (lineRead != null)
			{
				row = lineRead.split("\t");
				if (row[filterIndex].equals(filter))
				{
					printWriter.println(offset + "\t" + row[columnToIndex].toLowerCase());
					printWriter.flush();
				}

				offset = randomAccessFile.getFilePointer();
				lineRead = randomAccessFile.readLine();
			}

			randomAccessFile.close();
			printWriter.close();

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}