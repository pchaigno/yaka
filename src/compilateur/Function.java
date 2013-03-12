package compilateur;

/**
 * Compute the function part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Function {
	private int nbArguments; // Used during the declaration and the call.
	private String functionName;
	private IdFunction function;
	private Type lastTypeParameter;

	/**
	 * Check the type of the returned expression.
	 */
	public void checkReturnedType() {
		if(Yaka.expression.getType()!=this.function.getType()) {
			System.err.println("Function: The returned type is incorrect.");
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
		this.nbArguments = 0;
	}

	/**
	 * Declare a new parameter for a function using the type given before.
	 * @param identLu The name of the parameter.
	 */
	public void addParameter(String identLu) {
		int offset = 4+4-(this.nbArguments*2);
		IdParam parameter = new IdParam(this.lastTypeParameter, offset);
		Yaka.tabIdent.setIdent(identLu, parameter);
		this.nbArguments++;
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
	 * Check the type of an argument of a function.
	 * Need to check the arguments in the right order.
	 */
	public void checkParameter() {
		Type typeNeeded = this.function.getTypeOfParameter(this.nbArguments);
		if(Yaka.expression.getType()!=typeNeeded) {
			System.err.println("Function: The "+this.nbArguments+"ieme parameter doesn't have the right type.");
		}
		this.nbArguments++;
	}

	/**
	 * Called at the beginning of a function call to record the name of the function called.
	 * @param identLu The name of the function to call.
	 */
	public void prepareCallFunction(String identLu) {
		this.function = Yaka.tabIdent.getFunction(identLu);
		if(this.function==null) {
			System.err.println("Function: There is no function with this name.");
		} else {
			this.nbArguments = 0;
			this.functionName = identLu;
		}
	}

	/**
	 * Called at the end of a function call in the Yaka code.
	 * Check the number of arguments.
	 */
	public void callFunction() {
		if(this.nbArguments!=this.function.getNbParameters()) {
			System.err.println("Function: Incorrect number of arguments.");
		}
		Yaka.yvm.callFunction(this.functionName);
	}
}