/*
* HSCard.java
* Henry Chang
* This is an ADT for the cards in the video game Hearthstone.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;



class HSCardList{
	
	// finals
   // Hero Classes
   final int Neutral = 0;
   final int Druid = 1;
   final int Hunter = 2;
   final int Mage = 3;
   final int Paladin = 4;
   final int Priest = 5;
   final int Rogue = 6;
   final int Shaman = 7;
   final int Warlock = 8;
   final int Warrior = 9;
   // Sets
   final int Classic = 0;
   final int Naxxramas = 1;
   final int Goblins = 2;
   final int Blackrock = 3;
   final int Grand = 4;
   final int Explorers = 5;
   final int Old = 6;
   //Rarity
   final int Basic = 0;
   final int Common = 1;
   final int Rare = 2;
   final int Epic = 3;
   final int Legendary = 4;
	
	// Private Class
	private class Card{
		// Field
		String Name;
		int cost;
		String set;
		String heroClass;
		String rarity;
		
		// Constructor
		Card(String Name, int cost, String set, String heroClass, String rarity ){
			this.Name = Name;
			this.cost = cost;
			this.set = set;
			this.heroClass = heroClass;
			this.rarity = rarity;
			
		}
		
		// toString()
		public String toString(){
			return Name;
		}
	}
	// Fields
	private List L;
	private int size;
	private Scanner in;
	private Card C;
	private String info;
	private String[] script;
	
	// Constructor
	HSCardList(){
		size = 0;
		L = null;
		in = null;
		C = null;
		info = null;
		script = null;
	}
	
	// Acess Functions
	
	// getSize()
	// Return the size of the cardList
	int getSize(){
		return size;
	}
	
	// getList()
	// Return the entire card collection list
	List getList() throws FileNotFoundException{
		L = new List();
		in = new Scanner(new File("cards.csv"));
		in.nextLine();
		while(in.hasNextLine()){
			info = in.nextLine();
			script = info.split(";");
			C = new Card(script[0],
			   Integer.parseInt(script[1]),
				script[2],
				script[3],
				script[4]
			);
			L.append(C);
			size++;
		}
		in.close();
		return L;	
	}
	
	// Manipulative Functions
	
	// Other Functions
	
	// toString()
	// Overrides Object's toString method
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if(L!=null){
			L.moveFront();
			while(L.index()!=-1){
			   C = (Card) L.get();
			   sb.append(C.toString());
				sb.append("\n");
				L.moveNext();
			}
		}
		return new String(sb);
	}
}