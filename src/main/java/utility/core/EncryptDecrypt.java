package utility.core;

import org.apache.commons.codec.binary.Base64;

public class EncryptDecrypt {

	/**Encrypts a given String and returns an encoded version of it. for example you enter encryptPassword("MyPassword1233") as the String to be encoded this method
	 * will return the encrypted string "TXlQYXNzd29yZDEyMw==".
	 * **/
	public static String encryptPassword(String password) {
		byte[] encodePwdBytes = Base64.encodeBase64(password.getBytes());
		return new String(encodePwdBytes);
	}
	/**decrypts a given String and returns an original version of it. for example you enter "TXlQYXNzd29yZDEyMw==" then decrypt and it will return ("MyPassword1233")
	 * **/
	public static String decryptPassword(String encryptedPassword) {
		byte[] decodedPwdBytes = Base64.decodeBase64(encryptedPassword.getBytes());
		return new String(decodedPwdBytes);
	}
	
}
