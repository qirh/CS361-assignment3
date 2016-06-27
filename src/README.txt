UTEID: mp34495; soa322;
FIRSTNAME: Ovais; Saleh;
LASTNAME: Panjwani; Alghusson;
CSACCOUNT: mp34495; almto3;
EMAIL: ovais.panjwani@utexas.edu; almto3@hotmail.com;

[Program 3]
[Description]
There are 5 java files:
We got BinaryHeap.java, Bitstream.java, HuffmanTree.java from the Internet, it's in implementation of the HuffmanTree algorithm. Link:
https://gist.github.com/snarkbait/86c7a4bc743e8f327dbc
https://gist.github.com/snarkbait/c939953337ad74d1ab04

For the other two files, Encoder.java and Frequency.java we handeled everything that was required by the specs. Encoder.java is the driver class and has the classes Encoder, Writer and Reader. Frequency.java has the classes Frequency and Range. These 5 classes all work together to produce the output. We both worked together equally, usually on the same screen throughout this project.
run the program with:
javac *.java
java Encoder [path to test cast]


[Finish]
We finished all requirements for this assignment, the reason it took us extra time to submit is that we both were busy during the last week and the 2 symbol implementation was really hard to implement without hogging a lot of memory, it's either we didn't fully understand what's asked, or we just didn't know how to implement it efficiently. for instance, for a 26 char input file (max input file), there will be a 26*26 = 676 symbol for this huffman implementation and out huffman implementation might break as such high numbers. However, for the 1 symbol implementation, all inputs should work.

[Test Case 1]

[Command line]

[Input]

[Output]




[Test Case 2]

[Command line]

[Input]

[Output]




[Test Case 3]

[Command line]

[Input]

[Output]



[Test Case 4]

[Command line]

[Input]

[Output]


