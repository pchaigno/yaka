package compilateur;

import java.io.OutputStream;

/**
 * Generate the ASM code for each YVM function.
 */
public class YVMasm extends YVM {
	private static int cptES = 0;
	
	/**
	 * Constructor
	 */
	public YVMasm() {
		super();
		cptES = 0;
	}

	@Override
	public void generateFile(String name) {
		OutputStream f = Ecriture.ouvrir(name);
		Ecriture.ecrireString(f, this.program);
		Ecriture.fermer(f);
		System.out.println("---- Code ASM genere dans '" + name + "' : \n" + program);
	}
	
	@Override
	String header() {
		this.addLine("; ");
		super.header();
		String str = "extrn lirent:proc, ecrent:proc\n";
		str += "extrn ecrbool:proc\n";
		str += "extrn ecrch:proc, ligsuiv:proc\n";
		str += ".model SMALL\n";
		str += ".586\n\n";
		str += ".CODE\n";
		str += "debut :\n";
		str += "STARTUPCODE\n\n";
		return this.addLine(str);
	}
	
	@Override
	String bookMemory(int nbVariables) {
		this.addLine("; ");
		super.bookMemory(nbVariables);
		String str = "mov bp, sp\n";
		str += "sub sp, "+nbVariables*2+"\n\n";
		return this.addLine(str);
	}

	@Override
	String iconst(int obj) {
		this.addLine("; ");
		super.iconst(obj);
		String str = "push word ptr "+obj+"\n\n";
		return this.addLine(str);
	}
	
	@Override
	String iload(int offset) {
		this.addLine("; ");
		super.iload(offset);
		String str = "";
		if(offset<0) {
			str += "push word ptr [bp"+offset+"]\n\n";
		} else {
			str += "push word ptr [bp+"+offset+"]\n\n";
		}
		return this.addLine(str);
	}
	
	@Override
	String iadd() {
		this.addLine("; ");
		super.iadd();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "add ax, bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String imul() {
		this.addLine("; ");
		super.imul();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "imul bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String isub() {
		this.addLine("; ");
		super.isub();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "sub ax, bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String iand() {
		this.addLine("; ");
		super.iand();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "and ax, bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String ior() {
		this.addLine("; ");
		super.ior();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "or ax, bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String idiv() {
		this.addLine("; ");
		super.idiv();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cwd\n";
		str += "idiv bx\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String iinf() {
		this.addLine("; ");
		super.iinf();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jge $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String iinfegal() {
		this.addLine("; ");
		super.iinfegal();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jg $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String isupegal() {
		this.addLine("; ");
		super.isupegal();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jl $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String isup() {
		this.addLine("; ");
		super.isup();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jle $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String iegal() {
		this.addLine("; ");
		super.iegal();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jne $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String idiff() {
		this.addLine("; ");
		super.idiff();
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "je $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return this.addLine(str);
	}

	@Override
	String inot() {
		this.addLine("; ");
		super.inot();
		String str = "pop ax\n";
		str += "not ax\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}

	@Override
	String ineg() {
		this.addLine("; ");
		super.ineg();
		String str = "pop ax\n";
		str += "neg ax\n";
		str += "push ax\n\n";
		return this.addLine(str);
	}
	
	@Override
	String istore(int offset) {
		this.addLine("; ");
		super.istore(offset);
		String str = "pop ax\n";
		if(offset<0) {
			str += "mov word ptr [bp"+offset+"], ax\n\n";
		} else {
			str += "mov word ptr [bp+"+offset+"], ax\n\n";
		}
		return this.addLine(str);
	}
	
	@Override
	String aLaLigne() {
		this.addLine("; ");
		super.aLaLigne();
		String str = "call ligsuiv\n\n";
		return this.addLine(str);
	}
	
	@Override
	String lire(int offset) {
		this.addLine("; ");
		super.lire(offset);
		String str = "";
		if(offset<0) {
			str += "lea dx, [bp"+offset+"]\n";
		} else {
			str += "lea dx, [bp+"+offset+"]\n";
		}
		str += "push dx\n";
		str += "call lirent\n\n";
		return this.addLine(str);
	}
	
	@Override
	String ecrireChaine(String s) {
		this.addLine("; ");
		super.ecrireChaine(s); 
		String str = ".DATA\n";
		str += "mess"+cptES+" DB \""+s+"$\" \n";
		str += ".CODE\n";
		str += "lea dx, mess"+cptES+"\n";
		str += "push dx\n";
		str += "call ecrch\n\n";
		cptES++;
		return this.addLine(str);
	}
	
	@Override
	String ecrireEnt() {
		this.addLine("; ");
		super.ecrireEnt();
		String str = "call ecrent\n\n";
		return this.addLine(str); 
	}
	
	@Override
	String labelFaire() {
		this.nbIterations++;
		this.iterations.push(this.nbIterations);
		return "FAIRE"+this.iterations.peek()+" :\n";
	}

	@Override
	String ifFaux() {
		String str = "pop ax\n";
		str += "cmp ax\n";
		str += "je FAIT"+this.iterations.peek()+"\n";
		return str;
	}

	@Override
	String gotoFaire() {
		return "jmp FAIRE"+this.iterations.peek()+"\n";
	}

	@Override
	String labelFait() {
		return "FAIT"+this.iterations.pop()+" :\n";
	}
	
	@Override
	String footer() {
		this.addLine("; ");
		super.footer();
		String str = "nop\n";
		str += "EXITCODE\n";
		str += "end debut\n";
		return this.addLine(str);
	}
}