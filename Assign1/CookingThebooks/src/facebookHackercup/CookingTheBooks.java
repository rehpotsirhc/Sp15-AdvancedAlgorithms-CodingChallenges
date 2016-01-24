package facebookHackercup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CookingTheBooks {
    
    
    public static void main(String[] args)
    {
	
	String inputFileName = "files" + File.separator + "cooking_the_books.txt";
	String outputFileName = "files" + File.separator + "output.txt";
	
	List<String> input = readLines(inputFileName);
	List<String> output = new ArrayList<String>();
	
	int N = Integer.parseInt(input.get(0));
	
	
	
	for(int i = 1; i <= N; i++)
	{
	    String line = "Case #%d: "; 
	    String numberS = input.get(i);
	    line = String.format(line, i);
	    
	    if(numberS.length() == 1) line += numberS + " " + numberS;
	    else
	    {
		List<String> swaps = produceSwaps(numberS);
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(String swap : swaps)
		{
		    int swapInt = Integer.parseInt(swap);
		    if(!swap.startsWith("0"))
		    {
			if(swapInt < min) min = swapInt;
			if(swapInt > max) max = swapInt;
		    }
		}
		
		line += String.valueOf(min) + " " + String.valueOf(max);
		
		
	    }
	    output.add(line);
	    
	    
	}
	writeLines(outputFileName, output);
	
    }
    
    
    
    
    
    
    private static List<String> produceSwaps(String number)
    {
	
	
	
	List<String> swaps = new ArrayList<String>();
	swaps.add(number);
	
	char[] original = number.toCharArray();
	char[] changed = new char[original.length];
	
	for(int i = 0; i < original.length - 1; i++)
	{
	    char first = original[i];
	    for(int j = i + 1; j < original.length; j++)
	    {
		changed = Arrays.copyOf(original, original.length);
		
		char second = original[j];
		
		changed[i] = second;
		changed[j] = first;
		
		 String swap = String.valueOf(changed);
		 swaps.add(swap);
	    }
	   
	}

	return swaps;
	
    }
    
    
    private static List<String> readLines(String fileName)
    {

        try
            {
                return Files.readAllLines(Paths.get(fileName));
            }
        catch (IOException ex)
            {
                ex.printStackTrace();
            }

        return null;
    }
    
    private static void writeLines(String fileName, List<String> lines)
    {
        try
            {
                FileWriter fw = new FileWriter(fileName);
                for (String s : lines)
                    {


                        fw.write(s);
                        fw.write(System.getProperty("line.separator"));


                    }
                fw.close();
            }
        catch (IOException ex)
            {
                ex.printStackTrace();
            }
    }

}
