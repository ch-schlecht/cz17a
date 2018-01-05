package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class for PasswordHashing
 * @author Michael
 * @version 1.1
 */
public class PasswordManager {

	/**
	 * Encode Byte[] to String
	 * @param b byte-Array
	 * @return String
	 * @since 1.1
	 */
	public static String byteToString(byte[] b) {
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(b);
	}
	
	/**
	 * Decode String to byte[]
	 * @param s String to Decode
	 * @return byte-Array
	 * @since 1.1
	 */
	public static byte[] StringToByte(String s) {
		Base64.Decoder dec = Base64.getDecoder();
		return dec.decode(s);
	}
	
	/**
	 * Generates random salt as byte-Array
	 * with length 20
	 * @return random salt as byte[] 
	 * @since 1.0
	 */
	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		return random.generateSeed(20);
	}
	
	/**
	 * Hash-Function
	 * uses PBKDF2 , HmacSHA1
	 * @param pw 	plain-text Password to hash
	 * @param salt 	Salt to Hash with
	 * @return hashed password
	 * @since 1.0
	 */
	public static String hash(String pw, byte[] salt) {
		KeySpec spec  = new PBEKeySpec(pw.toCharArray(), salt, 65536, 128); //create spec with lenght 128
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //setting hash-algorithm
			Base64.Encoder enc = Base64.getEncoder(); //encoder 
			byte [] hash = f.generateSecret(spec).getEncoded();  //encode hashed 
			return enc.encodeToString(hash); //return hashed pawssword as String
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
