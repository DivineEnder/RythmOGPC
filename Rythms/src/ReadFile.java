import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class ReadFile 
{
	private String path;
	private int numberOfLines;
	
	public ReadFile (String file_path)
	{
		path = file_path;
		
	}
	
	public String[] OpenFile() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		
		for (int i = 0; i < numberOfLines; i++)
		{
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	public int readLines() throws IOException
	{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		numberOfLines = 0;
		while ((aLine = bf.readLine()) != null)
		{
			numberOfLines++;
		}
		bf.close();
		
		return numberOfLines;
	}
	
	public int[][] toIntList(String[] converting)
	{
		int[][] converted = new int[numberOfLines][4];
		
		String[][] converting2 = new String[numberOfLines][5];
		String delims = "[,()]+";
		for (int i = 0; i < converting.length; i++)
		{
			converting2[i] = converting[i].split(delims);
		}
		
		for (int i = 0; i < converting2.length; i++)
		{
			for (int j = 0; j < converting2[i].length; j++)
			{
				if (j != 0)
				{
					converted[i][j-1] = Integer.parseInt(converting2[i][j]);
				}
			}
		}
		
		return converted;
	}
}
