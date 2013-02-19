
public class Declaration {

	public void declarationConst(String nom, IdConst id){
		if(! Yaka.tabIdent.existeIdent(nom)){
			Yaka.tabIdent.rangeIdent(nom, id);
		}else{
			System.err.println("Nom déjà pris.");
		}
	}
	
	public void declarationVar(String nom, IdVar id){
		if(! Yaka.tabIdent.existeIdent(nom)){
			Yaka.tabIdent.rangeIdent(nom, id);
		}else{
			System.err.println("Nom déjà pris.");
		}
	}
	
}
