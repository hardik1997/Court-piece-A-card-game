package project;

import java.util.Random;

public class Deck {

	private Card[] cards;
	
	public Deck(){
		cards=new Card[52];
		for(short i=0;i<4;i++){
			for(short j=0;j<13;j++){
				cards[j+13*i]=new Card(i,j);
			}
		}
	}
	//shuffles the cards by making random array
	public void shuffle(){
		Random rnd=new Random();
		for(short i=0;i<52;i++){
			int j=rnd.nextInt(52);
			Card temp=cards[i];
			cards[i]=cards[j];
			cards[j]=temp;
			
		}
	}
	//distributes cards between 4 players
	public void distribute(Player[] _players){
		
		for(short i=0;i<4;i++){
			Card[] temp=new Card[13];
			for(short j=0;j<13;j++){
				temp[j]=cards[j+13*i];
			}
			_players[i].get(temp);
		}
	}
	//sets a trump by selecting random card
	public Card setTrump(){
		Random rnd=new Random();
		int i=rnd.nextInt(cards.length);
		System.out.println("TrumpCard is "+cards[i].show().split("-")[1]+".\n");
		return cards[i];
	}
	
	
}
