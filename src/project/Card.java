package project;

public class Card {

	private short rank, suit;
	private String[] suits={"heart","spade","diamond","club"};
	private String[] ranks={"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K","A" };
	
	public Card(short _suit, short _rank){
		this.rank=_rank;
		this.suit=_suit;
	}
	
	//getter-setter methods
	public String show(){
		return ranks[rank]+"-"+suits[suit];
	}
	
	public short getRank(){
		return rank;
	}
	
	public short getSuit(){
		return suit;
	}
}
