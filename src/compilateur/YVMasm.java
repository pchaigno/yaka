package compilateur;

import java.io.OutputStream;

/**
 * Generate the ASM code for each YVM function.
 */
public class YVMasm extends YVM {
	private String program;
	private static int cptES = 0;
	
	/**
	 * Constructor
	 */
	public YVMasm() {
		this.program = "";
		cptES = 0;
	}

	@Override
	protected String addLine(String str) {
		this.program += str;
		return str;
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
		String str = "; "+super.header();
		str += "extrn lirent:proc, ecrent:proc\n";
		str += "extrn ecrbool:proc\n";
		str += "extrn ecrch:proc, ligsuiv:proc\n";
		str += ".model SMALL\n";
		str += ".586\n\n";
		str += ".CODE\n";
		str += "debut :\n";
		str += "STARTUPCODE\n\n";
		return addLine(str);
	}

	@Override
	String iconst(int obj) {
		String str = "; "+super.iconst(obj);
		str += "push "+obj+"\n\n";
		return addLine(str);
	}
	
	@Override
	String iload(int offset) {
		String str = "; "+super.iload(offset);
		str += "push word ptr [bp"+offset+"]\n\n";
		return addLine(str);
	}
	
	@Override
	String iadd() {
		String str = "; "+super.iadd();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "add ax, bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String imul() {
		String str = "; "+super.imul();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "imul bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String isub() {
		String str = "; "+super.isub();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "sub ax, bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String iand() {
		String str = "; "+super.iand();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "and ax, bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String ior() {
		String str = "; "+super.ior();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "or ax, bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String idiv() {
		String str = "; "+super.idiv();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cwd\n";
		str += "idiv bx\n";
		str += "push ax\n\n";
		return addLine(str);
	}

	@Override
	String iinf() {
		String str = "; "+super.iinf();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jge $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}

	@Override
	String iinfegal() {
		String str = "; "+super.iinfegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jg $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}

	@Override
	String isupegal() {
		String str = "; "+super.isupegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jl $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}

	@Override
	String isup() {
		String str = "; "+super.isup();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jle $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}

	@Override
	String iegal() {
		String str = "; "+super.iegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jne $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}

	@Override
	String idiff() {
		String str = "; "+super.idiff();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "je $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n\n";
		return addLine(str);
	}
	
	@Override
	String istore(int offset) {
		String str = "; "+super.istore(offset);
		str += "pop ax\n";
		str += "mov word ptr [bp"+offset+"], ax\n\n";
		return addLine(str);
	}
	
	@Override
	String aLaLigne() {
		String str = "; "+super.aLaLigne();
		str += "call ligsuiv\n\n";
		return addLine(str);
	}
	
	@Override
	String lire(int offset) {
		String str = "; "+super.lire(offset);
		if(offset<0){
			str += "lea dx,[bp"+offset+"]\n";
		}else{
			str += "lea dx,[bp+"+offset+"]\n";
		}
		str += "push dx\n";
		str += "call lirent\n\n";
		return addLine(str);
	}
	
	@Override
	String ecrireChaine(String s) {
		String str = "; "+super.ecrireChaine(s); 
		str += ".DATA\n";
		str += "mess"+cptES+" DB \""+s+"$\" \n";
		str += ".CODE\n";
		str += "lea dx,mess"+cptES+"\n";
		str += "push dx\n";
		str += "call ecrch\n\n";
		cptES++;
		return addLine(str);
	}
	
	@Override
	String ecrireEnt() {
		String str = "; "+super.ecrireEnt();
		str += "call ecrent\n\n";
		return addLine(str); 
	}
	
	@Override
	String footer() {
		String str = "; "+super.footer();
		str += "nop\n";
		str += "EXITCODE\n";
		str += "end debut\n";
		return addLine(str);
	}
}