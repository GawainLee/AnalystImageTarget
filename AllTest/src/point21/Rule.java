package point21;

import java.util.ArrayList;

public class Rule {
	
	private OnHand onHand;
	
	public Rule(OnHand onHand){
		this.onHand = onHand;
	}
	
	public void checkRule() {
		ArrayList<Integer> onHandScore =  this.onHand.getOnHandScore();
		for (int i = 0; i < onHandScore.size(); i++) {
			int tempScore = onHandScore.get(i);
			if (tempScore>21) {
				this.onHand.setNeedBid(false);
				System.out.println("Now score is  " + tempScore + "Boom");
			}
			else if(tempScore==21) {
				this.onHand.setNeedBid(false);
				System.out.println("Now score is  " + tempScore + "Win");
			}
			else {
				System.out.println("Now score is  " + tempScore);
			}
		}
	}

}
