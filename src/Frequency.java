import java.util.HashMap;
import java.util.Random;

public class Frequency {
	
	public static void main(String[] args){
		// Send in your frequency table below is an example
		double[] frequency = {.4, .2, .3, .1};
		HashMap<Range, Character> freq = new HashMap<Range, Character>();
		double sum = 0;
		// This creates ranges for each character based on its frequency
		for (int i = 0; i < frequency.length; i++){
			Range range = new Range(sum, sum + frequency[i]);
			sum += frequency[i];
			freq.put(range, (char)('A' + i));
		}
		// This makes a random number from 0 to 10000 and gives you a letter 
		// depending on the range created above
		Random randomGenerator = new Random();
		// The 10 below is your k characters that you need to generate
		for (int i = 0; i < 10; i++){
			int prob = randomGenerator.nextInt(10000);
			for(Range range: freq.keySet()){
				if (range.contains(prob/10000.0)){
					System.out.print(freq.get(range));
				}
			}
		}
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
        return (number >= low && number <= high);
    }
}
