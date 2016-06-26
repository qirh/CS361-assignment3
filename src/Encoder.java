/*
 * Program done by Saleh Alghusson and Ovais Panjwani
 * For CS f361
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;


public class Encoder {
	/*	how big is the alphabet?	*/
	static private int ALPHABET_SIZE = 0;

	/*	how many lines?	*/
	static private int LINES = 0;
	
	/*	ASCII table list, must have 128 elements to work, will pad the first 97 and last n elements	*/
	static ArrayList <Integer> num_frq = new ArrayList<Integer>();
	
	static HuffmanTree ht = null;
	
	/*	increments the counter	*/
	static void incrementAlphabet(int n){
		ALPHABET_SIZE += n;
	}
	/*	returns the counter	*/
	static int getAlphabet(){
		return ALPHABET_SIZE;
	}
	
	/*	returns the lines	*/
	static int getLines(){
		return LINES;
	}
	/*	increments the lines	*/
	static void incrementLines(int n){
		LINES += n;
	}
	

	
	/*	converts Integer objects to int primitive types	*/
	private static int[] convertIntegers(ArrayList<Integer> integers){
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	/*	returns the entropy	*/
	static private double bitsPerSymbol(String encExt) throws Exception{
		return ( 1.0 * Reader.readEncoding(encExt).length() ) / Reader.readFile().length();
	}
	
	static private double diffrence(double x, double y){
		return Math.abs(x-y)/ ( (x+y) / 2 ) * 100 ; 
	}
	/*	returns the entropy	*/
	static private double entropy(){
		double h = 0;
		
		for(int x : num_frq){
			double y = x;
			
			if(y <= 0)
				continue;
			
			//System.out.print("h = "+ h + " - " + "y = " + y + "\n");
			h += ( y /ALPHABET_SIZE) * ( Math.log(y/ALPHABET_SIZE) / Math.log(2.0));
		}
		return -h;
	}
	private static void setUpArray(String arg) throws Exception{
		/* pad beginning with 97 zeros	*/
		for (int i = 0; i < 97; i++)
			num_frq.add(0);
		/* add n chars	*/
		Reader.readInput(arg, num_frq);
		int fill = 26 - num_frq.size() + 97;
		/* pad end with 5 zeros + any fills	*/
		for (int i = 0; i < 5+fill; i++)
			num_frq.add(0);
	}
	
	private static double round (double d, int i){
		double out = d;
		BigDecimal bd = new BigDecimal(out);
		bd = bd.round(new MathContext(i));
		return bd.doubleValue();
		
		
	}
	
	/*	Main method	*/
	public static void main(String[] args) throws Exception{
		
	/* PART 1	*/
		System.out.println("PART 1");
		setUpArray(args[0]);
		System.out.println("The Entropy of this text is: " + round(entropy(), 3) +"\n");
		
	/* PART 2	*/
		/* create huffman tree from frequencies	*/
		System.out.println("PART 2");
		ht = new HuffmanTree(convertIntegers(num_frq));
		ht.displayChars();
	
	/* PART 3	*/
		System.out.println("PART 3");
		int k = 10000;
		String encExt = ".enc1";
		String decExt = ".dec1";
		Frequency.generateText(num_frq, ALPHABET_SIZE, k, encExt, decExt);
		System.out.println("Generated and written " + k + " random charecters in testText, encoded them into testText" + encExt +" and decoded them in testText"+ decExt + "\n");
	
	/* PART 4	*/
		System.out.println("PART 4");
		System.out.println("bits per symbol for " + k + " charecters: " + round(bitsPerSymbol(".enc1"), 3));
		System.out.println("a " + round(diffrence(bitsPerSymbol(".enc1"), entropy() ), 3) + "% difference from entropy" );
		
	/* PART 5	*/
		System.out.println("PART 5");
		
		encExt = ".enc2";
		decExt = ".dec2";
		
		
	}
}


/*
 * Writer is a class that is responsible of writing a file
 */
