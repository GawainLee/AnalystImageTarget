package asciiTraTesting;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ASCIITra asciiTra = new ASCIITra();
		
		  System.out.println("\n***** Convert ASCII to Hex *****");
		  String str = "I Love Java!";  
		  System.out.println("Original input : " + str);
		  
		  String hex = asciiTra.convertStringToHex(str);
		  
		  System.out.println("Hex : " + hex);
		  
		  System.out.println("\n***** Convert Hex to ASCII *****");
		  System.out.println("Hex : " + hex);
		  System.out.println("ASCII : " + asciiTra.convertStringToHex(hex));
		  
		  System.out.println("\n***** test test Hex to ASCII *****");
		  String tempString = "31783a2d37382e34323020743a3635340d0a3179";
		  System.out.println("ASCII : " + asciiTra.convertStringToHex(tempString));
		  
		  System.out.println("\n***** test test Hex to ASCII *****");
		  tempString = "39270000702b0000";
		  System.out.println("ASCII : " + asciiTra.convertStringToHex(tempString));
	}

}
