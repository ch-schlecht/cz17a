package cz17a.gamification.adminpanel.application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class for PasswordHashing
 * 
 * @author Michael
 * @version 1.2
 */
public class PasswordManager {

	/**
	 * Encode Byte[] to String
	 * 
	 * @param b
	 *            byte-Array
	 * @return String
	 * @since 1.1
	 */
	public static String byteToString(byte[] b) {
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(b);
	}

	/**
	 * Decode String to byte[]
	 * 
	 * @param s
	 *            String to Decode
	 * @return byte-Array
	 * @since 1.1
	 */
	public static byte[] StringToByte(String s) {
		Base64.Decoder dec = Base64.getDecoder();
		return dec.decode(s);
	}

	/**
	 * Generates random salt as byte-Array with length 20
	 * 
	 * @return random salt as byte[]
	 * @since 1.0
	 */
	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		return random.generateSeed(20);
	}

	public static String generateSaltAsString() {
		return byteToString(generateSalt());
	}

	public static String generateRandomCode() {
		SecureRandom random = new SecureRandom();
		return byteToString(random.generateSeed(128));
	}

	/**
	 * Hash-Function uses PBKDF2 , HmacSHA1
	 * 
	 * @param pw
	 *            plain-text Password to hash
	 * @param salt
	 *            Salt to Hash with
	 * @return hashed password
	 * @since 1.0
	 */
	public static String hash(String pw, byte[] salt) {
		KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt, 65536, 128); // create spec with lenght 128
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); // setting hash-algorithm
			Base64.Encoder enc = Base64.getEncoder(); // encoder
			byte[] hash = f.generateSecret(spec).getEncoded(); // encode hashed
			return enc.encodeToString(hash); // return hashed password as String
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Hash-Function with salt as String uses PBKDF2 , HmacSHA1
	 * 
	 * @param pw
	 *            plain-text Password to hash
	 * @param salt
	 *            Salt to Hash with as String (auto parse to byte[])
	 * @return hashed password
	 * @since 1.2
	 */
	public static String hash(String pw, String salt) {
		KeySpec spec = new PBEKeySpec(pw.toCharArray(), StringToByte(salt), 65536, 128); // create spec with lenght 128
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); // setting hash-algorithm
			Base64.Encoder enc = Base64.getEncoder(); // encoder
			byte[] hash = f.generateSecret(spec).getEncoded(); // encode hashed
			return enc.encodeToString(hash); // return hashed password as String
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Compares two passwords always in the same time.
	 * 
	 * @param pw2
	 * @return
	 */
	public static boolean validatePassword(String pw1, String pw2) {
		boolean valid = true;
		if (pw1.length() == pw2.length()) {
			for (int i = 0; i < pw1.length(); i++) {
				if (pw1.charAt(i) != pw2.charAt(i))
					valid = false;
			}
		} else {
			valid = false;
		}
		return valid;
	}
}
