/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Huffman Tree class
 * for /r/javaexamples
 *
 * @author /u/Philboyd_Studge
 */
import java.util.Arrays;

public class HuffmanTree
{
	// the Tree of Trees
	private Tree htree;

	// the bit codes and lengths
	private int[] huffmanCodes = new int[256];
	private int[] huffmanLengths = new int[256];

	/**
	 * Create Huffman Tree for encoding
	 * and populates the code/length arrays
	 * based on a 256 element array of frequency counts
	 * @param frequencies integer array of frequency counts
	 */
	public HuffmanTree(int[] frequencies)
	{
		this.htree = getHuffmanTree(frequencies);
		getCodes();
	}

	/**
	 * Create Huffman Tree for decoding
	 * from a BitStream of the encoded tree
	 * frequencies have been discarded
	 * @param bs BitStream of encoded tree
	 */
	public HuffmanTree(BitStream bs)
	{
		this.htree = getHuffmanTree(bs);
		getCodes();
	}

	/**
	 * get Tree
	 * @return Tree Huffman Tree
	 */
	public Tree getTree()
	{
		return htree;
	}

	/**
	 * Encode a string to a huffman-encoded BitStream
	 * @param input String to encode
	 * @return output BitStream encoded stream
	 */
	public BitStream encode(String input)
	{
		BitStream output = new BitStream();
		for (int i = 0; i < input.length() ;i++ )
		{
			output.pushBits(huffmanCodes[(int) input.charAt(i)], huffmanLengths[(int) input.charAt(i)]);
		}
		output.close();
		return output;		
	}

	/**
	 * Encode a byte array to a huffman-encoded BitStream
	 * @param input byte[] array to encode
	 * @return output BitStream encoded stream
	 */
	public BitStream encode(byte[] input)
	{
		BitStream output = new BitStream();
		for (int i = 0; i < input.length; i++)
		{
			output.pushBits(huffmanCodes[input[i]], huffmanLengths[input[i]]);
		}
		output.close();
		return output;
	}

	/**
	 * Decode a huffman-encoded BitStream to String
	 * @param input BitStream of encoded data
	 * @return output decoded String
	 */
	public String decode(BitStream input)
	{
		String output = "";
		while (!input.EOB())
		{
			output += (char) htree.getCode(input);
		}
		return output;
	}

	/** 
	 * Decode a huffman-encoded BitStream to byte array
	 * @param input BitStream of encoded data
	 * @return output byte array
	 */
	 public byte[] decodeBytes(BitStream input)
	 {
	 	byte[] output = new byte[input.length() * 4];
	 	int counter = 0;
	 	while (!input.EOB())
	 	{
	 		output[counter] = (byte) (htree.getCode(input) & 0xff);
	 		counter++;
	 	}
	 	return Arrays.copyOfRange(output, 0, counter + 1);
	 } 

	/**
	 * Build Tree from frequency array
	 * Stores them in a Priority Queue
	 * pulls out two smallest and adds them together
	 * creates a new subtree
	 * @param frequencies integer array of frequencies
	 * @return Tree huffman tree 
	 */
	private Tree getHuffmanTree(int[] frequencies)
	{
		BinaryHeap<Tree> heap = new BinaryHeap<>();
		for (int i = 0; i < frequencies.length ; i++)
		{
			if (frequencies[i] > 0)
			{
				// add all frequencies > 0 as new subtree with no children
				heap.add(new Tree(i, frequencies[i]));
			}	
		}

		while (heap.length() > 1)
		{
			Tree t1 = heap.remove();
			Tree t2 = heap.remove();
			heap.add(new Tree(t1, t2));
		}

		return heap.remove();
	}

	/**
	 * Re-build tree from BitStream
	 * frequencies were not stored but are
	 * now unimportant
	 * @param bs BitStream of encoded tree 1 bit + literal byte or 0
	 * @return Tree Huffman Tree
	 */
	public Tree getHuffmanTree(BitStream bs)
	{
		if (bs.readBit())
		{
			return new Tree(bs.readBits(8), 0);
		}
		else
		{
			Tree left = getHuffmanTree(bs);
			Tree right = getHuffmanTree(bs);
			return new Tree(left, right);
		}

	}

	/**
	 * Build huffman codes based on current tree
	 */
	private void getCodes()
	{
		if (htree.root == null) return;
		getCodes(htree.root);
	}

