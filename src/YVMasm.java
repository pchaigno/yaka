
public class YVMasm extends YVM {
	
	@Override
	String iconst(String obj) {
		Ident ident = Yaka.tabIdent.chercheIdent(obj);
		return "iconst"++"\n";
	}
	
	@Override
	String iload(String str) {
		return "iload "+str+"\n";
	}
	
	@Override
	String iadd() {
		return "iadd\n";
	}

	@Override
	String imul() {
		return "imul\n";
	}

	@Override
	String isub() {
		return "isub\n";
	}

	@Override
	String iand() {
		return "iand\n";
	}

	@Override
	String ior() {
		return "ior\n";
	}

	@Override
	String idiv() {
		return "idiv\n";
	}
}
