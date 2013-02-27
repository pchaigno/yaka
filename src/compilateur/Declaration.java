package compilateur;

/**
 * Compute the declaration part.
 */
public class Declaration {
	private String lastConstName;
	private Type lastVarType;
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
		if(!Yaka.tabIdent.containsIdent(name)) {
			this.lastConstName = name;
		} else {
			System.err.println("Declaration: Name '"+name+"' already taken.");
		}
	}
	
	/**
	 * Called when a value is assigned to the last constant.
	 * @param type The type of the value.
	 * @param value The value.
	 */
	public void valConst(Type type, int value) {
		if(this.lastConstName != "") {
			Yaka.tabIdent.setIdent(this.lastConstName, new IdConst(type, value));
			Yaka.yvm.iconst(value);
		} else {
			System.err.println("Declaration: Constant name undefined");
		}
	}
	
	/**
	 * Called when a reference to an other constant is set as the value of the last constant.
	 * @param ref The name of the other constant.
	 */
	public void valConstRef(String ref) {
		Ident ident = Yaka.tabIdent.getIdent(ref);
		if(ident != null) {
			Yaka.tabIdent.setIdent(this.lastConstName, new IdConst(ident.type, ident.getValue()));
		} else {
			System.err.println("Declaration: Reference to undefined const");
		}
	}
	
	/**
	 * Called when a type of variable is announced.
	 * @param type The type.
	 */
	public void declVar(Type type) {
		this.lastVarType = type;
	}
	
	/**
	 * Called when a variable is declared.
	 * @param name The name of the variable.
	 */
	public void defVar(String name) {
		if(!Yaka.tabIdent.containsIdent(name)) {
			Yaka.tabIdent.setIdent(name, new IdVar(lastVarType, offset));
			offset -= 2;
		} else {
			System.err.println("Declaration: Name '"+name+"' already taken.");
		}
	}
}