	/**
	 * recursive helper class
	 */
	private void getCodes(Node root)
	{
		if (!htree.isLeaf(root))
		{
			root.left.huffmanCode = root.huffmanCode << 1;
			root.left.huffmanLen = root.huffmanLen + 1;
            getCodes(root.left);
            
            root.right.huffmanCode = root.huffmanCode << 1 ^ 1;
            root.right.huffmanLen = root.huffmanLen + 1;
            getCodes(root.right);
        }
        else
        {
            huffmanCodes[root.index] = root.huffmanCode;
            huffmanLengths[root.index] = root.huffmanLen;
        }
	}

	/**
	 * Show all non-zero codes
	 *
	 */
	public String returnCodes()
	{
		String x = "";
		for (int i = 0; i < 256; i++)
		{
			if (huffmanLengths[i] > 0)
			{
				x += i + ": " + toBinaryString(huffmanCodes[i], huffmanLengths[i]) + "\n";
			}
		}
		return x;
	}
	public String returnChars()
	{
		String x = "";
		for (int i = 0; i < 256; i++)
		{
			if (huffmanLengths[i] > 0)
			{
				x += Character.toString ((char) i) + ": " + toBinaryString(huffmanCodes[i], huffmanLengths[i]) + "\n";
			}
		}
		return x;
	}
	
	public void displayCodes()
	{
		System.out.println(this.returnCodes());
	}
	
	public void displayChars()
	{
		System.out.println(this.returnChars());
	}

	/**
	 * Binary String method with padding/truncating to specified length
	 * @param value integer value to convert
	 * @param length integer length to convert, adding zeroes at the front if necessary
	 * @return retval String binary representation of value at specified length
	 */
	public static String toBinaryString(int value, int length)
	{
		String retval = "";
		for (int i = length - 1; i >= 0; i--)
		{
			retval += ((value >> i & 1) == 1) ? "1" : "0";
		}
		return retval;
	}

	/**
	 * Tree class
	 */
	class Tree implements Comparable<Tree>
	{
		Node root;

		/**
		 * Create tree with childless leaf node
		 * @param index integer of character/byte value
		 * @param frequency count of occuring frequency
		 */
		public Tree(int index, int frequency)
		{
			root = new Node(index, frequency);
		}

		/**
		 * Create subtree with null node as root
		 * and total of two subtree frequencies
		 * @param tree1 left subtree
		 * @param tree2 right subtree
		 */
		public Tree(Tree tree1, Tree tree2)
		{
			root = new Node();
			root.left = tree1.root;
			root.right = tree2.root;
			root.frequency = tree1.root.frequency + tree2.root.frequency;
		}

		/**
		 * Encode Huffman Tree to BitStream
		 * if leaf node pushes 1 + literal byte
		 * otherwise 0
		 * @return bs BitStream with encoded tree
		 */
		public BitStream encodedTree()
		{
			BitStream bs = new BitStream();
			encodedTree(root, bs);
			bs.close();
			//System.out.println(bs);
			return bs;
		}

		/**
		 * recursive helper method
		 */
		private void encodedTree(Node node, BitStream bs)
		{
			if (isLeaf(node))
			{
				bs.pushBit(true);
				bs.pushBits(node.index, 8);
			}
			else
			{
				bs.pushBit(false);
				encodedTree(node.left, bs);
				encodedTree(node.right, bs);
			}
		}

		/**
		 * Get individual huffman code from current spot in tree
		 * recurses until leaf node found
		 */
	    public int getCode(BitStream bs)
	    {
	        Node current = root;
	        boolean bit;
	        while (!isLeaf(current))
	        {
	           bit = bs.readBit();
	           if (bit) current = current.right;
	           else current = current.left;
	           
	        }
	        return current.index;
	    }

	    /**
	     * is node a leaf node (childless)
	     * @param n Node to test
	     * @return true if node has no children
	     */
		public boolean isLeaf(Node n)
		{
			return n.left == null;
		}

		@Override
		public int compareTo(Tree t)
		{
			return root.frequency - t.root.frequency;
		}
	}

	/**
	 * Node Class
	 * 
	 */
	class Node
	{
		// actual ascii character value
		int index;

		// count
		int frequency;

		// integer value of huffman code
		int huffmanCode;

		// legth of huffman code in bits
		int huffmanLen;

		// left child
		Node left;

		//right child
		Node right;

		/**
		 * Create blank Node
		 */
		public Node()
		{

		}

		/**
		 * create Node based on index value and frequency count
		 */
		public Node(int index, int frequency)
		{
			this.index = index;
			this.frequency = frequency;
		}

		@Override
		public String toString()
		{
			return this.index + " : " + this.frequency;
		}


	}
}