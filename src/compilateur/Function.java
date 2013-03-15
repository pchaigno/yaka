package compilateur;

/**
 * Compute the function part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Function {
	private int nbParameters; // Used during the declaration and the call.
	private String functionName;
	private IdFunction function;
	private Type lastTypeParameter;

	/**
	 * Check the type of the returned expression.
	 */
	public void checkReturnedType() {
		if(Yaka.expression.getType()!=this.function.getType()) {
			System.err.println("Function: The returned type is incorrect for function "+this.functionName+".");
		}
	}
	
	/**
	 * Declare a new function and add it to the table of idents.
	 * @param identLu The name of the function.
	 * @param typeLu The type returned by the function.
	 */
	public void declarFunction(String identLu, Type typeLu) {
		this.function = new IdFunction(identLu, typeLu); 
		Yaka.tabIdent.setFunction(identLu, this.function);
		this.nbParameters = 0;
		Yaka.yvm.label(identLu);
	}

	/**
	 * Declare a new parameter for a function using the type given before.
	 * @param identLu The name of the parameter.
	 */
	public void addParameter(String identLu) {
		IdParam parameter = new IdParam(this.lastTypeParameter, this.nbParameters+1);
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
	 * Check the type of an argument of a function.
	 * Need to check the arguments in the right order.
	 */
	public void checkParameter() {
		if(this.function.getNbParameters()>this.nbParameters) {
			Type typeNeeded = this.function.getTypeOfParameter(this.nbParameters);
			if(Yaka.expression.getType()!=typeNeeded) {
				System.err.println("Function: The "+this.nbParameters+"ieme parameter doesn't have the right type for function "+this.functionName+".");
			}
			this.nbParameters++;
		} else {
			System.err.println(this.nbParameters+"");
			System.err.println("Function: There are too many parameters ("+this.function.getNbParameters()+" normaly) for function "+this.functionName+".");
		}
	}

	/**
	 * Called at the beginning of a function call to record the name of the function called.
	 * @param identLu The name of the function to call.
	 */
	public void prepareCallFunction(String identLu) {
		this.function = Yaka.tabIdent.getFunction(identLu);
		if(this.function==null) {
			System.err.println("Function: There is no function with this name ("+this.functionName+").");
		} else {
			this.nbParameters = 0;
			this.functionName = identLu;
			Yaka.yvm.reserveRetour();
		}
	}

	/**
	 * Called at the end of a function call in the Yaka code.
	 * Check the number of arguments.
	 */
	public void callFunction() {
		if(this.nbParameters!=this.function.getNbParameters()) {
			System.err.println("Function: Incorrect number of arguments for function "+this.functionName+".");
		}
		Yaka.yvm.callFunction(this.functionName);
		Yaka.expression.pushFunction(this.function.getType());
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
	}
	
	/**
	 * Called in a function when a value need to be returned.
	 */
	public void returnValue() {
		int offset = 2*this.function.getNbParameters()+4;
		Yaka.yvm.ireturn(offset);
	}
}