class Writer { 
	
	/*	/Users/almto3/Github/361assignment3/src/testText	*/
	static private final String filename = "testText";
	static private String filepath = "";
	
	private static void setPath()throws Exception{
		filepath = Reader.getPath().substring(0, Reader.getPath().lastIndexOf('/')+1) + filename;
	}
	
	static String getPath()throws Exception{
		return filepath;	
	}
	
	/*	write method will write to the file	*/
	static void writeFile (String content, String encExt, String decExt) throws Exception{
		setPath();	
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(filepath), "utf-8"));
		writer.write(content);
		writer.close();
		
		/*	encode the test and write it to testText.enc1	*/
		writeEncoding(content, encExt);
		
		/*	decode the encoded version and write it to testText.dec1	*/
		writeDecoding(encExt, decExt);
	}
	static private void writeEncoding (String content, String encExt) throws Exception{
		
		
		for (String line : Encoder.ht.returnChars().split("\n"))
			//System.out.println("line --> " + line + "\t" + line.substring(3));
			content = content.replace(line.substring(0, 1), line.substring(3));
		
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(filepath+encExt), "utf-8"));
		writer.write(content);
		writer.close();
	}
	
	static private void writeDecoding (String encExt, String decExt) throws Exception{
		
		String encodedText = Reader.readEncoding(encExt);
		String decodedText = "";
		String tmp = "";
		
		ArrayList<String> encodings = new ArrayList<String>();
		
		for(int i = 0; i<Encoder.getLines(); i++){
			encodings.add(Encoder.ht.returnChars().split("\n")[i].substring(3));
		}
		
		for (int i = 0; i < encodedText.length(); i++){
			tmp += encodedText.charAt(i);
			for (int j = 0; j<encodings.size(); j++){
				if(encodings.get(j).equals(tmp)){
					decodedText += Encoder.ht.returnChars().split("\n")[j].substring(0,1);
					tmp = "";
				}
			}
		}
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(filepath+decExt), "utf-8"));
		writer.write(decodedText);
		writer.close();
	}
}

	
/*
 * Reader is a class that is responsible of reading a file
 */
class Reader { 
	
	/*	/Users/almto3/Github/361assignment3/src/testcoin	*/
	static private String filePath = "";
	static private File input = null;
	static private BufferedReader br = null;
	
	static String getPath(){
		return filePath;
	}

	private static void setPath(String path)throws Exception{
		filePath = path;
		input = new File(path);
		
		if(!input.exists() || input.isDirectory()) { 
			throw new FileNotFoundException("File not found at path: " + path);
		}
		
	}
	
	
	/*	will read and return the encoded file may return null	*/
	static String readEncoding(String encExt) throws Exception{
		
		String filePath = Writer.getPath() + encExt;
		File input = new File(filePath);
		
		String encoded = "";
		try {
	    	String line;
		    br = new BufferedReader(new FileReader(input));
		    while ((line = br.readLine()) != null) {
		    	encoded += line;
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
		return encoded;
	}
	
	/*	read method will read the input file	*/
	static void readInput (String path, ArrayList <Integer> num_frq) throws Exception{
		setPath(path);	
	  
	    try {
	    	String line;
		    br = new BufferedReader(new FileReader(input));
		    while ((line = br.readLine()) != null) {
		    	int n = Integer.parseInt(line);
		    	num_frq.add(n);
		    	if (n>0){
		    		Encoder.incrementAlphabet(n);
		    		Encoder.incrementLines(1);
		    	}
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
	
	/*	will read the testText file written by Writer, cannot be called before readInput(String, ArrayList <Integer>) 
	 * returns a string	
	 */
	static String readFile() throws Exception{
		String filePath = Writer.getPath();
		File input = new File(filePath);
		
		String text = "";
		try {
	    	String line;
		    br = new BufferedReader(new FileReader(input));
		    while ((line = br.readLine()) != null) {
		    	text += line;
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
		return text;
	}
}