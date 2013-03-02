package compilateur;

import java.io.OutputStream;

/**
 * Generate the YVM code for each YVM function.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class YVM extends Generator {
	protected String program;
	
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
	String ouvrePrinc(int nbVariables) {
		return this.addLine("ouvrePrinc "+nbVariables*2+"\n");
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
	String queue() {
		return this.addLine("queue\n");
	}
}