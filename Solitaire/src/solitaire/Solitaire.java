package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * 
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
	
				CardNode current=deckRear.next;
	
				do{
			
					if(current.cardValue==27 && current.next==deckRear)
					{
						
						CardNode Next=current.next;
						current.cardValue=Next.cardValue;
						Next.cardValue=27;
						deckRear=Next;
						break;
						
					
					}
					
					if(deckRear.cardValue==27)
					{
							
						CardNode Next=deckRear.next;
						deckRear.cardValue=Next.cardValue;
						Next.cardValue=27;
						
						break;
					}
				
					
					
					if(current.cardValue==27)
					{
						
						CardNode Next=current.next;
						
						current.cardValue=Next.cardValue;
						Next.cardValue=27;
						
						break;
					}
					
					
					
					current=current.next;
				}
				
				while(current!=deckRear.next);
				
				
			}
		
		
	
	
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		
		if(deckRear==null)
		{
			return;
		}
		else
		{
			CardNode current=deckRear.next;
			
			do
			{
				//if two away from the deck rear
				if(current.cardValue==28 && current.next.next==deckRear)
				{	
					CardNode Next1=current.next;
					CardNode Next2=current.next.next;
					current.cardValue=Next1.cardValue;
					Next1.cardValue=28;
					Next1.cardValue=Next2.cardValue;
					Next2.cardValue=28;
					deckRear=Next2;
					break;
				}
				
				
				//if one away
				else if(current.cardValue==28 && current.next==deckRear)
				{
					
					CardNode Next1=current.next;
					
					CardNode Next2=current.next.next;
					
					current.cardValue=Next1.cardValue;
					Next1.cardValue=28;
					Next1.cardValue=Next2.cardValue;
					Next2.cardValue=28;
					deckRear=Next1;
					
					
					
					break;
					
				}
				else if(current.cardValue==28){	
					CardNode Next1=current.next;
					CardNode Next2=current.next.next;
					current.cardValue=Next1.cardValue;
					Next1.cardValue=28;
					Next1.cardValue=Next2.cardValue;
					Next2.cardValue=28;
					break;
				}
				current=current.next;
			}while(current!=deckRear.next);
			
		
			
		}
		
		
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
	
		//have a check if a joker is the head and if it is tail then proceed with a case
		// where the jokers are in the middle
		
		
		
		CardNode ptr = deckRear.next;
		
		//Joker on rear, also checks if joker on head
	if(deckRear.cardValue==27 || deckRear.cardValue==28)
	{
		CardNode Joker_Head =null;
		
		CardNode First_Head = null;
		CardNode First_Tail = null;
		CardNode Joker_Tail = deckRear;
		
		if(ptr.cardValue==27 || ptr.cardValue==28)
		{
			
			return;
		}
		First_Head=ptr;
		while(ptr!=deckRear)
		{
			if((ptr.cardValue == 28  || ptr.cardValue == 27) && Joker_Head==null)
			{
				Joker_Head=ptr;
				
			}
			if((ptr.cardValue!=28 || ptr.cardValue!=27) && First_Head!=null && Joker_Head==null )
			{
				First_Tail=ptr;
			}
			ptr=ptr.next;
		}
		
		Joker_Tail.next=First_Head;
		First_Tail.next=Joker_Head;
		deckRear=First_Tail;
		
		
	}
	//if no joker at rear but joker at head
	else if(ptr.cardValue==27 || ptr.cardValue==28)
	{
		CardNode Joker_Head =ptr;
		
		CardNode First_Head = null;
		CardNode First_Tail = deckRear;
		CardNode Joker_Tail = null;
		
		ptr=ptr.next;
		while(ptr!=deckRear)
		{
			if((ptr.cardValue == 28  || ptr.cardValue == 27) && Joker_Head!=null)
			{
				Joker_Tail=ptr;
				First_Head=Joker_Tail.next;
				break;
			}
			
			ptr=ptr.next;
		}
		
		First_Tail.next=Joker_Head;
		Joker_Tail.next=First_Head;
		deckRear=Joker_Tail;
		
		
	}
	
	else
	{
		CardNode Joker_Head =null;
		CardNode Joker_Tail = null;
		CardNode First_Head = null;
		CardNode First_Tail = null;
		CardNode Second_Head = null;
		CardNode Second_Tail = null;
		
		Second_Tail=deckRear;
		
		while(ptr!=deckRear)
		{	//find the first instance of a head
			
			if((ptr.cardValue == 28  || ptr.cardValue == 27) && Joker_Head==null)
			{
				Joker_Head=ptr;
				
			}
			//will set a joker tail if the joker head is already set
			if((ptr.cardValue == 28  || ptr.cardValue == 27) && Joker_Head!=null)
			{
				Joker_Tail=ptr;
			}
			//set the first head value
			if((ptr.cardValue!=28 || ptr.cardValue!=27) && First_Head==null)
			{
				First_Head=ptr;
			}
			
			if((ptr.cardValue!=28 || ptr.cardValue!=27) && First_Head!=null && Joker_Head==null )
			{
				First_Tail=ptr;
			}
							
			
			ptr=ptr.next;
			
		}
		
		Second_Head=Joker_Tail.next;
		Second_Tail.next=Joker_Head;
		
		Joker_Tail.next=First_Head;
		First_Tail.next=Second_Head;
		
		deckRear=First_Tail;
		
		
		
	}

	
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {
		
		if(deckRear.cardValue== 27 || deckRear.cardValue==28)
		{
			
			return;
		}
		else{
			
		
		CardNode head=null;
		CardNode tail=null;
		CardNode Before_deckRear=null;
		CardNode current=deckRear.next;
		for(int i =0;i<deckRear.cardValue;i++)
		{
			if(i==0)
			{
				
				head=current;
			}
			
			if(i==(deckRear.cardValue-1))
			{
				tail=current;
				
			
			}
			
			current=current.next;
		}
		
		
				deckRear.next=tail.next;
				
				while(current!=deckRear)
				{
					Before_deckRear=current;
					current=current.next;
				}
				
				Before_deckRear.next=head;
				
				
				tail.next=deckRear;
				
				tail.next=deckRear;
				
			
		}
			
		
			
			
			}

	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		
		
		CardNode tmp=null;
		do
		{
			jokerA();
			
			jokerB();
			
			tripleCut();
			
			countCut();
		
			
			tmp = deckRear.next;
			int length=tmp.cardValue;
			if(tmp.cardValue==28)
			{
				length--;
			}
			for(int i =0;i<length;i++)
			{
				
				tmp=tmp.next;
			}
		
		}while(tmp.cardValue==28 || tmp.cardValue==27);
		
		
		
		return tmp.cardValue;
		
		
	    
	}
	
	
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
	
		int messageLength = message.length();
		
		String encryption="";
		
		for(int i =0;i<messageLength;i++)
		{
			char c = message.charAt(i);
			if(Character.isLetter(c))
				
			{	
				int char_position = c-'A'+1;
				int key = getKey();
				//System.out.println(key);
				int sum=key+char_position;
				if(sum>26)
				{
					sum-=26;
				}
				
				char s=(char)(sum-1+'A');
				encryption+=s;
			}
			
			else
			{
				continue;
			}
		}
		
		return encryption;
		
		
		
	    
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		
int messageLength = message.length();
		
		String dencryption="";
		
		for(int i =0;i<messageLength;i++)
		{
			char c = message.charAt(i);
			
				int char_position = c-'A'+1;
				int key = getKey();
				//System.out.println(key);
				int sum=char_position-key;
				if(sum<=0)
				{
					sum+=26;
				}
				
				char s=(char)(sum-1+'A');
				dencryption+=s;
			}
			
			
		
		
		return dencryption;
		
		
	}
}
