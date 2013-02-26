
/**
 * Generate the YVM code for each YVM function.
 */
public class YVM {
	
	/**
	 * Generate the code for an iconst instruction.
	 * @param obj The integer.
	 * @return The YVM code.
	 */
	String iconst(int obj) {
		return "iconst "+obj+"\n";
	}
		
	/**
	 * Generate the code for an iload instruction.
	 * @param offset The offset.
	 * @return The YVM code.
	 */
	String iload(int offset) {
		return "iload "+offset+"\n";
	}
	
	/**
	 * Generate the code for an iadd instruction.
	 * @return The YVM code.
	 */
	String iadd() {
		return "iadd\n";
	}

	/**
	 * Generate the code for an imul instruction.
	 * @return The YVM code.
	 */
	String imul() {
		return "imul\n";
	}

	/**
	 * Generate the code for an isub instruction.
	 * @return The YVM code.
	 */
	String isub() {
		return "isub\n";
	}

	/**
	 * Generate the code for an iand instruction.
	 * @return The YVM code.
	 */
	String iand() {
		return "iand\n";
	}

	/**
	 * Generate the code for an ior instruction.
	 * @return The YVM code.
	 */
	String ior() {
		return "ior\n";
	}

	/**
	 * Generate the code for an idiv instruction.
	 * @return The YVM code.
	 */
	String idiv() {
		return "idiv\n";
	}

	/**
	 * Generate the code for an iinf instruction.
	 * @return The YVM code.
	 */
	String iinf() {
		return "iinf\n";
	}

	/**
	 * Generate the code for an iinfegal instruction.
	 * @return The YVM code.
	 */
	String iinfegal() {
		return "iinfegal\n";
	}

	/**
	 * Generate the code for an isupegal instruction.
	 * @return The YVM code.
	 */
	String isupegal() {
		return "isupegal\n";
	}

	/**
	 * Generate the code for an isup instruction.
	 * @return The YVM code.
	 */
	String isup() {
		return "isup\n";
	}

	/**
	 * Generate the code for an iegal instruction.
	 * @return The YVM code.
	 */
	String iegal() {
		return "iegal\n";
	}

	/**
	 * Generate the code for an idiff instruction.
	 * @return The YVM code.
	 */
	String idiff() {
		return "idiff\n";
	}
	
	/**
	 * Generate the code for an istore instruction.
	 * @return The YVM code.
	 */
	String istore(int offset) {
		return "istore "+offset+"\n";
	}
	
	/**
	 * Generate the code for an aLaLigne instruction.
	 * @return The YVM code.
	 */
	String aLaLigne() {
		return "aLaLigne";
	}
	
	/**
	 * Generate the code for an lire instruction.
	 * @return The YVM code.
	 */
	String lire(int offset) {
		return "lireEnt "+offset+"\n"; 
	}
	
	/**
	 * Generate the code for an ecrireChaine instruction.
	 * @return The YVM code.
	 */
	String ecrireChaine(String s) {
		return "ecrireChaine \""+s+"\"\n"; 
	}
	
}
