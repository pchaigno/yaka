
public class EntreeSortie {

	public void aLaLigne() {
		Yaka.program += Yaka.yvm.aLaLigne();
	}
	
	public void ecrireEnt() {
		Yaka.program += Yaka.yvm.ecrireEnt();
	}
	
	public void ecrireChaine(String ident) {
		Yaka.program += Yaka.yvm.ecrireChaine(ident);
	}
	
	public void lire (String ident) {
		if(Yaka.tabIdent.containsIdent(ident)){
			Yaka.program += Yaka.yvm.lire(Yaka.tabIdent.getIdent(ident).getValue());
		}else{
			System.err.println("EntreeSortie: ");
		}
	}	
}