package compilateur;

import java.util.Stack;
import compilateur.CompilationError.Error;

/**
 * Compute the calls of functions.
 * Use stacks to handle nested calls.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class FunctionCall {
	private Stack<Integer> nbParameters;
	private Stack<IdFunction> functions;
	private Stack<String> functionsName;
	
	/**
	 * Constructor
	 */
	public FunctionCall() {
		this.nbParameters = new Stack<Integer>();
		this.functions = new Stack<IdFunction>();
		this.functionsName = new Stack<String>();
	}

	/**
	 * Check the type of an argument of a function.
	 * Need to check the arguments in the right order.
	 */
	public void checkParameter() {
		IdFunction function = this.functions.peek();
		int nbParams = this.nbParameters.pop();
		String functionName = this.functionsName.peek();
		if(function.getNbParameters()>nbParams) {
			Type typeNeeded = function.getTypeOfParameter(nbParams);
			if(Yaka.expression.getType()!=typeNeeded) {
				Yaka.errors.addError(Error.TYPE_PARAMETER_INCORRECT, "The "+(nbParams+1)+"ieme parameter doesn't have the right type for function "+functionName+".");
			}
			nbParams++;
			this.nbParameters.push(nbParams);
		} else {
			Yaka.errors.addError(Error.NUMBER_PARAMETERS_INCORRECT, "There are too many parameters ("+function.getNbParameters()+" normaly) for function "+functionName+".");
		}
	}

	/**
	 * Called at the beginning of a function call to record the name of the function called.
	 * @param identLu The name of the function to call.
	 */
	public void prepareCallFunction(String identLu) {
		IdFunction function = Yaka.tabIdent.getFunction(identLu);
		this.functions.push(function);
		if(function==null) {
			Yaka.errors.addError(Error.IDENT_UNKNOWN, "There is no function with the name '"+identLu+"'.");
		} else {
			this.nbParameters.push(0);
			this.functionsName.push(identLu);
			Yaka.yvm.reserveRetour();
		}
	}

	/**
	 * Called at the end of a function call in the Yaka code.
	 * Check the number of arguments.
	 */
	public void callFunction() {
		IdFunction function = this.functions.pop();
		int nbParams = this.nbParameters.pop();
		String functionName = this.functionsName.pop();
		if(nbParams!=function.getNbParameters()) {
			String message = "Incorrect number of arguments for function "+functionName+".\n";
			message += "Expected "+function.getNbParameters()+" but "+nbParams+" found.";
			Yaka.errors.addError(Error.NUMBER_PARAMETERS_INCORRECT, message);
		}
		Yaka.yvm.callFunction(functionName);
		Yaka.expression.pushFunction(function.getType());
	}
}