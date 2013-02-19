
public class Declaration {
	private String lastConstName;
	private static int offset;
	
	public Declaration(){
		offset = -2;
	}
	
	public void defConst(String name){
		if(! Yaka.tabIdent.existIdent(name)){
			lastConstName = name;
		}else{
			System.err.println("Declaration: Name '" + name + "' already taken.");
		}
	}
	
	public void valConst(Type type, int value) {
		if (lastConstName != ""){
			Yaka.tabIdent.setIdent(lastConstName, new IdConst(type, value));
		}else{
			System.err.println("Declaration: Const def name undefined");
		}
	}
	
	public void declVar(Type type, String name){
		if(! Yaka.tabIdent.existIdent(name)){
			Yaka.tabIdent.setIdent(name, new IdVar(type, offset));
			offset -=2;
		}else{
			System.err.println("Declaration: Name '" + name + "' already taken.");
		}
	}
	
}
