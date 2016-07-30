/*
* HSCardListTest.java
* Henry Chang
* This is a testing client for the HSCardList ADT.
*/

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

class HSCardListTest{
	public static void main(String[] args)throws FileNotFoundException{
		// Declaration
		PrintWriter out;
		HSCardList Collection = new HSCardList();
		
		// usage
		if(args.length != 1){
			System.err.println("Usage: HSCardListTest outfile");
			System.exit(1);
		}
		
		out = new PrintWriter(new File(args[0]));
		Collection.getList();
		out.println(Collection);
		out.close();
	}
}