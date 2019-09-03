package securityTesting;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	//output code type
	public static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	//encryption types and order
	//encryption all will loop from first, so change order will change encryption result
	public static final String[] hashTypes = new String[] { 
		"SHA-512", "SHA-384", "SHA-256", "MD2", "MD5", "SHA1" };

	/**
	 * encryption in all types
	 * @param content
	 * @return
	 */
	public static String run(String content)
	{
		String result = "";
		for(int i = 0; i < hashTypes.length; i++)
        {
			result = encryption(content,hashTypes[i]);
        }
		return result;
	}
	
	/**
	 * encryption content in type
	 * @param content
	 * @param type
	 * @return
	 */
	public static String encryption(String content, String type) {
		String result = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(type);
			byte[] byteArray = content.getBytes();
			messageDigest.update(byteArray, 0, byteArray.length);
			result = toHexString(messageDigest.digest());
			System.out.println(messageDigest.getAlgorithm() + " == " + result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * change byte to String
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
