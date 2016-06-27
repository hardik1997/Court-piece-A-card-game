package project;

public class Player {

	private Card[] cards;
	private short numberOfCards;
	private boolean _IsHuman;
	private Player _teamPlayer;
	private Card _playedCard;
	private int turn;
	private int e;
	
	public Player(){
		_IsHuman=false;
		e=0;
	}
	//gets the array of cards which were distributed and got by this player and stores them into cards array
	public void get(Card[] _cards){
		this.cards=_cards;
		//sorts the array
		for(short i=0;i<13;i++){
			for(short j=0;j<12-i;j++){
				if(cards[j].getRank()>cards[j+1].getRank()){
					Card temp=cards[j];
					cards[j]=cards[j+1];
					cards[j+1]=temp;
				}
			}
		}
		//number of cards that the player have
		numberOfCards=(short) cards.length;
	}
	
	//method to choose card if it was chosen by computer
	public Card chooseCard(Card _trump,Card _choise){
		if(turn==3){
			for(short i=0;i<numberOfCards;i++){
				if(cards[i].getSuit()==_choise.getSuit()){
					Card temp=cards[i];
					remove(i);
					_playedCard=temp;
					return temp;
				}
			}
			for(short i=0;i<numberOfCards;i++){
				if(cards[i].getSuit()==_trump.getSuit()){
					Card temp=cards[i];
					remove(i);
					_playedCard=temp;
					return temp;
				}
			}
		}
		for(short i=0;i<numberOfCards;i++){
			if(cards[i].getSuit()==_choise.getSuit()){
				if(cards[i].getRank()>_choise.getRank()){
					Card temp=cards[i];
					remove(i);
					_playedCard=temp;
					return temp;
				}
			}
		}
		for(short i=0;i<numberOfCards;i++){
			if(cards[i].getSuit()==_choise.getSuit()){
				Card temp=cards[i];
				remove(i);
				_playedCard=temp;
				return temp;
			}
		}
		for(short i=0;i<numberOfCards;i++){
			if(cards[i].getSuit()==_trump.getSuit()&&cards[i].getRank()>_trump.getRank()){
				Card temp=cards[i];
				remove(i);
				_playedCard=temp;
				return temp;
			}
		}
		Card temp=cards[0];
		remove((short)0);
		return temp;
	}
	
	//method to choose card if it was chosen by human
	public Card chooseCard(short _index,Card _choise,Card _trump){
		if(_index<numberOfCards){
			if(_choise!=null){
				if(cards[_index].getSuit()==_choise.getSuit()){
					Card temp=cards[_index];
					remove(_index);
					_playedCard=temp;
					return temp;
				}
				else{
					int i;
					for(i=0;i<numberOfCards;i++){
						if(cards[i].getSuit()==_choise.getSuit())
							break;
					}
					if(i==numberOfCards){
						Card temp=cards[_index];
						remove(_index);
						_playedCard=temp;
						return temp;
						
					}
					else{
						System.err.println("You can't choose this card...Choose the correct card...");
						return null;
					}
				}
			}	
			else{
				Card temp=cards[_index];
				remove(_index);
				_playedCard=temp;
				return temp;
			}
		}
		else
			return null;
	}
	
	//method to choose card if card was chosen first time by computer when choice card is not set
	public Card chooseCard(){
		Card temp=cards[numberOfCards-1];
		remove((short) (numberOfCards-1));
		_playedCard=temp;
		return temp;
	}
	
	//to remove card
	private void remove(short i){
		for(short j=i;j<numberOfCards-1;j++){
			cards[j]=cards[j+1];
		}
		numberOfCards--;
	}
	
	//prints cards that player have
	public void print(){
		if(this._IsHuman||e==1){
			for(short i=0;i<numberOfCards;i++){
				System.out.print(cards[i].show()+"  ");
			}
		}
		else{
			for(short i=0;i<numberOfCards;i++){
				System.out.print("-------- ");
			}
		}
		System.out.println();
	}
	
	//getter-setter methods
	public void setHuman(){
		_IsHuman=true;
	}
	public boolean IsHuman(){
		return _IsHuman;
	}
	public void setTeamPlayer(Player _player){
		_teamPlayer=_player;
	}
	public Card playedCard(){
		return _playedCard;
	}
	public void setTurn(int i){
		turn=i;
	}
	public void sete(){
		e=1;
	}
}
