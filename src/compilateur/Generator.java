package compilateur;

import java.io.OutputStream;

/**
 * Generate the code.
 * Extends for each generated language.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public abstract class Generator {
	protected String program;

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
		OutputStream f = Writer.open(name);
		Writer.writeString(f, this.program);
		Writer.close(f);
	}
	
	/**
	 * Generate the program's header.
	 * @return The code.
	 */
	abstract String entete();
	
	/**
	 * Generate the code to book memory space.
	 * @param nbVariables The number of variables.
	 * @return The code.
	 */
	abstract String ouvrePrinc(int nbVariables);
	
	/**
	 * Generate the code for an iconst instruction.
	 * @param obj The integer.
	 * @return The code.
	 */
	abstract String iconst(int obj);
		
	/**
	 * Generate the code for an iload instruction.
	 * @param offset The offset.
	 * @return The code.
	 */
	abstract String iload(int offset);
	
	/**
	 * Generate the code for an iadd instruction.
	 * @return The code.
	 */
	abstract String iadd();

	/**
	 * Generate the code for an imul instruction.
	 * @return The code.
	 */
	abstract String imul();

	/**
	 * Generate the code for an isub instruction.
	 * @return The code.
	 */
	abstract String isub();

	/**
	 * Generate the code for an iand instruction.
	 * @return The YVM code.
	 */
	abstract String iand();

	/**
	 * Generate the code for an ior instruction.
	 * @return The code.
	 */
	abstract String ior();

	/**
	 * Generate the code for an idiv instruction.
	 * @return The code.
	 */
	abstract String idiv();

	/**
	 * Generate the code for an iinf instruction.
	 * @return The code.
	 */
	abstract String iinf();

	/**
	 * Generate the code for an iinfegal instruction.
	 * @return The code.
	 */
	abstract String iinfegal();

	/**
	 * Generate the code for an isupegal instruction.
	 * @return The code.
	 */
	abstract String isupegal();

	/**
	 * Generate the code for an isup instruction.
	 * @return The code.
	 */
	abstract String isup();

	/**
	 * Generate the code for an iegal instruction.
	 * @return The code.
	 */
	abstract String iegal();

	/**
	 * Generate the code for an idiff instruction.
	 * @return The code.
	 */
	abstract String idiff();

	/**
	 * Generate the code for an inot instruction.
	 * @return The code.
	 */
	abstract String inot();

	/**
	 * Generate the code for an ineg instruction.
	 * @return The code.
	 */
	abstract String ineg();
	
	/**
	 * Generate the code for an istore instruction.
	 * @return The code.
	 */
	abstract String istore(int offset);
	
	/**
	 * Generate the code for an aLaLigne instruction.
	 * @return The code.
	 */
	abstract String aLaLigne();
	
	/**
	 * Generate the code for an lire instruction.
	 * @return The code.
	 */
	abstract String lire(int offset);
	
	/**
	 * Generate the code for an ecrireChaine instruction.
	 * @return The code.
	 */
	abstract String ecrireChaine(String s);
	
	/**
	 * Generate the code for an ecrireEnt instruction.
	 * @return The code.
	 */
	abstract String ecrireEnt();
	
	/**
	 * Generate the code for a label at the beginning of the loop.
	 * @return The YVM code.
	 */
	abstract String labelFaire();

	/**
	 * Generate the code to go to the end of the loop if the expression is false.
	 * @return The YVM code.
	 */
	abstract String ifFauxFait();

	/**
	 * Generate the code to go to the beginning of the loop.
	 * @return The YVM code.
	 */
	abstract String gotoFaire();

	/**
	 * Generate the code for a label at the end of the loop.
	 * @return The YVM code.
	 */
	abstract String labelFait();
	
	/**
	 * Generate the code to go to the second part of the condition bloc.
	 * @return The YVM code.
	 */
	abstract String ifFauxSinon();
	
	/**
	 * Generate the code to go to the end of the condition bloc.
	 * @return The YVM code.
	 */
	abstract String gotoFsi();
	
	/**
	 * Generate the code for a label for the second part of the condition bloc.
	 * @return The YVM code.
	 */
	abstract String labelSinon();
	
	/**
	 * Generate the code for a label at the end of the condition bloc.
	 * @return The YVM code.
	 */
	abstract String labelFsi();
	
	/**
	 * Generate the program's footer.
	 * @return The code.
	 */
	abstract String queue();
}
