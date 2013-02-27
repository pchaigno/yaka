import java.util.Stack;


/**
 * Generate the YVM code for each YVM function.
 */
public class YVM {
	protected int nbIterations;
	protected Stack<Integer> iterations;

	public YVM() {
		this.nbIterations = 0;
		this.iterations = new Stack<Integer>();
	}
	
	/**
	 * Generate the code for an iconst instruction with an integer.
	 * @param obj The integer.
	 * @return The YVM code.
	 */
	String iconstInt(int obj) {
		return "iconst "+obj+"\n";
	}
	
	/**
	 * Generate the code for an iconst instruction with a boolean.
	 * @param obj The boolean
	 * @return The YVM code.
	 */
	String iconstBool(boolean obj) {
		if(obj) {
			return "iconst -1\n";
		}
		return "iconst 0\n";
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
	 * Generate the code for a label at the beginning of the loop.
	 * @return The YVM code.
	 */
	String labelFaire() {
		this.nbIterations++;
		this.iterations.push(this.nbIterations);
		return "FAIRE"+this.iterations.peek()+" :\n";
	}

	/**
	 * Generate the code to go to the end of the loop if the expression is false.
	 * @return The YVM code.
	 */
	String ifFaux() {
		return "iffaux FAIT"+this.iterations.peek()+"\n";
	}

	/**
	 * Generate the code to go to the beginning of the loop.
	 * @return The YVM code.
	 */
	String gotoFaire() {
		return "goto FAIRE"+this.iterations.peek()+"\n";
	}

	/**
	 * Generate the code for a label at the end of the loop.
	 * @return The YVM code.
	 */
	String labelFait() {
		return "FAIT"+this.iterations.pop()+" :\n";
	}
	
	String ecrireEnt() {
		return "ecrireEnt\n"; 
	}
}
