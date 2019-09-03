package killSingle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int personNum = 1029;
		
		for (int i = 1; i <= personNum; i++) {
			Kill kill = new Kill(i);
			kill.killPerson();
		}
	}
}
