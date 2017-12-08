## How to start?

1. Open cmd prompt
2. Execute :

         $ git clone https://github.com/adwansyed/java_btavl.git 
         $ cd btavl
	 
#### For bt
-----------
                
    $ cd bt/src
    $ javac bt.java
    $ java bt 100 1000000 1000 14 90 150 900 1500 9999 15236 147258 0 2 99 456 147 1478 15000 12369 #example
#### For btavl
--------------
      
    $ cd btavl/src
    $ javac btavl.java
    $ java btavl 100 1000000 1000 14 90 150 900 1500 9999 15236 147258 0 2 99 456 147 1478 15000 12369 #example

#### The third input is the _stream_ _lenght_ => to be changed to complete the table :

Test case example :

 | stream lenght  |   AVL Tree    |    AVL Search     |    AVL At      |    Tree       		   |    Search    |    At     |
 | ---------------|---------------|-------------------|----------------|-----------------------|--------------|-----------|
 |	100			  |	  4 ms	      | 		0 ms      |     0 ms       |	2 ms	           |  	0 ms	  |  0 ms     |
 |	1 000	      |    6 ms       |      0 ms         |     0 ms       | 7 ms                  |     0 ms     |  0 ms     |
 |	50 000		  |    54 ms      |      2 ms         |     0 ms       | 5259 ms               |     3 ms     |  0 ms     |
 |	500 000		  |    554 ms     |      11 ms        |     0 ms       | 1111831 ms (18 min)   |     13 ms    |  1 ms     |
 |	1 000 000     |    1153 ms    |      13 ms        |     1 ms       | 4901822 ms ( ~ 1.5 hours)|     22 ms    |  2 ms     |
	
