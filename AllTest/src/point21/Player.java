package point21;

import java.util.ArrayList;

public class Player {
	
	private ArrayList<OnHand> onHands;
	
	public Player() {
		onHands = new ArrayList<OnHand>();
	}

	public void addOneHand() {
		OnHand onHand = new OnHand();
		onHands.add(onHand);
	}
	
	public OnHand getOneOnHandCards(int onHandNum)
	{
		return onHands.get(onHandNum);
	}
	
	public void getCardsForOneHand(int onHandNum, Card cardTemp) {
		onHands.get(onHandNum).getCard(cardTemp);
	}
	

//	public int[] getOneOnHandScore(int onHandNum) {
//		OnHand onHand = onHands.get(onHandNum);
//		int[] onHandSroce = {onHand.getOnHandScoreNotHasOne(),onHand.getOnHandScoreHasOne()};
//		return onHandSroce;
//	}
	
	public void separateCards() {
		OnHand onHand = new OnHand();
		Card cardTemp = onHands.get(0).takeAwayCard();
		onHand.getCard(cardTemp);
		onHands.add(onHand);
	}
	
	public int getPlayerOnHandNum() {
		return onHands.size();
	}
	
	public void checkOnHandScore(){
		for (int i = 0; i < onHands.size(); i++) {
			OnHand onHand = onHands.get(i);
			Rule rule = new Rule(onHand);
			rule.checkRule();
		}
	}
}
