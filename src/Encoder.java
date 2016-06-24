import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Encoder {
	static private int ALPHABET_SIZE = 27;
	static ArrayList <Integer> num_frq = new ArrayList<Integer>();
	
	/* only used for different alphabets, currently not implemented	*/
	static void incrementAlphabet(){
		ALPHABET_SIZE++;
	}
	
	private static int[] convertIntegers(ArrayList<Integer> integers){
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	static private double entropy(){
		double h = 0;
		
		for(int x : num_frq){
			
			double y = x;
			
			if(y <= 0)
				continue;
			System.out.print("h = "+ h + " - " + "y = " + y + "\n");
			h += ( y /ALPHABET_SIZE) * ( Math.log(y/ALPHABET_SIZE) / Math.log(2.0));
		}
		return -h;
	}
	
	public static void main(String[] args) throws Exception{
		
		/* pad beginning with 97 zeros	*/
		for (int i = 0; i < 97; i++)
			num_frq.add(0);
		
		/* add 27 chars	*/
		Reader.read(args[0], num_frq);
		
		/* pad end with 5 zeros	*/
		for (int i = 0; i < 5; i++)
			num_frq.add(0);
		
		for (int i = 0; i<num_frq.size(); i++)
			System.out.println(i + " - " + num_frq.get(i));
		
		/* create huffman tree from frequencies	*/
		HuffmanTree ht = new HuffmanTree(convertIntegers(num_frq));
		ht.displayCodes();
		
		System.out.println("Entropy " + entropy());
		
	}	
}

/*
 * Reader is a class that is responsible of reading a file
 */
class Reader { 
	static private File input = null;
	
	private static void setPath(String path)throws Exception{
		input = new File(path);
		
		if(!input.exists() || input.isDirectory()) { 
			throw new FileNotFoundException("File not found at path: " + path);
		}
		
	}
	
	/*	read method will read the file	*/
	static void read (String path, ArrayList <Integer> num_frq) throws Exception{
		
		setPath(path);
		
	    BufferedReader br = null;

	    try {
	    	String line;
		     br = new BufferedReader(new FileReader(input));
		     while ((line = br.readLine()) != null) {
		    	 num_frq.add( Integer.parseInt(line));
		     }
	    } 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
		    } 
			catch (IOException ex) {
				ex.printStackTrace();
		    }
		}
	}
}