## CS361 Foundations of Security. Assignment 3 (Entropy) [![MIT license](https://img.shields.io/badge/license-MIT-lightgrey.svg)](https://https://raw.githubusercontent.com/qirh/361assignment4/master/LICENSE)

### Description
Java project by me and [@Ovais](https://github.com/theBrovais). Done for CS f361, taught by the amazing Dr. Bill Young in the summer of 2016. For assignment details, please look [here](https://github.com/qirh/361assignment3/blob/master/assignment-3.pdf)

**There are 5 java files:**
* We got *BinaryHeap.java*, *Bitstream.java*, *HuffmanTree.java* from [here](https://gist.github.com/snarkbait/86c7a4bc743e8f327dbc) & [here](https://gist.github.com/snarkbait/c939953337ad74d1ab04) The files contain an implementation of the HuffmanTree algorithm.

* We wrote *Encoder.java* and *Frequency.java* and in them we handeled everything that was required by the specs.
    * *Encoder.java* is the driver class and has the classes Encoder, Writer and Reader.
    * *Frequency.java* has the classes Frequency and Range.

These 5 classes all work together to produce the output. We both worked together equally, usually on the same screen throughout this project. Upon running the program, the main method in the Encoder class will take care of everything

run the program with:

javac *.java

java Encoder [path to test case]

The test case must be a list of numbers (one in each line), not exceeding 26 numbers.


### Finish
We finished all requirements for this assignment.


### Test Case 1
* **Command line**
```
    $ javac *.java
    $ java Encoder test1
```
* **Input**
```
    22
    14
    2
    21
    12
    9
    23
    20
    8
    3
    3
```
* **Output**
    * **Part 1:** The Entropy of this text is: 3.15
    * **Part 2:**
```
        a: 111
        b: 010
        c: 011110
        d: 101
        e: 1101
        f: 1100
        g: 00
        h: 100
        i: 0110
        j: 011111
        k: 01110
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** bits per symbol for 10000 characters: 3.18. A 1.04% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters = 6.34. A 0.658% difference from entropy
```
            aa: 01001
            ab: 101011
            ac: 110011001
            ad: 00011
            ae: 011100
            af: 1101111
            ag: 01100
            ah: 111111
            ai: 1100101
            aj: 01110100
            ak: 01110111
            ba: 101010
            bb: 1101110
            bc: 1111011011
            bd: 101000
            be: 1011111
            bf: 0101010
            bg: 101110
            bh: 100100
            bi: 11110111
            bj: 101111010
            bk: 110000100
            ca: 110011000
            cb: 001010100
            cc: 101001101110
            cd: 110000101
            ce: 1110001001
            cf: 1001101011
            cg: 110011011
            ch: 101001111
            ci: 0010101011
            cj: 101001101111
            ck: 111101101000
            da: 00100
            db: 100111
            dc: 101111011
            dd: 00000
            de: 010110
            df: 1101011
            dg: 01000
            dh: 111100
            di: 1100011
            dj: 00110111
            dk: 01010111
            ea: 100000
            eb: 1100000
            ec: 1110001100
            ed: 010100
            ee: 1010010
            ef: 11110100
            eg: 100011
            eh: 001011
            ei: 11011011
            ej: 100110111
            ek: 100110100
            fa: 1110000
            fb: 0101110
            fc: 1001101010
            fd: 1101100
            fe: 11110101
            ff: 10111100
            fg: 1110101
            fh: 1101000
            fi: 10011000
            fj: 1111011000
            fk: 1111011001
            ga: 01101
            gb: 101101
            gc: 110011010
            gd: 00111
            ge: 100001
            gf: 1110100
            gg: 01111
            gh: 00010
            gi: 1101001
            gj: 10001010
            gk: 10001011
            ha: 111110
            hb: 100101
            hc: 101001110
            hd: 111011
            he: 001100
            hf: 1100111
            hg: 00001
            hh: 111001
            hi: 1011000
            hj: 00110100
            hk: 00110101
            ia: 1100100
            ib: 0010100
            ic: 0010101010
            id: 1100010
            ie: 11011010
            if: 10011001
            ig: 1101010
            ih: 1011001
            ii: 01011111
            ij: 1110001101
            ik: 1110001010
            ja: 01110101
            jb: 110000111
            jc: 111101101001
            jd: 01011110
            je: 100110110
            jf: 1110001111
            jg: 10001000
            jh: 00101011
            ji: 1110001011
            jj: 111101101011
            jk: 10100110100
            ka: 01110110
            kb: 110000110
            kc: 111101101010
            kd: 01010110
            ke: 101001100
            kf: 1110001110
            kg: 10001001
            kh: 00110110
            ki: 1110001000
            kj: 10100110101
            kk: 10100110110
```
    * **Part 7:** A 0.382% efficiency increase from the 1 symbol implementation


### Test Case 2
* **Command line**
```
    $ javac *.java
    $ java Encoder test2
```
* **Input**
```
    17
    22
    5
    27
    3
    2
    15
    9
```
* **Output**
    * **Part 1:** The Entropy of this text is: 2.63
    * **Part 2:**
```
        a: 111
        b: 01
        c: 0010
        d: 10
        e: 00111
        f: 00110
        g: 110
        h: 000
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** Bits per symbol for 10000 characters: 2.68. A 1.76% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters: 5.3. A 0.797% difference from entropy
```
        aa: 01100
        ab: 11010
        ac: 1010110
        ad: 0000
        ae: 11000111
        af: 01001111
        ag: 00111
        ah: 011011
        ba: 11001
        bb: 0010
        bc: 1111010
        bd: 0111
        be: 0100110
        bf: 11000000
        bg: 10011
        bh: 110110
        ca: 1010111
        cb: 1111001
        cc: 110000111
        cd: 010101
        ce: 1111000001
        cf: 1100001100
        cg: 0110100
        ch: 11000101
        da: 11111
        db: 1000
        dc: 010100
        dd: 1011
        de: 1010101
        df: 11110001
        dg: 11100
        dh: 00011
        ea: 11000110
        eb: 0100101
        ec: 010010010
        ed: 1010100
        ee: 0100100111
        ef: 11110000001
        eg: 11000100
        eh: 111101110
        fa: 01001110
        fb: 10010101
        fc: 1100001101
        fd: 11110110
        fe: 0100100110
        ff: 11110000000
        fg: 111101111
        fh: 100101000
        ga: 01000
        gb: 10100
        gc: 0110101
        gd: 11101
        ge: 11000010
        gf: 01001000
        gg: 00010
        gh: 010110
        ha: 100100
        hb: 110111
        hc: 11000001
        hd: 00110
        he: 111100001
        hf: 100101001
        hg: 010111
        hh: 1001011
```
    * **Part 7:** A 0.963% efficiency increase from the 1 symbol implementation

### Test Case 3
* **Command line**
```
        $ javac *.java
        $ java Encoder test3
```
* **Input**
```
  3
  1
  26
  17
  2
  25
  16
```
* **Output**
    * **Part 1:** The Entropy of this text is: 2.29
    * **Part 2:**
```
      a: 0100
      b: 01010
      c: 11
      d: 00
      e: 01011
      f: 10
      g: 011
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** Bits per symbol for 10000 characters: 2.32. A 1.64% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters: 4.6. A 0.571% difference from entropy
```
      aa: 0100111001
      ab: 110011001011
      ac: 1100100
      ad: 0100010
      ae: 11001100110
      af: 1010111
      ag: 11001110
      ba: 110011001110
      bb: 1100110011110
      bc: 01001001
      bd: 101010011
      be: 1100110011111
      bf: 01000011
      bg: 101010010
      ca: 1100101
      cb: 01001000
      cc: 1111
      cd: 1000
      ce: 0100101
      cf: 1101
      cg: 0011
      da: 0100011
      db: 110011000
      dc: 0111
      dd: 11000
      de: 10101011
      df: 0110
      dg: 10011
      ea: 0100111000
      eb: 110011001010
      ec: 0100110
      ed: 10101010
      ee: 11001100100
      ef: 0100000
      eg: 01001111
      fa: 1010110
      fb: 01000010
      fc: 1110
      fd: 0101
      fe: 11001111
      ff: 1011
      fg: 0001
      ga: 11001101
      gb: 010011101
      gc: 0010
      gd: 10100
      ge: 10101000
      gf: 0000
      gg: 10010
```
    * **Part 7:** A 1.07% efficiency increase from the 1 symbol implementation

### Test Case 4
* **Command line**
```
        $ javac *.java
        $ java Encoder test4
```
* **Input**
```
  3
  1
  26
  19
```
* **Output**
    * **Part 1:** The Entropy of this text is: 1.38
    * **Part 2:**
```
      a: 001
      b: 000
      c: 1
      d: 01
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** Bits per symbol for 10000 characters: 1.55. A 12.0% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters: 2.78. A 1.08% difference from entropy
```
      aa: 111111101
      ab: 1111111000
      ac: 11101
      ad: 111101
      ba: 11111110011
      bb: 11111110010
      bc: 1111110
      bd: 11111111
      ca: 11100
      cb: 1111001
      cc: 10
      cd: 00
      da: 111110
      db: 1111000
      dc: 01
      dd: 110
```
    * **Part 7:** A 10.9% efficiency increase from the 1 symbol implementation

### Test Case 5
* **Command line**
```
    $ javac *.java
    $ java Encoder testcoin
```
* **Input**
```
    2
    1
```
* **Output**
    * **Part 1:** The Entropy of this text is: 0.918
    * **Part 2:**
```
      a: 1
      b: 0
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** Bits per symbol for 10000 characters: 1.00. A 8.52% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters: 1.89. A 2.77% difference from entropy
```
      aa: 0
      ab: 111
      ba: 10
      bb: 110
```
    * **Part 7:** A 5.75% efficiency increase from the 1 symbol implementation

### Test Case 6
* **Command line**
```
    $ javac *.java
    $ java Encoder testdice
```
* **Input**
```
  1
  1
  1
  1
  1
  1
```

* **Output**
    * **Part 1:** The Entropy of this text is: 2.58
    * **Part 2:**
```
      a: 00
      b: 101
      c: 111
      d: 110
      e: 100
      f: 01
```
    * **Part 3:** Generated and written 10000 random 1 symbol characters in testText, encoded them into testText.enc1 and decoded them in testText.dec1
    * **Part 4:** Bits per symbol for 10000 characters: 2.67. A 3.25% difference from entropy
    * **Parts 5&6:** Generated 10000 random 2 symbol characters, encoded them into testText.enc2 and decoded them in testText.dec2. Bits per symbol for 10000 characters: 5.22. A 0.955% difference from entropy
```
      aa: 01110
      ab: 01011
      ac: 00101
      ad: 00001
      ae: 111011
      af: 01000
      ba: 11011
      bb: 10000
      bc: 01101
      bd: 00011
      be: 111000
      bf: 01001
      ca: 11010
      cb: 10010
      cc: 10011
      cd: 10001
      ce: 10111
      cf: 10101
      da: 111101
      db: 11001
      dc: 111111
      dd: 111001
      de: 00100
      df: 111110
      ea: 11000
      eb: 00010
      ec: 111010
      ed: 111100
      ee: 10100
      ef: 01100
      fa: 10110
      fb: 00110
      fc: 00111
      fd: 00000
      fe: 01010
      ff: 01111
```
    * **Part 7:** A 2.29% efficiency increase from the 1 symbol implementation
