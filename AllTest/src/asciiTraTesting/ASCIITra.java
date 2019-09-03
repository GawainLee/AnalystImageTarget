package asciiTraTesting;

public class ASCIITra {

	public ASCIITra()
	{
//		  System.out.println("\n***** Convert ASCII to Hex *****");
//		  String str = "I Love Java!";  
//		  System.out.println("Original input : " + str);
//		  
//		  String hex = convertStringToHex(str);
//		  
//		  System.out.println("Hex : " + hex);
//		  
//		  System.out.println("\n***** Convert Hex to ASCII *****");
//		  System.out.println("Hex : " + hex);
//		  System.out.println("ASCII : " + convertHexToString(hex));
//		  
//		  System.out.println("\n***** test test Hex to ASCII *****");
//		  String tempString = "31783a2d37382e34323020743a3635340d0a3179";
//		  System.out.println("ASCII : " + convertHexToString(tempString));
//		  
//		  System.out.println("\n***** test test Hex to ASCII *****");
//		  tempString = "39270000702b0000";
//		  System.out.println("ASCII : " + convertHexToString(tempString));
	}
	
	
	 public String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
		  
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
			  
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
			  
		      temp.append(decimal);
		  }
		  System.out.println("Decimal : " + temp.toString());
		  
		  return sb.toString();
	  }

	
	  public String convertStringToHex(String str){
		  
		  char[] chars = str.toCharArray();
		  
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }
		  
		  return hex.toString();
	  }
}
