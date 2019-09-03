package casinoTesting;

public class Casino {
public Casino() {
	// TODO Auto-generated constructor stub
	Shake shake = new Shake();
	int total = 10000;
	int betBegin = 100;
	int bet = 100;
	int betOnNum = 1;
	int platRands = 100;
	
	
	for (int i = 0; i < platRands; i++) {
		if (total > 0) {
			total = total - bet;
			shake.shaking();
			int returnMoney = shake.bet(bet, betOnNum);
			if (returnMoney == 0) {
				bet = bet * 2;
			} else {
				total = total + returnMoney;
				bet = betBegin;
			}
		}
		else {
			System.out.println("no more money in time " + i);
			break;
		}
		System.out.println("Rand: " +  i + "  Money on hand: " + total);

	}
}
}
