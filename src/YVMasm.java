
/**
 * Generate the ASM code for each YVM function.
 */
public class YVMasm extends YVM {

	private static int cptEs = 0;
	
	public YVMasm() {
		super();
		cptES = 0;
	}
	

	@Override
	String entete() {
		String str = "; " + super.entete();
		str += "extrn lirent:proc, ecrent:proc\n";
		str += "extrn ecrbool:proc\n";
		str += "extrn ecrch:proc, ligsuiv:proc\n";
		str += ".model SMALL\n";
		str += ".586\n\n";
		return str;
	}

	@Override
	String iconst(int obj) {
		return "push "+obj+"\n";
	}
	
	@Override
	String iload(int offset) {
		return "push word ptr [bp"+offset+"]\n";
	}
	
	@Override
	String iadd() {
		String str = "; " + super.iadd();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "add ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String imul() {
		String str = "; " + super.imul();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "imul bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String isub() {
		String str = "; " + super.isub();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "sub ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String iand() {
		String str = "; " + super.iand();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "and ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String ior() {
		String str = "; " + super.ior();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "or ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String idiv() {
		String str = "; " + super.idiv();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cwd\n";
		str += "idiv bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String iinf() {
		String str = "; " + super.iinf();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jge $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	@Override
	String iinfegal() {
		String str = "; " + super.iinfegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jg $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	@Override
	String isupegal() {
		String str = "; " + super.isupegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jl $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	@Override
	String isup() {
		String str = "; " + super.isup();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jle $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	@Override
	String iegal() {
		String str = "; " + super.iegal();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jne $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	@Override
	String idiff() {
		String str = "; " + super.idiff();
		str += "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "je $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}
	
	@Override
	String istore(int offset) {
		String str = "; " + super.istore(offset);
		str += "pop ax\n";
		str += "mov word ptr [bp"+offset+"], ax\n";
		return str;
	}
	
	String aLaLigne() {
		return "call ligsuiv";
	}
	
	String lire(int offset) {
		String str = "";
		if(offset<0){
			str += "lea dx,[bp"+offset+"]\n";
		}else{
			str += "lea dx,[bp+"+offset+"]\n";
		}
		str += "push dx\n";
		str += "call lirent\n";
		return str;
	}
	
	String ecrireChaine(String s) {
		String str = "";
		str += ".DATA\n";
		str += "mess"+cptES+" DB \""+s+"$\" \n";
		str += ".CODE\n";
		str += "lea dx,mess"+cptES+"\n";
		str += "push dx\n";
		str += "call ecrch\n";
		cptES++;
		return str;
	}
	
	String ecrireEnt() {
		return "call ecrent\n"; 
	}
}