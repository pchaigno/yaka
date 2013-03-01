package compilateur;

import java.io.OutputStream;
import java.util.Stack;

/**
 * Generate the YVM code for each YVM function.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class YVM {
	protected int nbIterations;
	protected Stack<Integer> iterations;
	protected int nbConditions;
	protected Stack<Integer> conditions;
	protected String program;

	/**
	 * Constructor
	 */
	public YVM() {
		this.nbIterations = 0;
		this.iterations = new Stack<Integer>();
		this.nbConditions = 0;
		this.conditions = new Stack<Integer>();
		this.program = "";
	}
	
	/**
	 * Add some lines to the program and return this line.
	 * @param str The lines to add.
	 * @return The lines.
	 */
	protected String addLine(String str) {
		this.program += str;
		return str;
	}
	
	/**
	 * @return The program generated.
	 */
	public String getProgram() {
		return this.program;
	}
	
	/**
	 * Generate the file with the result program.
	 * @param name The name of the file.
	 */
	public void generateFile(String name) {
		OutputStream f = Ecriture.ouvrir(name);
		Ecriture.ecrireString(f, this.program);
		Ecriture.fermer(f);
		System.out.println("---- Code YVM genere dans '" + name + "' ---- \n" + program);
	}
	
	/**
	 * Generate the program's header.
	 * @return The YVM code.
	 */
	String header() {
		return this.addLine("entete\n");
	}
	
	/**
	 * Generate the code to book memory space.
	 * @param nbVariables The number of variables.
	 * @return The YVM code.
	 */
	String bookMemory(int nbVariables) {
		return this.addLine("ouvrePrinc "+nbVariables*2+"\n");
	}
	
	/**
	 * Generate the code for an iconst instruction with an integer.
	 * @param obj The integer.
	 * @return The YVM code.
	 */
	String iconst(int obj) {
		return this.addLine("iconst "+obj+"\n");
	}
	
	/**
	 * Generate the code for an iload instruction.
	 * @param offset The offset.
	 * @return The YVM code.
	 */
	String iload(int offset) {
		return this.addLine("iload "+offset+"\n");
	}
	
	/**
	 * Generate the code for an iadd instruction.
	 * @return The YVM code.
	 */
	String iadd() {
		return this.addLine("iadd\n");
	}

	/**
	 * Generate the code for an imul instruction.
	 * @return The YVM code.
	 */
	String imul() {
		return this.addLine("imul\n");
	}

	/**
	 * Generate the code for an isub instruction.
	 * @return The YVM code.
	 */
	String isub() {
		return this.addLine("isub\n");
	}

	/**
	 * Generate the code for an iand instruction.
	 * @return The YVM code.
	 */
	String iand() {
		return this.addLine("iand\n");
	}

	/**
	 * Generate the code for an ior instruction.
	 * @return The YVM code.
	 */
	String ior() {
		return this.addLine("ior\n");
	}

	/**
	 * Generate the code for an idiv instruction.
	 * @return The YVM code.
	 */
	String idiv() {
		return this.addLine("idiv\n");
	}

	/**
	 * Generate the code for an iinf instruction.
	 * @return The YVM code.
	 */
	String iinf() {
		return this.addLine("iinf\n");
	}

	/**
	 * Generate the code for an iinfegal instruction.
	 * @return The YVM code.
	 */
	String iinfegal() {
		return this.addLine("iinfegal\n");
	}

	/**
	 * Generate the code for an isupegal instruction.
	 * @return The YVM code.
	 */
	String isupegal() {
		return this.addLine("isupegal\n");
	}

	/**
	 * Generate the code for an isup instruction.
	 * @return The YVM code.
	 */
	String isup() {
		return this.addLine("isup\n");
	}

	/**
	 * Generate the code for an iegal instruction.
	 * @return The YVM code.
	 */
	String iegal() {
		return this.addLine("iegal\n");
	}

	/**
	 * Generate the code for an idiff instruction.
	 * @return The YVM code.
	 */
	String idiff() {
		return this.addLine("idiff\n");
	}

	/**
	 * Generate the code for an inot instruction.
	 * @return The YVM code.
	 */
	String inot() {
		return this.addLine("inot\n");
	}

	/**
	 * Generate the code for an ineg instruction.
	 * @return The YVM code.
	 */
	String ineg() {
		return this.addLine("ineg\n");
	}
	
	/**
	 * Generate the code for an istore instruction.
	 * @return The YVM code.
	 */
	String istore(int offset) {
		return this.addLine("istore "+offset+"\n");
	}

	/**
	* Generate the code for a aLaLigne instruction.
	* @return The YVM code.
	*/
	String aLaLigne() {
		return this.addLine("aLaLigne\n");
	}
	
	/**
	* Generate the code for a lire instruction.
	* @return The YVM code.
	*/
	String lire(int offset) {
		return this.addLine("lireEnt "+offset+"\n"); 
	}
	
	/**
	* Generate the code for an ecrireChaine instruction.
	* @return The YVM code.
	*/
	String ecrireChaine(String s) {
		return this.addLine("ecrireChaine \""+s+"\"\n");
	}
	
	/**
	 * Generate the code for an ecrireEnt instruction.
	 * @return The YVM code.
	 */
	String ecrireEnt() {
		return this.addLine("ecrireEnt\n"); 
	}

	/**
	 * Generate the code for a label at the beginning of the loop.
	 * @return The YVM code.
	 */
	String labelFaire() {
		this.nbIterations++;
		this.iterations.push(this.nbIterations);
		return addLine("FAIRE"+this.iterations.peek()+" :\n");
	}

	/**
	 * Generate the code to go to the end of the loop if the expression is false.
	 * @return The YVM code.
	 */
	String ifFauxFait() {
		return addLine("iffaux FAIT"+this.iterations.peek()+"\n");
	}

	/**
	 * Generate the code to go to the beginning of the loop.
	 * @return The YVM code.
	 */
	String gotoFaire() {
		return this.addLine("goto FAIRE"+this.iterations.peek()+"\n");
	}

	/**
	 * Generate the code for a label at the end of the loop.
	 * @return The YVM code.
	 */
	String labelFait() {
		return this.addLine("FAIT"+this.iterations.pop()+" :\n");
	}
	
	/**
	 * Generate the code to go to the second part of the condition bloc.
	 * @return The YVM code.
	 */
	String ifFauxSinon() {
		this.nbConditions++;
		this.conditions.push(this.nbConditions);
		return this.addLine("iffaux SINON"+this.conditions.peek()+"\n");
	}
	
	/**
	 * Generate the code to go to the end of the condition bloc.
	 * @return The YVM code.
	 */
	String gotoFsi() {
		return this.addLine("goto FSI"+this.conditions.peek()+"\n");
	}
	
	/**
	 * Generate the code for a label for the second part of the condition bloc.
	 * @return The YVM code.
	 */
	String labelSinon() {
		return this.addLine("SINON"+this.conditions.peek()+" :\n");
	}
	
	/**
	 * Generate the code for a label at the end of the condition bloc.
	 * @return The YVM code.
	 */
	String labelFsi() {
		return this.addLine("FSI"+this.conditions.pop()+" :\n");
	}
	
	/**
	 * Generate the program's footer.
	 * @return The YVM code.
	 */
	String footer() {
		return this.addLine("queue\n");
	}
}