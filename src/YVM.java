import java.io.OutputStream;

/**
 * Generate the YVM code for each YVM function.
 */
public class YVM {
	
	private String program;
	
	public YVM() {
		program = "";
	}
	
	protected String addLine(String str) {
		program += str;
		return str;
	}
	
	public String getProgram() {
		return this.program;
	}
	
	public void generateFile(String name) {
		OutputStream f = Ecriture.ouvrir(name);
		Ecriture.ecrireString(f, program);
		Ecriture.fermer(f);
		System.out.println("---- Code YVM genere dans '" + name + "' ---- \n" + program);
	}
	
	/**
	 * Generate program header
	 * @return The YVM code.
	 */
	String entete() {
		return addLine("entete\n");
	}
	
	/**
	 * Generate the code for an iconst instruction.
	 * @param obj The integer.
	 * @return The YVM code.
	 */
	String iconst(int obj) {
		return addLine("iconst "+obj+"\n");
	}
		
	/**
	 * Generate the code for an iload instruction.
	 * @param offset The offset.
	 * @return The YVM code.
	 */
	String iload(int offset) {
		return addLine("iload "+offset+"\n");
	}
	
	/**
	 * Generate the code for an iadd instruction.
	 * @return The YVM code.
	 */
	String iadd() {
		return addLine("iadd\n");
	}

	/**
	 * Generate the code for an imul instruction.
	 * @return The YVM code.
	 */
	String imul() {
		return addLine("imul\n");
	}

	/**
	 * Generate the code for an isub instruction.
	 * @return The YVM code.
	 */
	String isub() {
		return addLine("isub\n");
	}

	/**
	 * Generate the code for an iand instruction.
	 * @return The YVM code.
	 */
	String iand() {
		return addLine("iand\n");
	}

	/**
	 * Generate the code for an ior instruction.
	 * @return The YVM code.
	 */
	String ior() {
		return addLine("ior\n");
	}

	/**
	 * Generate the code for an idiv instruction.
	 * @return The YVM code.
	 */
	String idiv() {
		return addLine("idiv\n");
	}

	/**
	 * Generate the code for an iinf instruction.
	 * @return The YVM code.
	 */
	String iinf() {
		return addLine("iinf\n");
	}

	/**
	 * Generate the code for an iinfegal instruction.
	 * @return The YVM code.
	 */
	String iinfegal() {
		return addLine("iinfegal\n");
	}

	/**
	 * Generate the code for an isupegal instruction.
	 * @return The YVM code.
	 */
	String isupegal() {
		return addLine("isupegal\n");
	}

	/**
	 * Generate the code for an isup instruction.
	 * @return The YVM code.
	 */
	String isup() {
		return addLine("isup\n");
	}

	/**
	 * Generate the code for an iegal instruction.
	 * @return The YVM code.
	 */
	String iegal() {
		return addLine("iegal\n");
	}

	/**
	 * Generate the code for an idiff instruction.
	 * @return The YVM code.
	 */
	String idiff() {
		return addLine("idiff\n");
	}
	
	/**
	 * Generate the code for an istore instruction.
	 * @return The YVM code.
	 */
	String istore(int offset) {
		return addLine("istore "+offset+"\n");
	}
	
	/**
	 * Generate the code for an aLaLigne instruction.
	 * @return The YVM code.
	 */
	String aLaLigne() {
		return addLine("aLaLigne\n");
	}
	
	/**
	 * Generate the code for an lire instruction.
	 * @return The YVM code.
	 */
	String lire(int offset) {
		return addLine("lireEnt "+offset+"\n"); 
	}
	
	/**
	 * Generate the code for an ecrireChaine instruction.
	 * @return The YVM code.
	 */
	String ecrireChaine(String s) {
		return addLine("ecrireChaine "+s+"\n"); 
	}
	
	String ecrireEnt() {
		return addLine("ecrireEnt\n"); 
	}
	
}
