/*
 * Stefon Miller
 * CS445
 * 9:30AM Tue/Thur class
 * 4PM Tuesday Lab
 * This program uses the RandINdexQueue class to simulate a simple blackjack scenario
 */
public class Blackjack 
{
	
	private static RandIndexQueue<Card> shoe;	//shoe of cards
	private static RandIndexQueue<Card> pHand;	//player's hand
	private static RandIndexQueue<Card> dHand;	//dealer's hand
	private static RandIndexQueue<Card> dPile;	//discard pile
	public static int totalCards;				//total # of cards
	public static int dWins;					//# of dealer's wins
	public static int pWins;					//# of player's wins
	public static int p;						//# of pushes
	public static void main(String[] args)
	{
		int s = Integer.parseInt(args[0]);		//# of decks
		int r = Integer.parseInt(args[1]);		//# of rounds
		//Make the shoe s number of decks big
		shoe = new RandIndexQueue<Card>(52*s);
		//Make the discard pile max size just in case
		dPile = new RandIndexQueue<Card>(52*s);
		//Make the hands the max size
		dHand = new RandIndexQueue<Card>(21);
		pHand = new RandIndexQueue<Card>(21);
		//Make the total cards the # of decks * # of cards in a deck
		totalCards = 52*s;
		//Set wins to 0
		pWins = 0;
		dWins = 0;
		p = 0;
		//Add the right # of decks and shuffle after each one is added
		for(int i = 1; i <= s; i++)
		{
			addDeck();
			shoe.shuffle();
		}
		//Play a detailed round if the user enters a round # less than or = to 10
		if(r <= 10)
		{
			//Exit if the user enters an invalid # of rounds
			if(r <= 0)
			{
				System.out.println("You entered an invalid number of rounds.  Exiting...");
				System.exit(0);
			}
			for(int i = 0; i < r; i++)
			{
				playDetailedRound(i);
			}
		}
		//If the user enters 10
		else
		{
			for(int i = 0; i < r; i++)
			{
				playRound(i);
			}
		}
		//Display the final results
		System.out.println("\n\n---------------------------------------");
		System.out.println("Total rounds played: " + r);
		System.out.println("Dealer wins: " + dWins);
		System.out.println("Player wins: " + pWins);
		System.out.println("Pushes: " + p);
		
		
		
	}
	/*
	 * Plays a detailed round
	 */
	public static void playDetailedRound(int j)
	{
		//Total of player and dealers' hands respectively
		int pTotal = 0;
		int dTotal = 0;
		//Number of cards in the player and dealer's hands respectively
		int nPCards = 0;
		int nDCards = 0;
		//Make sure the user didn't enter 0 for # of decks
		if(shoe.empty())
		{
			System.out.println("No cards in shoe! Exiting...");
			System.exit(0);
		}
		//Refill shoe if it's less than 1/4 it's original size
		if(shoe.size() < (double)(totalCards*.25))
		{
			System.out.println("Shoe size of " + shoe.size() + " is smaller than 1/4 of its capacity of " + totalCards);
			System.out.println("Shuffling at round " + j + "...");
			while(!dPile.empty())
			{
				shoe.addItem(dPile.removeItem());
				shoe.shuffle();
			}
		}
		//Fill the hands
		pHand.addItem(shoe.removeItem());
		pTotal = pTotal + pHand.get(nPCards).value();
		nPCards++;
		dHand.addItem(shoe.removeItem());
		dTotal = dTotal + dHand.get(nDCards).value();
		nDCards++;
		pHand.addItem(shoe.removeItem());
		pTotal = pTotal + pHand.get(nPCards).value();
		nPCards++;
		dHand.addItem(shoe.removeItem());
		dTotal = dTotal + dHand.get(nDCards).value();
		nDCards++;
		if(j == 0)
		{
			System.out.println("Round " + j + " now starting!");
		}
		else
		{
			System.out.println("\n\nRound " + j + " now starting!");
		}
		//If the player has a total of over 21, try to make aces one, if it's still above 21 they bust
		if(pTotal > 21)
		{
			//Make a new total with all aces evaluated as 1, if the total is 11 or less, make one ace equal 11 again
			int temp = 0;
			for(int i = 0; i < pHand.size(); i++)
			{
				temp = temp + pHand.get(i).value2();
			}
			if(temp == pTotal)
			{
				
			}
			else
			{
				if(temp <= 11)
				{
					temp += 10;
				}
				pTotal = temp;
			}
		}
		//Same process as above but for the dealer
		if(dTotal > 21)
		{
			int temp = 0;
			for(int i = 0; i < dHand.size(); i++)
			{
				temp = temp + dHand.get(i).value2();
			}
			if(temp == dTotal)
			{
				
			}
			else
			{
				if(temp <= 11)
				{
					temp += 10;
				}
				dTotal = temp;
			}
		}
		System.out.println("Player's hand " + pHand.toString() + " which equals " + pTotal);
		System.out.println("Dealer's hand " + dHand.toString() + " which equals " + dTotal);
		//Detect and tell the user if either the dealer of player get a blackjack
		if(dTotal == 21 && pTotal != 21)
		{
			System.out.println("Dealer got a BLACKJACK!");
			System.out.println("Dealer WINS!");
			dWins++;
		}
		else if(pTotal == 21 && dTotal != 21)
		{
			System.out.println("Player got a BLACKJACK!");
			System.out.println("Player WINS!");
			pWins++;
		}
		//If both get a blackjack, it's a push
		else if(pTotal == 21 && dTotal == 21)
		{
			System.out.println("Wow, BOTH players got a BLACKJACK!");
			System.out.println("Push!");
			p++;
		}
		else
		{
			//Hitting process for the dealer and player(only if they are under 17 in value)
			while(pTotal < 17)
			{
				//If the shoe is empty when a player tries to hit, refill it and reshuffle
				if(shoe.empty())
				{
					System.out.println("Shoe is empty!");
					System.out.println("Refilling...");
					while(!dPile.empty())
					{
						shoe.addItem(dPile.removeItem());
						shoe.shuffle();
					}
				}
				System.out.println("Player HITS on " + pTotal);
				//Take an item from the shoe
				pHand.addItem(shoe.removeItem());
				//Add it to the value
				pTotal = pTotal + pHand.get(nPCards).value();
				System.out.println("Player got a " + pHand.get(nPCards).toString());
				nPCards++;
				//If the total for the player is above 21, try to reevaluate the aces as 1
				if(pTotal > 21)
				{
					int temp = 0;
					for(int i = 0; i < pHand.size(); i++)
					{
						temp = temp + pHand.get(i).value2();
					}
					if(temp == pTotal)
					{
						
					}
					//If the total is less than 11(Two aces were evaluated as 1 and one can be reevaluated as 11), then reevaluate the other ace as 11
					else
					{
						if(temp <= 11)
						{
							temp += 10;
						}
						pTotal = temp;
					}
				}
				System.out.println("Player hand is now at " + pTotal);
			}
			//If the total is still over 21 after trying to resize, they bust
			if(pTotal > 21)
			{
				System.out.println("Player BUSTS with " + pHand.toString() + ", which is " + pTotal);
				System.out.println("Dealer wins!");
				dWins++;
			}
			//IF the player's hand is under 21 and above 17, they stand
			else
			{
				System.out.println("Player now STANDS with " + pHand.toString() + ", which is " + pTotal);
				//Same hitting process for the dealer
				while(dTotal < 17)
				{
					//If the shoe is empty when a player tries to hit, refill it and reshuffle
					if(shoe.empty())
					{
						System.out.println("Shoe is empty!");
						System.out.println("Refilling...");
						while(!dPile.empty())
						{
							shoe.addItem(dPile.removeItem());
							shoe.shuffle();
						}
					}
					System.out.println("Dealer HITS on " + dTotal);
					dHand.addItem(shoe.removeItem());
					dTotal = dTotal + dHand.get(nDCards).value();
					System.out.println("Dealer got a " + dHand.get(nDCards).toString());
					nDCards++;
					if(dTotal > 21)
					{
						int temp = 0;
						for(int i = 0; i < dHand.size(); i++)
						{
							temp = temp + dHand.get(i).value2();
						}
						if(temp == dTotal)
						{
							
						}
						else
						{
							if(temp <= 11)
							{
								temp += 10;
							}
							dTotal = temp;
						}
					}
					System.out.println("Dealer hand is now at " + dTotal);
				}
				//Same busting process as player
				if(dTotal > 21)
				{
					System.out.println("Dealer BUSTS with " + dHand.toString() + ", which is " + dTotal);
					System.out.println("Player wins!");
					pWins++;
				}
				//Dealer stands with a hand between 17 and 21
				else
				{
					System.out.println("Dealer now STANDS with " + dHand.toString() + ", which is " + dTotal);
					

					//If the dealer's total is greater than the player's, the dealer wins
					if(dTotal > pTotal)
					{
						System.out.println("Dealer total of " + dTotal + " is greater than player total of " + pTotal);
						System.out.println("Dealer wins!");
						dWins++;
					}

					//If the player's total is greater than the dealer's, the player wins
					else if(pTotal > dTotal)
					{
						System.out.println("Player total of " + pTotal + " is greater than the dealer total of " + dTotal);
						System.out.println("Player wins!");
						pWins++;
					}
					//If their totals are equal, it is a push
					else
					{
						System.out.println("Player total of " + pTotal + " equals the dealer total of " + dTotal);
						System.out.println("Push!");
						p++;
					}
				}
				
			}
			
			
			
			
		}
		//Add the hands to the discard pile
		while(!pHand.empty())
		{
			dPile.addItem(pHand.removeItem());
		}
		while(!dHand.empty())
		{
			dPile.addItem(dHand.removeItem());
		}
	}
	/*
	 * Same process as the playDetailedRound method, just without debug print statements
	 */
	public static void playRound(int j)
	{
		int pTotal = 0;
		int dTotal = 0;
		int nPCards = 0;
		int nDCards = 0;
		//Make sure the user didn't enter 0 for # of decks
		if(shoe.empty())
		{
			System.out.println("No cards in shoe! Exiting...");
			System.exit(0);
		}
		//Refill shoe if it's less than 1/4 it's original size
		if(shoe.size() < (double)(totalCards*.25))
		{
			System.out.println("Shoe size of " + shoe.size() + " is smaller than 1/4 of its capacity of " + totalCards);
			System.out.println("Shuffling at round " + j + "...");
			while(!dPile.empty())
			{
				shoe.addItem(dPile.removeItem());
				shoe.shuffle();
			}
		}
		//Fill the hands
		pHand.addItem(shoe.removeItem());
		pTotal = pTotal + pHand.get(nPCards).value();
		nPCards++;
		dHand.addItem(shoe.removeItem());
		dTotal = dTotal + dHand.get(nDCards).value();
		nDCards++;
		pHand.addItem(shoe.removeItem());
		pTotal = pTotal + pHand.get(nPCards).value();
		nPCards++;
		dHand.addItem(shoe.removeItem());
		dTotal = dTotal + dHand.get(nDCards).value();
		nDCards++;
		if(pTotal > 21)
		{
			int temp = 0;
			for(int i = 0; i < pHand.size(); i++)
			{
				temp = temp + pHand.get(i).value2();
			}
			if(temp == pTotal)
			{
				
			}
			else
			{
				if(temp <= 11)
				{
					temp += 10;
				}
				pTotal = temp;
			}
		}
		if(dTotal > 21)
		{
			int temp = 0;
			for(int i = 0; i < dHand.size(); i++)
			{
				temp = temp + dHand.get(i).value2();
			}
			if(temp == dTotal)
			{
				
			}
			else
			{
				if(temp <= 11)
				{
					temp += 10;
				}
				dTotal = temp;
			}
		}
		if(dTotal == 21 && pTotal != 21)
		{
			dWins++;
		}
		else if(pTotal == 21 && dTotal != 21)
		{
			pWins++;
		}
		else if(pTotal == 21 && dTotal == 21)
		{
			p++;
		}
		else
		{
			while(pTotal < 17)
			{
				if(shoe.empty())
				{
					while(!dPile.empty())
					{
						shoe.addItem(dPile.removeItem());
						shoe.shuffle();
					}
				}
				pHand.addItem(shoe.removeItem());
				pTotal = pTotal + pHand.get(nPCards).value();
				nPCards++;
				if(pTotal > 21)
				{
					int temp = 0;
					for(int i = 0; i < pHand.size(); i++)
					{
						temp = temp + pHand.get(i).value2();
					}
					if(temp == pTotal)
					{
						
					}
					else
					{
						if(temp <= 11)
						{
							temp += 10;
						}
						pTotal = temp;
					}
				}
			}
			if(pTotal > 21)
			{
				dWins++;
			}
			else
			{
				while(dTotal < 17)
				{
					if(shoe.empty())
					{
						while(!dPile.empty())
						{
							shoe.addItem(dPile.removeItem());
							shoe.shuffle();
						}
					}
					dHand.addItem(shoe.removeItem());
					dTotal = dTotal + dHand.get(nDCards).value();
					nDCards++;
					if(dTotal > 21)
					{
						int temp = 0;
						for(int i = 0; i < dHand.size(); i++)
						{
							temp = temp + dHand.get(i).value2();
						}
						if(temp == dTotal)
						{
							
						}
						else
						{
							if(temp <= 11)
							{
								temp += 10;
							}
							dTotal = temp;
						}
					}
				}
				if(dTotal > 21)
				{
					pWins++;
				}
				else
				{
					
					if(dTotal > pTotal)
					{
						dWins++;
					}
					else if(pTotal > dTotal)
					{
						pWins++;
					}
					else
					{
						p++;
					}
				}
				
			}
			
			
			
			
		}
		while(!pHand.empty())
		{
			dPile.addItem(pHand.removeItem());
		}
		while(!dHand.empty())
		{
			dPile.addItem(dHand.removeItem());
		}
	}
	/**
	 * Add a set of all 52 different cards to the shoe
	 */
	public static void addDeck()
	{
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Two));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Three));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Four));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Five));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Six));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Seven));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Eight));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Nine));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Ten));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Jack));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Queen));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.King));
		shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Ace));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Two));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Three));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Four));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Five));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Six));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Seven));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Eight));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Nine));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Ten));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Jack));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Queen));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.King));
		shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Ace));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Two));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Three));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Four));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Five));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Six));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Seven));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Eight));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Nine));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Ten));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Jack));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Queen));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.King));
		shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Ace));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Two));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Three));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Four));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Five));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Six));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Seven));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Eight));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Nine));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Ten));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Jack));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Queen));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.King));
		shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Ace));
			
		
		
	}
}
