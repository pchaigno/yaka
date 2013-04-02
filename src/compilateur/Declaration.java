package compilateur;

import compilateur.CompilationError.Error;

/**
 * Compute the declaration part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Declaration {
	private String lastConstName;
	private Type lastVarType;
	private int offset;
	
	/**
	 * Constructor
	 */
	public Declaration() {
		this.offset = -2;
	}
	
	/**
	 * Called when a constant is declared.
	 * @param name The name of the constant.
	 */
	public void defConst(String name) {
		if(!Yaka.tabIdent.containsIdent(name)) {
			this.lastConstName = name;
		} else {
			Yaka.errors.addError(Error.NAME_ALREADY_TAKEN, "A variable or a constant already has the name '"+name+"'.");
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
			this.lastConstName = "";
		} else {
			Yaka.errors.addError(Error.NAME_ALREADY_TAKEN, "Value not assigned because the name was already taken.");
		}
	}
	
	/**
	 * Called when a reference to an other constant is set as the value of a constant.
	 * @param ref The name of the other constant.
	 */
	public void valConstRef(String ref) {
		Ident ident = Yaka.tabIdent.getIdent(ref);
		if(ident != null) {
			if(this.lastConstName != "") {
				Yaka.tabIdent.setIdent(this.lastConstName, new IdConst(ident.type, ((IdConst)ident).getValue()));
			} else {
				Yaka.errors.addError(Error.NAME_ALREADY_TAKEN, "Value not assigned because the name was already taken.");
			}
		} else {
			Yaka.errors.addError(Error.IDENT_UNKNOWN, "Reference to undefined const '"+ref+"' in the declaration part.");
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
			Yaka.tabIdent.setIdent(name, new IdVar(lastVarType, this.offset));
			this.offset -= 2;
		} else {
			Yaka.errors.addError(Error.NAME_ALREADY_TAKEN, "A variable or a constant already has the name '"+name+"'.");
		}
	}
	
	/**
	 * Called at the end of the declarations.
	 * Book memory space according to the number of variables.
	 */
	public void bookMemory() {
		Yaka.yvm.ouvreBloc(Yaka.tabIdent.getNumberOfVariables());
	}
}