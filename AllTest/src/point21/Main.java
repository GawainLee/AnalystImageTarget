package point21;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Banker banker = new Banker();
		banker.addPlayer();
		banker.addPlayer();
		banker.addPlayer();
		banker.addPlayer();
		
		banker.deal();
		banker.deal();
		banker.deal();
		banker.deal();
	}

}
