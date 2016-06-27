package project;
import java.util.Scanner;
public class CourtPiece {

	public static void main(String[] args){
		
		Deck _deck = new Deck();
		Player[] _players=new Player[4];
		Card _trump;
		Scanner scan=new Scanner(System.in);
		
		//defines the objects of player
		for(short i=0;i<4;i++)
			_players[i]=new Player();
		
		_deck.shuffle();//shuffles the cards
		_deck.distribute(_players);//distributes shuffled cards among players
		
		//sets the team players
		_players[0].setTeamPlayer(_players[2]);
		_players[1].setTeamPlayer(_players[3]);
		_players[2].setTeamPlayer(_players[0]);
		_players[3].setTeamPlayer(_players[1]);
		
		//software runs in two modes
		System.out.println("In which mode do you want to play??\n1. As Human\n2. As computer");
		int mode=scan.nextInt();
		if(mode==1)
			_players[0].setHuman();
		else{
			_players[0].sete();
			_players[1].sete();
			_players[2].sete();
			_players[3].sete();
		}
		_trump=_deck.setTrump(); //sets the trump card
		
		short _score1=0,_score2=0,_index=0; //score1=team1_score score2=team2_score
		
		while(true){
			
			
			//loop will finish when any one of team gets more than 6 points
			if(_score1>6){
				System.out.println("Your team has won.");
				break;
			}
			if(_score2>6){
				System.out.println("Your team has lost.");
				break;
			}
			
			//prints the cards of players
			for(int i=0;i<4;i++){
				_players[i].print();
			}
			
			int[] rank=new int[4];
			int[] suit=new int[4];
			int k=0;
			Card choise=null;
			int i=0;
			
			//loop runs 4 times because there are 4 turns in one trick
			for(int j=0;j<4;j++){
				if((_index+i)>3){
					i=-_index;
				}
				
				//saves the instance of turn number
				_players[i+_index].setTurn(j);
				if(_players[i+_index].IsHuman()){
					if(choise==null)
						System.out.println("\nChoose the trick...");
					else{
						System.out.println("\n\nThe Trick Suit is "+choise.show().split("-")[1]+".");
						System.out.println("Enter the index of card...");
					}
					Card temp;
					while(true){
						temp=_players[i+_index].chooseCard(scan.nextShort(),choise,_trump);
						if(temp!=null)
							break;
					}
					
					//checks if turn is first or not...It turn is first then the card played by 1st player will be choice
					if(k==0){
						k++;
						choise=temp;
					}
					
					//checks played card has higher rank than choice or trump or not
					rank[i+_index]=temp.getRank();
					suit[i+_index]=temp.getSuit();
					if(rank[i+_index]>choise.getRank()&&suit[i+_index]==choise.getSuit())
						choise=temp;
					if(rank[i+_index]>_trump.getRank()&&suit[i+_index]==_trump.getSuit())
						_trump=temp;
					
					//prints the card which was played
					System.out.print("\n"+(_players[i+_index].equals(_players[0])?"You :\t\t":("Player-"+i+_index+" :\t"))+temp.show());
				}
				//if the player is not human then
				else{
					Card temp;
					
					//checks if turn is first or not...It turn is first then the card played by 1st player will be choice
					if(k==0){
						temp=_players[i+_index].chooseCard();
						choise=temp;
						k++;
					}
					else
						temp=_players[i+_index].chooseCard(_trump, choise);
					rank[i+_index]=temp.getRank();
					suit[i+_index]=temp.getSuit();
					
					//checks played card has higher rank than choice or trump or not
					if(rank[i+_index]>choise.getRank()&&suit[i+_index]==choise.getSuit())
						choise=temp;
					if(rank[i+_index]>_trump.getRank()&&suit[i+_index]==_trump.getSuit())
						_trump=temp;
					//prints the card which was played
					System.out.print("\nPlayer-"+(int)(i+_index)+" :\t"+temp.show());
				}
				i++;
			}
			int max1=-1,max2=-1;
			//finds the max of played choice and trump cards
			for(int j=0;j<4;j++){
				if(max1<rank[j]&&suit[j]==_trump.getSuit()){
					max1=rank[j];
					_index=(short) j;
				}
				if(max1==-1){
					if(max2<rank[j]&&suit[j]==choise.getSuit()){
						max2=rank[j];
						_index=(short) j;
					}
				}
			}
			int Team=0;
			//checks which team has won
			if(_index==0||_index==2)
				Team=1;
			else
				Team=2;
			//increment in scores
			if(Team==1)
				_score1++;
			else
				_score2++;
			//prints which team has won with their scores
			System.out.println("\n\nPlayer-"+_index+(Team==1?"(Your Team)":"(Opposite Team)")+" gets one point.\nYour Team Score : "+_score1+"\tOpposite Team Score : "+_score2+"\n");
		}
	}
}
