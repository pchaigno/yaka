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
	public void newLine() {
		Yaka.yvm.aLaLigne();
	}
	
	/**
	 * Called to write an integer on the screen.
	 */
	public void writeInteger() {
		Yaka.yvm.ecrireEnt();
	}
	
	/**
	 * Called to write a string on the screen.
	 * @param str The string to write.
	 */
	public void writeString(String str) {
		str = str.substring(1, str.length()-1);
		Yaka.yvm.ecrireChaine(str);
	}
	
	/**
	 * Called to read a value from the keyboard.
	 * @param ident
	 */
	public void read(String ident) {
		if(Yaka.tabIdent.containsIdent(ident)) {
			if (Yaka.tabIdent.getIdent(ident).isVar()) {
				Yaka.yvm.lire(((IdVar)Yaka.tabIdent.getIdent(ident)).getOffset());
			}
		} else {
			System.err.println("InputOutput: "+ident+" doesn't exist.");
		}
	}
}