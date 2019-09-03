package casinoTesting;

import java.util.ArrayList;
import java.util.Random;

public class Shake {

	public Dice dice1, dice2, dice3, dice4, dice5, dice6;

	public ArrayList<Dice> diceList;

	public Shake() {
		dice1 = new Dice();
		dice2 = new Dice();
		dice3 = new Dice();
//		dice4 = new Dice();
//		dice5 = new Dice();
//		dice6 = new Dice();

		diceList = new ArrayList<Dice>();
		diceList.add(dice1);
		diceList.add(dice2);
		diceList.add(dice3);
//		diceList.add(dice4);
//		diceList.add(dice5);
//		diceList.add(dice6);
	}

	public void shaking() {
		Random rand = new Random();
		for (int i = 0; i < diceList.size(); i++) {
			int tempNum = rand.nextInt(6) + 1;
//			System.out.println(tempNum);
			diceList.get(i).num = tempNum;
		}
//		System.out.println("One time");
	}

	public int checkNum(int tempNum) {
		int numOfTempNum = 0;
		for (int i = 0; i < diceList.size(); i++) {
			if (diceList.get(i).num == tempNum) {
				numOfTempNum++;
			}
		}
//		System.out.println("have: " + numOfTempNum);
		return numOfTempNum;
	}
	
	public int bet(int money, int checkNum) {
		int returnMoney = 0;
		int numOfDice = checkNum(checkNum);
		if (numOfDice == 0 || numOfDice == 6) {
			return returnMoney = 0;
		}
		else if(numOfDice ==1){
			returnMoney = money *2;//pei lv 1:1
		}
		else if(numOfDice ==2){
			returnMoney = money *3;//pei lv 1:2
		}
		else if(numOfDice >=3){
			returnMoney = money *4;//pei lv 1:3
		}
		return returnMoney;
	}

}
