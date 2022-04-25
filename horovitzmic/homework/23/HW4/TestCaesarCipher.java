
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

public class TestCaesarCipher {

	public static void main(String[] args) {
//		String text = "This is my forth homework in object oriented course.";
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		int shiftEncoding = 	Integer.parseInt(sc.nextLine());
		sc.close();
		try {
			StringWriter fw = new StringWriter();
			CaesarEncryptorWriter cw = new CaesarEncryptorWriter(fw, shiftEncoding);
			cw.write(text.toCharArray(), 0, text.length());
			String buffer = fw.getBuffer().toString();
			cw.close();
			System.out.println("Testing Caesar encryptor and decryptor with shift "+shiftEncoding+".");
			System.out.println();
			System.out.println("Writing the following text (of size " + text.length() + "):");
			System.out.println("\"" + text + "\"");
			System.out.println("which encoded to:");
			System.out.println("\"" + buffer + "\"");
			System.out.println();

			CaesarDecryptorReader cr = new CaesarDecryptorReader(new StringReader(buffer), shiftEncoding);
			char[] buff = new char[text.length()];
			int len = cr.read(buff);
			cr.close();
			System.out.println("Reading the following encoded text (of size " + len + "):");
			System.out.println("\"" + buffer + "\"");
			System.out.println("and decoding it to:");
			System.out.println("\"" + new String(buff) + "\"");

		} catch (IOException ioe) {
			System.err.println("IO exception");
		}
	}
}
