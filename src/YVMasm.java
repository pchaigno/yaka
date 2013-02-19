
public class YVMasm extends YVM {
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	String iconstInt(int obj) {
		return "push "+obj+"\n";
	}
	
	String iconstBool(boolean obj) {
		if(obj) {
			return "iconst 0\n";
		}
		return "iconst -1\n";
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	String iident(String str) {
		Ident ident = Yaka.tabIdent.chercheIdent(str);
		if(ident.isVar()) {
			return "push word ptr [bp"+ident.getValue()+"]\n";
		}
		return "push "+ident.getValue()+"\n";
	}
	
	@Override
	String iadd() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "add ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String imul() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "imul bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String isub() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "sub ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String iand() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "and ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String ior() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "or ax, bx\n";
		str += "push ax\n";
		return str;
	}

	@Override
	String idiv() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cwd\n";
		str += "idiv bx\n";
		str += "push ax\n";
		return str;
	}

	String iinf() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jge $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	String iinfegal() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jg $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	String isupegal() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jl $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	String isup() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jle $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	String iegal() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "jne $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}

	String idiff() {
		String str = "pop bx\n";
		str += "pop ax\n";
		str += "cmp ax, bx\n";
		str += "je $+6\n";
		str += "push -1\n";
		str += "jmp $+4\n";
		str += "push 0\n";
		return str;
	}
}
