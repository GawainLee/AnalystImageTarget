package point21;

import java.util.ArrayList;
import java.util.Random;

public class Poker {

	private String[] poketType = {"spade","heart","club","diamond"};
	private String[] poketNum = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	private final int ONEPOKETCARDNUM = 52;
	
	private ArrayList<Card> cards;
	
	private int numPoketSet = 1;
	
	public Poker()
	{
		inte();
	}
	
	
	public Poker(int numPokerSet)
	{
		this.numPoketSet = numPokerSet;
		inte();
	}
	
	/**
	 * get cards and set cards point
	 */
	private void inte()
	{
		cards = new ArrayList<Card>();
		for (int numPokerSetTemp = 0; numPokerSetTemp < numPoketSet; numPokerSetTemp++) {
			for (int poketTypeTemp = 0; poketTypeTemp < poketType.length; poketTypeTemp++) {
				for (int poketCardNumTemp = 0; poketCardNumTemp < poketNum.length; poketCardNumTemp++) {
					Card card = new Card();
					card.setCardNum(poketNum[poketCardNumTemp]);
					card.setCardType(poketType[poketTypeTemp]);
					if(poketCardNumTemp < 10)
					{
						card.setCardPoint(poketCardNumTemp+1);
					}
					else {
						card.setCardPoint(10);
					}
					
					cards.add(card);
				}
			}
		}
		
		

	}
	
	/**
	 * begin from 0, so get num max is cardnum-1
	 * @param cardNum
	 * @return
	 */
	public Card getCard(int cardNum) {
		Card card = cards.get(cardNum);
		cards.remove(cardNum);
		return card;
	}
	
	public int getCardTotalNum() {
		return cards.size();
	}
	
	public void shuffleCards()
	{
		ArrayList<Card> cardsTemp = new ArrayList<Card>();
		int totalCardsNum = ONEPOKETCARDNUM * numPoketSet;
		for (int i = 0; i < totalCardsNum; i++) {
			int numTemp = (int)(Math.random()*cards.size());
			Card cardTemp = cards.get(numTemp);
			cardsTemp.add(cardTemp);
			cards.remove(numTemp);
		}
		cards = cardsTemp;
	}
}
