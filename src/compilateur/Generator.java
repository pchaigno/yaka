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
	 * Constructor
	 */
	public Generator() {
		this.program = "";
	}
	
	/**
	 * Add some lines to the program and return this line.
	 * @param str The lines to add.
	 * @return The lines.
	 */
	protected String addLine(String str) {
		str = str.replaceAll("\n", "\n\t");
		str = str.replaceAll("; 	", "; ");
		this.program += "	" + str;
		return str;
	}
	
	/**
	 * Add some lines to the program without tabulation and return this line.
	 * @param str The lines to add.
	 * @return The lines without tabulation.
	 */
	protected String addLineWithoutTab(String str) {
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
	 * @return The code generated.
	 */
	abstract String entete();
	
	/**
	 * Generate the code to book memory space.
	 * @param nbVariables The number of variables.
	 * @return The code generated.
	 */
	abstract String ouvreBloc(int nbVariables);
	
	/**
	 * Generate the code end a function.
	 * @param nbParameters The number of parameters
	 * @return The code generated.
	 */
	abstract String fermeBloc(int nbParameters);
	
	/**
	 * Generate the code for an iconst instruction.
	 * @param obj The integer.
	 * @return The code generated.
	 */
	abstract String iconst(int obj);
		
	/**
	 * Generate the code for an iload instruction.
	 * @param offset The offset.
	 * @return The code generated.
	 */
	abstract String iload(int offset);
	
	/**
	 * Generate the code for an iadd instruction.
	 * @return The code generated.
	 */
	abstract String iadd();

	/**
	 * Generate the code for an imul instruction.
	 * @return The code generated.
	 */
	abstract String imul();

	/**
	 * Generate the code for an isub instruction.
	 * @return The code generated.
	 */
	abstract String isub();

	/**
	 * Generate the code for an iand instruction.
	 * @return The code generated.
	 */
	abstract String iand();

	/**
	 * Generate the code for an ior instruction.
	 * @return The code generated.
	 */
	abstract String ior();

	/**
	 * Generate the code for an idiv instruction.
	 * @return The code generated.
	 */
	abstract String idiv();

	/**
	 * Generate the code for an iinf instruction.
	 * @return The code generated.
	 */
	abstract String iinf();

	/**
	 * Generate the code for an iinfegal instruction.
	 * @return The code generated.
	 */
	abstract String iinfegal();

	/**
	 * Generate the code for an isupegal instruction.
	 * @return The code generated.
	 */
	abstract String isupegal();

	/**
	 * Generate the code for an isup instruction.
	 * @return The code generated.
	 */
	abstract String isup();

	/**
	 * Generate the code for an iegal instruction.
	 * @return The code generated.
	 */
	abstract String iegal();

	/**
	 * Generate the code for an idiff instruction.
	 * @return The code generated.
	 */
	abstract String idiff();

	/**
	 * Generate the code for an inot instruction.
	 * @return The code generated.
	 */
	abstract String inot();

	/**
	 * Generate the code for an ineg instruction.
	 * @return The code generated.
	 */
	abstract String ineg();
	
	/**
	 * Generate the code for an istore instruction.
	 * @return The code generated.
	 */
	abstract String istore(int offset);
	
	/**
	 * Generate the code for an aLaLigne instruction.
	 * @return The code generated.
	 */
	abstract String aLaLigne();
	
	/**
	 * Generate the code for an lire instruction.
	 * @return The code generated.
	 */
	abstract String lire(int offset);
	
	/**
	 * Generate the code for an ecrireChaine instruction.
	 * @return The code generated.
	 */
	abstract String ecrireChaine(String s);
	
	/**
	 * Generate the code for an ecrireEnt instruction.
	 * @return The code generated.
	 */
	abstract String ecrireEnt();
	
	/**
	 * Generate the code for an ecrireBool instruction.
	 * @return The code generated.
	 */
	abstract String ecrireBool();
	
	/**
	 * Generate the code for a label at the beginning of the loop.
	 * @return The code generated.
	 */
	abstract String labelFaire();

	/**
	 * Generate the code to go to the end of the loop if the expression is false.
	 * @return The code generated.
	 */
	abstract String ifFauxFait();

	/**
	 * Generate the code to go to the beginning of the loop.
	 * @return The code generated.
	 */
	abstract String gotoFaire();

	/**
	 * Generate the code for a label at the end of the loop.
	 * @return The code generated.
	 */
	abstract String labelFait();
	
	/**
	 * Generate the code to go to the second part of the condition bloc.
	 * @return The code generated.
	 */
	abstract String ifFauxSinon();
	
	/**
	 * Generate the code to go to the end of the condition bloc.
	 * @return The code generated.
	 */
	abstract String gotoFsi();
	
	/**
	 * Generate the code for a label for the second part of the condition bloc.
	 * @return The code generated.
	 */
	abstract String labelSinon();
	
	/**
	 * Generate the code for a label at the end of the condition bloc.
	 * @return The code generated.
	 */
	abstract String labelFsi();
	
	/**
	 * Generate the code for a label (used for functions).
	 * @return The code generated.
	 */
	abstract String label(String label);

	/**
	 * Generate the code for the main label.
	 * @return The code generated.
	 */
	abstract String main();
	
	/**
	 * Generate the code for a function call.
	 * @param functionName The name of the function.
	 * @return The code generated.
	 */
	abstract String callFunction(String functionName);
	
	/**
	 * Generate the code to book space in the stack.
	 * @return The code generated.
	 */
	abstract String reserveRetour();
	
	/**
	 * Generate the code to save the result of a function in the stack.
	 * @param offset The offset in the stack.
	 * @return The code generated.
	 */
	abstract String ireturn(int offset);
	
	/**
	 * Generate the program's footer.
	 * @return The code generated.
	 */
	abstract String queue();
}
