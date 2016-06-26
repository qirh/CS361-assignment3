import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Frequency {
	/* array of the frequency of characters	*/
	static ArrayList<Double> frq = new ArrayList<Double>(); 
	
	static void generateText(ArrayList<Integer> occurances, int ALPHABET_SIZE, int k, String encExt, String decExt) throws Exception{
		for (int x : occurances){
			double y = x;
			frq.add( y/ALPHABET_SIZE );
		}
		HashMap<Range, Character> freqmap = new HashMap<Range, Character>();
		double sum = 0;
		
		// This creates ranges for each character based on its frequency
		for (int i = 0; i < frq.size(); i++){
			Range range = new Range(sum, sum + frq.get(i));
			sum += frq.get(i);
			freqmap.put(range, (char)(i));
		}
		
		// This makes a random number from 0 to 10000 and gives a letter depending on the range created above
		Random randomGenerator = new Random();
		// The 10 below is your k characters that you need to generate
		String output = "";
		for (int i = 0; i < k; i++){
			int prob = randomGenerator.nextInt(k);
			for(Range range: freqmap.keySet()){
				if (range.contains((1.0*prob)/k)){
					if(freqmap.get(range).equals('#'))
						throw new Exception("EQUALS --> " + (1.0*prob)/k);
					output += freqmap.get(range);
				}
			}
		}
		Writer.writeFile(output, encExt, decExt);
	}
}

class Range {
    private double low;
    private double high;
    public Range(double low, double high){
        this.low = low;
        this.high = high;
    }
    public boolean contains(double number){
        return (number > low && number < high);
    }
}
