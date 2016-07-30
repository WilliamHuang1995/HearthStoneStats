/*
* HSCard.java
* Henry Chang
* This is an ADT for the cards in the video game Hearthstone.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

// finals
// Hero Classes
static final int Neutral = 0;
static final int Druid = 1;
static final int Hunter = 2;
static final int Mage = 3;
static final int Paladin = 4;
static final int Priest = 5;
static final int Rogue = 6;
static final int Shaman = 7;
static final int Warlock = 8;
static final int Warrior = 9;
// Sets
static final int Classic = 0;
static final int Naxxramas = 1;
static final int Goblins = 2;
static final int Blackrock = 3;
static final int Grand = 4;
static final int Explorers = 5;
static final int Old = 6;
//Rarity
static final int Basic = 0;
static final int Common = 1;
static final int Rare = 2;
static final int Epic = 3;
static final int Legendary = 4;

class HSCardList{
	// Private Class
	private class Card{
		// Field
		String Name;
		int cost;
		int set;
		int heroClass;
		int rarity;
		
		// Constructor
		Card(String Name, int cost, int set, int heroClass, int rarity ){
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
	private int[] script;
	
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
	List getList(){
		L = new List();
		in = new Scanner(new File(cards.csv));
		in.nextLine();
		while(in.hasNextLine()){
			info = in.nextLine();
			script = info.split(";");
			C = new Card(script[0],
			   Integer.parseInt(script[1]),
				Integer.parseInt(script[2]),
				Integer.parseInt(script[3]),
				Integer.parseInt(script[4])
			);
			size++;
		}
		
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
			   C = (Card) L.get()
			   sb.append(C.toString);
				sb.append("\n");
				L.moveNext();
			}
		}
		return new String(sb);
	}
}