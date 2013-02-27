package compilateur;
import java.io.*;

/**
 * Some methods to write on the screen or in a file.
 */
public class Ecriture {
	
	/**
	 * Display an error and quit.
	 * @param e The error.
	 */
	private static void erreur(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	/**
	 * Open a file and return the file stream.
	 * @param name The name of the file.
	 * @return The file stream or null if an error occurred.
	 */
	public static OutputStream ouvrir(String name) {
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
	public static void fermer(OutputStream f) {                                          
		try {
			f.close();
		} catch(IOException e) {
			erreur(e);
		}
	}

	/**
	 * Write a char in the file.
	 * @param f The file stream.
	 * @param c The char to write.
	 */
	public static void ecrireChar(OutputStream f, char c) {
		try {
			f.write(c);
		} catch(IOException e) {
			erreur(e);
		}
	}

	/**
	 * Write a char on the screen.
	 * @param c The char to write.
	 */
	public static void ecrireChar(char c) {
		ecrireChar(System.out, c);
	}

	/**
	 * Write a string in the file.
	 * @param f The file stream.
	 * @param s The string to write.
	 */
	public static void ecrireString(OutputStream f, String s) {
		try {
			for(int i=0 ; i<s.length() ; i++) {
				f.write(s.charAt(i));
			}
		} catch(IOException e) {
			erreur(e);
		}
	}

	/**
	 * Write a string on the screen.
	 * @param s The string to write.
	 */
	public static void ecrireString(String s) {
		ecrireString(System.out, s);
	}

	/**
	 * Write a string in the file and make a new line.
	 * @param f The file stream.
	 * @param s The string to write.
	 */
	public static void ecrireStringln(OutputStream f, String s) {
		ecrireString(f, s+"\r\n");
	}

	/**
	 * Write a string on the screen and make a new line.
	 * @param s The string to write.
	 */
	public static void ecrireStringln(String s) {
		ecrireStringln(System.out, s);
	}

	/**
	 * Write an integer in the file.
	 * @param f The file stream.
	 * @param x The integer to write.
	 */
	public static void ecrireInt(OutputStream f, int x) {
		ecrireString(f, Integer.toString(x));
	}

	/**
	 * Write an integer on the screen.
	 * @param x The integer to write.
	 */
	public static void ecrireInt(int x) {
		ecrireInt(System.out,x);
	}

	/**
	 * Write an integer in the file and add whitespaces to match longueur.
	 * @param f The file stream.
	 * @param x The integer to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void ecrireInt(OutputStream f, int x, int longueur) {
		String s = Integer.toString(x);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			ecrireChar(f, ' ');
		}
		ecrireString(f, s);
	}

	/**
	 * Write an integer on the screen and add whitespaces to match longueur.
	 * @param x The integer to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void ecrireInt(int x, int longueur) {
		ecrireInt(System.out, x, longueur);
	}

	/**
	 * Write a double in the file.
	 * @param f The file stream.
	 * @param d The double to write.
	 */
	public static void ecrireDouble(OutputStream f, double d) {
		ecrireString(f, Double.toString(d));
	}

	/**
	 * Write a double on the screen.
	 * @param d The double to write.
	 */
	public static void ecrireDouble(double d) {
		ecrireDouble(System.out, d);
	}

	/**
	 * Write a double in the file and add whitespaces to match longueur.
	 * @param f The file stream.
	 * @param d The double to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void ecrireDouble(OutputStream f, double d, int longueur) {
		String s = Double.toString(d);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			ecrireChar(f, ' ');
		}
		ecrireString(f, s);
	}

	/**
	 * Write a double on the screen and add whitespace to match longueur.
	 * @param d The double to write.
	 * @param longueur The total number of chars written in the file.
	 */
	public static void ecrireDouble(double d, int longueur) {
		ecrireDouble(System.out, d, longueur);
	}
}