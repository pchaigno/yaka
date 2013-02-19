/**
 * Compute the declaration part.
 */
public class Declaration {
	private String lastConstName;
	private static int offset;
	
	/**
	 * Constructor
	 */
	public Declaration() {
		offset = -2;
	}
	
	/**
	 * Called when a constant is declared.
	 * @param name The name of the constant.
	 */
	public void defConst(String name) {
		if(!Yaka.tabIdent.existIdent(name)) {
			lastConstName = name;
		} else {
			System.err.println("Declaration: Name '" + name + "' already taken.");
		}
	}
	
	/**
	 * Called when a value is assigned to the last constant.
	 * @param type The type of the value.
	 * @param value The value.
	 */
	public void valConst(Type type, int value) {
		if(lastConstName != "") {
			Yaka.tabIdent.setIdent(lastConstName, new IdConst(type, value));
		} else {
			System.err.println("Declaration: Const def name undefined");
		}
	}
	
	/**
	 * Called when a variable is declared.
	 * @param type The type of the variable.
	 * @param name The name of the variable.
	 */
	public void declVar(Type type, String name){
		if(!Yaka.tabIdent.existIdent(name)) {
			Yaka.tabIdent.setIdent(name, new IdVar(type, offset));
			offset -= 2;
		} else {
			System.err.println("Declaration: Name '" + name + "' already taken.");
		}
	}
	
}