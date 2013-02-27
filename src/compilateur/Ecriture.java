package compilateur;
import java.io.*;

//quelques primitives d'ecriture a l'ecran ou dans un fichier
public class Ecriture {
	
	private static void erreur(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	public static OutputStream ouvrir(String nomFich) {
		//delivre un pointeur sur le fichier de nom nomFich (null si erreur)
		OutputStream f;
		try {
			f = new DataOutputStream(new FileOutputStream(nomFich));
		} catch(IOException e) {
			f = null;
		}
		return f;
	}

	public static void fermer(OutputStream f) {
		//fermeture d'un fichier                                          
		try {
			f.close();
		} catch(IOException e) {
			erreur(e);
		}
	}

	//ecriture d'un caractere
	public static void ecrireChar(OutputStream f,char c) {
		try {
			f.write(c);
		} catch(IOException e) {
			erreur(e);
		}
	}

	public static void ecrireChar(char c) {
		ecrireChar(System.out,c);
	}

	//ecriture d'une chaine, avec eventuel passage a la ligne 
	public static void ecrireString(OutputStream f, String s) {
		try {
			for(int i=0 ; i<s.length() ; i++) {
				f.write(s.charAt(i));
			}
		} catch(IOException e) {
			erreur(e);
		}
	}

	public static void ecrireString(String s) {
		ecrireString(System.out, s);
	}

	public static void ecrireStringln(OutputStream f, String s) {
		ecrireString(f, s+"\r\n");
	}

	public static void ecrireStringln(String s) {
		ecrireStringln(System.out, s);
	}

	//ecriture d'un entier avec formatage eventuel 
	public static void ecrireInt(OutputStream f, int x) {
		ecrireString(f, Integer.toString(x));
	}

	public static void ecrireInt(int x) {
		ecrireInt(System.out,x);
	}

	public static void ecrireInt(OutputStream f,int x,int longueur) {
		String s = Integer.toString(x);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			ecrireChar(f, ' ');
		}
		ecrireString(f, s);
	}

	public static void ecrireInt(int x, int longueur) {
		ecrireInt(System.out, x, longueur);
	}

	//ecriture d'un double avec formatage eventuel                   
	public static void ecrireDouble(OutputStream f, double d) {
		ecrireString(f, Double.toString(d));
	}

	public static void ecrireDouble(double d) {
		ecrireDouble(System.out, d);
	}

	public static void ecrireDouble(OutputStream f, double d, int longueur) {
		String s = Double.toString(d);
		int k = longueur - s.length();
		for(int i=0 ; i<k ; i++) {
			ecrireChar(f, ' ');
		}
		ecrireString(f, s);
	}

	public static void ecrireDouble(double d, int longueur) {
		ecrireDouble(System.out, d, longueur);
	}
}