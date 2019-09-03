package killSingle;

public class Person {

	int beginNum, placeNum;
	
	boolean isSingle = true;
	
	public Person(int beginNum) {
		this.beginNum = beginNum;
		this.placeNum = beginNum;
		checkIsSingle();
	}
	
	public void checkIsSingle(){
		if(this.placeNum%2 == 0){
			this.isSingle = false;
		}else {
			this.isSingle = true;
		}
	}

	public int getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}

	public int getPlaceNum() {
		return placeNum;
	}

	public void setPlaceNum(int placeNum) {
		this.placeNum = placeNum;
	}

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

}
