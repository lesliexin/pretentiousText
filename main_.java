package terribleHacks;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class main_ {
	
	public static void writeToFile(String word) throws IOException{
		
		File textFile; 
		FileWriter out;  
		textFile = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/terribleHacks/src/terribleHacks/unprof.txt"); 
		out = new FileWriter(textFile, true); 
		BufferedWriter writeFile = new BufferedWriter(out);

		try 
		{			 
			writeFile.write(word + " ");
			
		}
		
		catch(IOException e)
		{
			System.out.println("Problem Writing to File");
	        System.err.println("IOException: "+e.getMessage());
		}
		
		writeFile.close();
		out.close();
		
	}
	
	public static String findWord(String line)
	{
		int start = 0;  
		int end = 0;
		
		for(int i=0;i<line.length();i++)
		{
			if (line.substring(i,i+1).equals("x"))
			{
				start = i+8; 
				for(int a=start;a<line.length();a++)
				{
					if(line.substring(a,a+1).equals(" "))
					{
						end = a; 
						break;
					}
				}
				break; 
			}
		
		}
		
		String word = line.substring(start, (end-1)); 
		
		return word; 
	}
	
	
	private static String getUrlSource(String word) throws IOException {
        
		String line = " ";
		URL website = new URL("http://www.thesaurus.com/browse/" + word + "?s=t");
        URLConnection yc = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;
        
        while ((inputLine = in.readLine()) != null)
        {
        	if(inputLine.contains("data-explore"))
        	{
        		line = inputLine; 
        		break; 
        	}
        }
        in.close();
        return line;
    }
	
	
	public static void main (String args[]) throws IOException{
		File file = new File("/Users/lesliexin/Documents/Coding/stockPriceAnalysis/terribleHacks/src/terribleHacks/professionalText.txt");
		
		FileReader readFile;
		BufferedReader read;
		String lineOfText = "";
		StringTokenizer s;
	
		
		try 
		{
			readFile = new FileReader(file); 
			read = new BufferedReader(readFile);

			lineOfText = read.readLine();
			
			s = new StringTokenizer(lineOfText);
			
			while (s.hasMoreTokens()){	
				String current = s.nextToken();
				
				if(current.length() > 3)
				{
					try {
				
					String sentence = getUrlSource(current);
					String synonym = findWord(sentence);
					writeToFile(synonym); 
					}
			
					catch (FileNotFoundException e)
					{
						writeToFile(current); 
					}
						
				}
				else 
					writeToFile(current); 
				
				
			}
			
			System.out.print("done");
		
		
		}
		catch (FileNotFoundException e)
		{
			
			System.out.println("File does not exist or could not be found");
            System.err.println("FileNotFoundException: "+e.getMessage());
		}
		
		catch (IOException e)
		{
			System.out.println("Problem Reading File");
            System.err.println("IOException: "+e.getMessage());
		}
		
		 
	}

}
