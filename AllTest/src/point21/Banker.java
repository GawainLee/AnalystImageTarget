package point21;

import java.util.ArrayList;

public class Banker {
	
	private ArrayList<Player> players;
	private Poker poker;
	private final int NUMBEROFPOKERSET = 3;
	
	public Banker(){
		players = new ArrayList<Player>();
		poker = new Poker(NUMBEROFPOKERSET);
		poker.shuffleCards();
	}
	
	public Banker(int numberOfPokerSet){
		players = new ArrayList<Player>();
		poker = new Poker(numberOfPokerSet);
		poker.shuffleCards();
	}

	public void addPlayer(){
		Player player = new Player();
		players.add(player);
	}
	
	public void deal()
	{
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.getPlayerOnHandNum()==0) {
				player.addOneHand();
			}
			for (int j = 0; j < player.getPlayerOnHandNum(); j++) {
				System.out.println("Player " + (i +1));
				if(player.getOneOnHandCards(j).isNeedBid()){
					player.getCardsForOneHand(j, poker.getCard(0));
				}
			}
			player.checkOnHandScore();
		}
		System.out.println(poker.getCardTotalNum());
	}
}
