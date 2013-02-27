
public class EntreeSortie {

	public void aLaLigne() {
		Yaka.yvm.aLaLigne();
	}
	
	public void ecrireEnt() {
		Yaka.yvm.ecrireEnt();
	}
	
	public void ecrireChaine(String chaine) {
		Yaka.yvm.ecrireChaine(chaine);
	}
	
	public void lire (String ident) {
		if(Yaka.tabIdent.containsIdent(ident)){
			Yaka.yvm.lire(Yaka.tabIdent.getIdent(ident).getValue());
		}else{
			System.err.println("EntreeSortie: ");
		}
	}
	
	
}
