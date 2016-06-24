import java.util.Arrays;

public class HuffmanTreeTest
{
	public static int[] getFrequencies(String input)
	{
		int[] freq = new int[256];
		for (int i = 0; i < input.length(); i++ )
		{
			int value = (int) input.charAt(i);
			freq[value]++;	
		}
		return freq;
	}

	public static void main(String[] args)
	{
		String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
					 + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco "
					 + "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in "
					 + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat "
					 + "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";


		// get frequency array
		int[] frequencies = getFrequencies(testString);

		// get original length
		int orig = testString.length();
		System.out.println("Length of original string: " + orig);

		System.out.println("\nFrequency array:");
		System.out.println(Arrays.toString(frequencies));

		// create huffman tree from frequencies
		HuffmanTree ht = new HuffmanTree(frequencies);
		ht.displayCodes();

		// get BitStream of tree
		BitStream bs = ht.getTree().encodedTree();

		System.out.println("Huffman Tree BitStream:");
		System.out.println(bs);
		int treelen = bs.getBank().length;
		System.out.println("Length of tree stream: " + treelen);

		// encode string to bitstream
		BitStream os = ht.encode(testString);
		//BitStream os = ht.encode(testString.getBytes());

		System.out.println("Data BitStream:");
		System.out.println(os);

		int datalen = os.getBank().length;
		System.out.println("length of data stream: " + datalen);
		System.out.println("Compressed length: " + (datalen + treelen));
		System.out.println("Compressed %:" + (((float)(orig - treelen - datalen)/orig) * 100));

		// re-create huffman tree from encoded bitstream
		HuffmanTree dt = new HuffmanTree(bs);
		dt.displayCodes();

		// decode back to string
		String output = dt.decode(os);
		System.out.println(output);
			
	}
}