package compilateur;

/**
 * Compute the inputs and outputs instructions.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class InputOutput {

	/**
	 * Called after a read operation.
	 */
	public static void newLine() {
		Yaka.yvm.aLaLigne();
	}
	
	/**
	 * Called to write the result of an expression on the screen.
	 * Can be a boolean or an integer.
	 */
	public static void writeResult() {
		Type type = Yaka.expression.getType();
		if(type==Type.BOOL) {
			writeBoolean();
		} else if(type==Type.INT) {
			writeInteger();
		} else {
			Yaka.errors.addError("The expression can't be display because it wasn't compute successfully.");
		}
	}
	
	/**
	 * Called to write an integer on the screen.
	 */
	private static void writeInteger() {
		Yaka.yvm.ecrireEnt();
	}
	
	/**
	 * Called to write a boolean on the screen.
	 */
	private static void writeBoolean() {
		Yaka.yvm.ecrireBool();
	}
	
	/**
	 * Called to write a string on the screen.
	 * @param str The string to write.
	 */
	public static void writeString(String str) {
		str = str.substring(1, str.length()-1);
		Yaka.yvm.ecrireChaine(str);
	}
	
	/**
	 * Called to read a value from the keyboard.
	 * @param ident The ident where to store the value read.
	 */
	public static void read(String ident) {
		if(Yaka.tabIdent.containsIdent(ident)) {
			if(Yaka.tabIdent.getIdent(ident).isVar()) {
				Yaka.yvm.lire(((IdVar)Yaka.tabIdent.getIdent(ident)).getOffset());
			}
		} else {
			Yaka.errors.addError("Ident '"+ident+"' doesn't exist.");
		}
	}
}