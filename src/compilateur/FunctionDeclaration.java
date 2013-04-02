package compilateur;

import compilateur.CompilationError.Error;

/**
 * Compute the declarations of functions.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class FunctionDeclaration {
	private int nbParameters;
	private IdFunction function;
	private Type lastTypeParameter;
	private String functionName;
	private boolean erreur;

	/**
	 * Check the type of the returned expression.
	 */
	public void checkReturnedType() {
		Type type = Yaka.expression.getType();
		Type expType = this.function.getType(); 
		if(type!=expType) {
			String message = "The returned type is incorrect for function '"+this.functionName+"'.\n";
			message += "Expected type "+expType+" but the expression returned is of type "+type+".";
			if(type==Type.ERROR) {
				Yaka.errors.addError(Error.NO_ERROR, message);
			} else {
				Yaka.errors.addError(Error.RETURNED_TYPE_INCORRECT, message);
			}
		}
	}

	/**
	 * Declare a new function and add it to the table of idents.
	 * @param identLu The name of the function.
	 * @param typeLu The type returned by the function.
	 */
	public void declarFunction(String identLu, Type typeLu) {
		this.nbParameters = 0;
		this.function = new IdFunction(typeLu);
		if(!Yaka.tabIdent.containsFunction(identLu)) {
			this.functionName = identLu;
			this.erreur = false;
		} else {
			this.functionName = identLu+"err";
			Yaka.errors.addError(Error.NAME_ALREADY_TAKEN, "The name '"+ identLu +"' is already taken by another function.");
			this.erreur = true;
		}
		Yaka.tabIdent.setFunction(this.functionName, this.function);
		Yaka.yvm.label(this.functionName);
	}

	/**
	 * Declare a new parameter for a function using the type given before.
	 * @param identLu The name of the parameter.
	 */
	public void addParameter(String identLu) {
		IdParam parameter = new IdParam(lastTypeParameter, this.nbParameters+1);
		Yaka.tabIdent.setIdent(identLu, parameter);
		this.nbParameters++;
	}

	/**
	 * Add a type for the next parameter to be declared.
	 * @param typeLu The type of the parameter.
	 */
	public void addTypeParameter(Type typeLu) {
		this.lastTypeParameter = typeLu;
		this.function.addParam(typeLu);
	}

	/**
	 * Compute the offsets.
	 * Transform the actual field offset of the parameters
	 * (currently egal to the rank) in the real offset.
	 */
	public void computeOffsets() {
		for(IdParam param: Yaka.tabIdent.getParameters()) {
			param.computeOffset(this.nbParameters);
		}
	}

	/**
	 * Called at the end of a function declaration.
	 * Clear the local variables.
	 * Reinitialize Declaration.
	 */
	public void endFunction() {
		Yaka.yvm.fermeBloc(this.nbParameters);
		Yaka.tabIdent.clear();
		Yaka.declaration = new Declaration();
		functionName = "";
		if(this.erreur) {
			Yaka.tabIdent.remove(this.functionName);
		}
	}

	/**
	 * Called in a function when a value need to be returned.
	 */
	public void returnValue() {
		int offset = 2*this.function.getNbParameters()+4;
		Yaka.yvm.ireturn(offset);
	}
}