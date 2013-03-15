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
public class YVM extends Generator {
	protected int nbIterations;
	protected Stack<Integer> iterations;
	protected int nbConditions;
	protected Stack<Integer> conditions;

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
	
	@Override
	public void generateFile(String name) {
		OutputStream f = Writer.open(name);
		Writer.writeString(f, this.program);
		Writer.close(f);
		System.out.println("---- Code YVM genere dans '" + name + "' ---- \n" + program);
	}
	
	@Override
	String entete() {
		return this.addLine("entete\n");
	}
	
	@Override
	String ouvreBloc(int nbVariables) {
		return this.addLine("ouvbloc "+nbVariables*2+"\n");
	}
	
	@Override
	String fermeBloc(int nbParameters) {
		return this.addLine("fermebloc "+nbParameters*2+"\n");
	}
	
	@Override
	String iconst(int obj) {
		return this.addLine("iconst "+obj+"\n");
	}
		
	@Override
	String iload(int offset) {
		return this.addLine("iload "+offset+"\n");
	}
	
	@Override
	String iadd() {
		return this.addLine("iadd\n");
	}

	@Override
	String imul() {
		return this.addLine("imul\n");
	}

	@Override
	String isub() {
		return this.addLine("isub\n");
	}

	@Override
	String iand() {
		return this.addLine("iand\n");
	}

	@Override
	String ior() {
		return this.addLine("ior\n");
	}

	@Override
	String idiv() {
		return this.addLine("idiv\n");
	}

	@Override
	String iinf() {
		return this.addLine("iinf\n");
	}

	@Override
	String iinfegal() {
		return this.addLine("iinfegal\n");
	}

	@Override
	String isupegal() {
		return this.addLine("isupegal\n");
	}

	@Override
	String isup() {
		return this.addLine("isup\n");
	}

	@Override
	String iegal() {
		return this.addLine("iegal\n");
	}

	@Override
	String idiff() {
		return this.addLine("idiff\n");
	}

	@Override
	String inot() {
		return this.addLine("inot\n");
	}

	@Override
	String ineg() {
		return this.addLine("ineg\n");
	}
	
	@Override
	String istore(int offset) {
		return this.addLine("istore "+offset+"\n");
	}
	
	@Override
	String aLaLigne() {
		return this.addLine("aLaLigne\n");
	}

	@Override
	String lire(int offset) {
		return this.addLine("lireEnt "+offset+"\n"); 
	}
	
	@Override
	String ecrireChaine(String s) {
		return this.addLine("ecrireChaine \""+s+"\"\n");
	}
	
	@Override
	String ecrireEnt() {
		return this.addLine("ecrireEnt\n"); 
	}
	
	@Override
	String ecrireBool() {
		return this.addLine("ecrireBool\n"); 
	}

	@Override
	String labelFaire() {
		this.nbIterations++;
		this.iterations.push(this.nbIterations);
		return addLine("FAIRE"+this.iterations.peek()+" :\n");
	}

	@Override
	String ifFauxFait() {
		return addLine("iffaux FAIT"+this.iterations.peek()+"\n");
	}

	@Override
	String gotoFaire() {
		return this.addLine("goto FAIRE"+this.iterations.peek()+"\n");
	}

	@Override
	String labelFait() {
		return this.addLine("FAIT"+this.iterations.pop()+" :\n");
	}
	
	@Override
	String ifFauxSinon() {
		this.nbConditions++;
		this.conditions.push(this.nbConditions);
		return this.addLine("iffaux SINON"+this.conditions.peek()+"\n");
	}
	
	@Override
	String gotoFsi() {
		return this.addLine("goto FSI"+this.conditions.peek()+"\n");
	}
	
	@Override
	String labelSinon() {
		return this.addLine("SINON"+this.conditions.peek()+" :\n");
	}
	
	@Override
	String labelFsi() {
		return this.addLine("FSI"+this.conditions.pop()+" :\n");
	}
	
	@Override
	String label(String label) {
		return this.addLine(label+" :\n");
	}
	
	@Override
	String main() {
		return this.addLine("main :\n");
	}

	@Override
	String callFunction(String functionName) {
		return this.addLine("call "+functionName+"\n");
	}
	
	@Override
	String reserveRetour() {
		return this.addLine("reserveRetour\n");
	}
	
	@Override
	String ireturn(int offset) {
		return this.addLine("ireturn "+offset+"\n");
	}
	
	@Override
	String queue() {
		return this.addLine("queue\n");
	}
}