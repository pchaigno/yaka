package compilateur;
import java.io.*;

/**
 * Some methods to write on the screen or in a file.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Writer {
	
	/**
	 * Display an error and quit.
	 * @param e The error.
	 */
	private static void error(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	/**
	 * Open a file and return the file stream.
	 * @param name The name of the file.
	 * @return The file stream or null if an error occurred.
	 */
	public static OutputStream open(String name) {
		OutputStream f;
		try {
			f = new DataOutputStream(new FileOutputStream(name));
		} catch(IOException e) {
			f = null;
		}
		return f;
	}

	/**
	 * Close a file.
	 * @param f The file stream.
	 */
	public static void close(OutputStream f) {                                          
		try {
			f.close();
		} catch(IOException e) {
			error(e);
		}
	}

	/**
	 * Write a char in the file.
	 * @param f The file stream.
	 * @param c The char to write.
	 */
	public static void writeChar(OutputStream f, char c) {
		try {
			f.write(c);
		} catch(IOException e) {
			error(e);
		}
	}

	/**
	 * Write a char on the screen.
	 * @param c The char to write.
	 */
	public static void writeChar(char c) {
		writeChar(System.out, c);
	}

	/**
	 * Write a string in the file.
	 * @param f The file stream.
	 * @param s The string to write.
	 */
	public static void writeString(OutputStream f, String s) {
		try {
			for(int i=0 ; i<s.length() ; i++) {
				f.write(s.charAt(i));
			}
		} catch(IOException e) {
			error(e);
		}
	}

	/**
	 * Write a string on the screen.
	 * @param s The string to write.
	 */
	public static void writeString(String s) {
		writeString(System.out, s);
	}

	/**
	 * Write a string in the file and make a new line.
	 * @param f The file stream.
	 * @param s The string to write.
	 */
	public static void writeStringln(OutputStream f, String s) {
		writeString(f, s+"\r\n");
	}

	/**
	 * Write a string on the screen and make a new line.
	 * @param s The string to write.
	 */
	public static void writeStringln(String s) {
		writeStringln(System.out, s);
	}

	/**
	 * Write an integer in the file.
	 * @param f The file stream.
	 * @param x The integer to write.
	 */
	public static void writeInt(OutputStream f, int x) {
		writeString(f, Integer.toString(x));
	}

	/**
	 * Write an integer on the screen.
	 * @param x The integer to write.
	 */
	public static void writeInt(int x) {
		writeInt(System.out,x);
	}

	/**
	 * Write an integer in the file and add whitespaces to match longueur.
	 * @param f The file stream.
	 * @param x The integer to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void writeInt(OutputStream f, int x, int longueur) {
		String s = Integer.toString(x);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			writeChar(f, ' ');
		}
		writeString(f, s);
	}

	/**
	 * Write an integer on the screen and add whitespaces to match longueur.
	 * @param x The integer to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void writeInt(int x, int longueur) {
		writeInt(System.out, x, longueur);
	}

	/**
	 * Write a double in the file.
	 * @param f The file stream.
	 * @param d The double to write.
	 */
	public static void writeDouble(OutputStream f, double d) {
		writeString(f, Double.toString(d));
	}

	/**
	 * Write a double on the screen.
	 * @param d The double to write.
	 */
	public static void writeDouble(double d) {
		writeDouble(System.out, d);
	}

	/**
	 * Write a double in the file and add whitespaces to match longueur.
	 * @param f The file stream.
	 * @param d The double to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void writeDouble(OutputStream f, double d, int longueur) {
		String s = Double.toString(d);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			writeChar(f, ' ');
		}
		writeString(f, s);
	}

	/**
	 * Write a double on the screen and add whitespace to match longueur.
	 * @param d The double to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void writeDouble(double d, int longueur) {
		writeDouble(System.out, d, longueur);
	}
}