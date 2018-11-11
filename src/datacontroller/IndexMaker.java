package datacontroller;

import java.io.*;

public class IndexMaker
{
	public IndexMaker()
	{
		
	}

	public void CreateIndex(int columnToIndex, String tableName)
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
			PrintWriter printWriter = new PrintWriter(indexedFile);
			long offset = 0;
			String[] row;
			String lineRead = randomAccessFile.readLine();

			while (lineRead != null)
			{
				row = lineRead.split("\t");
				printWriter.println(offset + "\t" + row[columnToIndex]);
				printWriter.flush();

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