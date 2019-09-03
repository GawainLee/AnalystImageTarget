package countryGame;

public class CountrySelfObject {
	
	private CountryObjectName name;
	
	private Double selfKnowData;
	
	private Double showOtherData;
	
	// y = x ^ n
	// 0 <= x <= 1
	// y is deviation ‰Y®i²v
	// x is input research §ë¤J¦¨¥»
	// n is curvature ¦±²v
	private Double yDeviation, xInputResearch, nCurvature;
	
	public void develop() {
		yDeviation = Math.pow(xInputResearch, nCurvature);
		selfKnowData = (1 + yDeviation) * selfKnowData;
	}
	
	public CountryObjectName getName() {
		return name;
	}

	public void setName(CountryObjectName name) {
		this.name = name;
	}

	public Double getSelfKnowData() {
		return selfKnowData;
	}

	public void setSelfKnowData(Double selfKnowData) {
		this.selfKnowData = selfKnowData;
	}

	public Double getShowOtherData() {
		return showOtherData;
	}

	public void setShowOtherData(Double showOtherData) {
		this.showOtherData = showOtherData;
	}
	
	

}
