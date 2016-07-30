package william;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ExcelReader {

	public static final String fileName = "cards.csv";

	public ExcelReader() {

	}
	
	public static void main(String[] args) throws IOException{
		HashMap<String, ArrayList<String>> map = ReadFile();
		if(map!=null){
			System.out.println("SUCCESS");
		}
		ArrayList<String> attribute = map.get("Nat, the Darkfisher");
		
		//Some sample usage of the map
		System.out.println("Nat, the Darkfisher "+attribute);
		attribute = map.get("Ogre Magi");
		System.out.println("Ogre Magi "+attribute);
		attribute = map.get("Doomguard");
		System.out.println("Doomguard "+attribute);
	}

	/*
	 * Reads the excel file and output a mapping of rules for each product part
	 */
	private static HashMap<String, ArrayList<String>> ReadFile() throws IOException {
		int count = 0;
		String attrStr = null;
		BufferedReader br = null;
		String scanCurrentLine;
		HashMap<String, ArrayList<String>> cardDatabase = new HashMap<String, ArrayList<String>>();

		// read the first line
		FileReader reader = new FileReader(fileName );
		br = new BufferedReader(reader);
		scanCurrentLine = br.readLine();

		// parse the first line and skip it
		StringTokenizer tok = new StringTokenizer(scanCurrentLine);
		while (tok.hasMoreElements()) {
			attrStr = tok.nextToken(",");
		}

		while ((scanCurrentLine = br.readLine()) != null) {
			ArrayList<String> ListOfAttr = new ArrayList<String>();
			String tempStr = null;

			tok = new StringTokenizer(scanCurrentLine);
			attrStr = tok.nextToken(";");
			count = 0;
			while (tok.hasMoreElements()) {
				count++;
				tempStr = tok.nextToken(";");
				// to compare the value, if 1 add to ArrayList
				if (!tempStr.isEmpty()) {
					ListOfAttr.add(new String(tempStr.getBytes("utf-8"), "utf-8"));
				}
				cardDatabase.put(attrStr, ListOfAttr);
			}
			// to prevent empty input
			if (count == 0) {
				ListOfAttr.add("Fuck");
				cardDatabase.put(attrStr, ListOfAttr);
			}
		}


		br.close();
		return cardDatabase;
	}
}
