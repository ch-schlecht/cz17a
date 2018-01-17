package pw;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PasswordManager manager = new PasswordManager();
		byte[] salt = manager.generateSalt();
		System.out.println(manager.byteToString(salt));
		System.out.println(manager.hash("admin1234", salt));
	}

}
