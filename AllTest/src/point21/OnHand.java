package point21;

import java.util.ArrayList;

public class OnHand {

	private ArrayList<Card> cardsOnHand;
	private boolean hasAce = false;
	private int onHandScoreHasOne = 0;
	private int onHandScoreNotHasOne = 0;
	private boolean needBid = true;
	private ArrayList<Integer>onHandScore;
	
	public OnHand() {
		// TODO Auto-generated constructor stub
		cardsOnHand = new ArrayList<Card>();
	}
	
	public void getCard(Card card) {
		cardsOnHand.add(card);
		onHandScoreNotHasOne += card.getCardPoint();
		if (hasAce) {
			onHandScoreHasOne +=card.getCardPoint();
		}
		if (card.getCardPoint()==1) {
			onHandScoreHasOne = onHandScoreNotHasOne + 10;
			this.hasAce = true;
		}
	}
	
	public Card takeAwayCard() {
		if (cardsOnHand.size()>0) {
			Card cardTemp = cardsOnHand.get(0);
			onHandScoreNotHasOne -= cardTemp.getCardPoint();
			if (cardTemp.getCardPoint()==1) {
				onHandScoreHasOne = 0;
				this.hasAce = false;
			}
			cardsOnHand.remove(0);
			return cardTemp;
		}
		System.out.println("No card on hand");
		return null;		
	}

	public boolean isHasAce() {
		return hasAce;
	}

	public int getOnHandScoreHasOne() {
		return onHandScoreHasOne;
	}

	public int getOnHandScoreNotHasOne() {
		return onHandScoreNotHasOne;
	}

	public boolean isNeedBid() {
		return needBid;
	}

	public void setNeedBid(boolean needBid) {
		this.needBid = needBid;
	}
	
	/**
	 * A can be 1 or 11
	 * on hand is A and X, then can be (1 + X) and (11 + X)
	 * on hand is A and A and X, then can be (1 + 1 + X) and (1 + 11 + X) and (11 + 11 + X)
	 * on hand is n * A and X, then can be (n + X) and (1 * 11 + (n-1) + X) ... (n*11 + X) 
	 * @return
	 */
	public ArrayList<Integer> getOnHandScore() {
		this.onHandScore = new ArrayList<Integer>();
		int tempNumOfOneCards = 0;
		int tempNowScore = 0;
		for (int cardsNum = 0; cardsNum < cardsOnHand.size(); cardsNum++) {
			if (cardsOnHand.get(cardsNum).getCardPoint()==1) {
				tempNumOfOneCards++;
			}
			else {
				tempNowScore += cardsOnHand.get(cardsNum).getCardPoint();
			}
		}
		for (int i = 0; i <= tempNumOfOneCards; i++) {
			int score = i * 10 + tempNumOfOneCards + tempNowScore;
			this.onHandScore.add(score);
		}
		
		return this.onHandScore;
		
	}
}
