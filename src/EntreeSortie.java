
public class EntreeSortie {

	public void aLaLigne() {
		Yaka.yvm.aLaLigne();
	}
	
	public void ecrireExpr(String ident) {
		if(Yaka.tabIdent.containsIdent(ident)){
			Yaka.yvm.iload(Yaka.tabIdent.getIdent(ident).getValue());
		}else{
			System.err.println("EntreeSortie: ");
		}
	}
	
	public void ecrireChaine(String ident) {
		Yaka.yvm.ecrireChaine(ident);
	}
	
	public void lire (String ident) {
		if(Yaka.tabIdent.containsIdent(ident)){
			Yaka.yvm.lire(Yaka.tabIdent.getIdent(ident).getValue());
		}else{
			System.err.println("EntreeSortie: ");
		}
	}
	
	
}
