package compilateur;

/**
 * Compute the function part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Function {

	/**
	 * Check the type of the returned expression.
	 */
	public void checkReturnedType() {
		// TODO
	}

	/**
	 * Declare a new parameter for a function using the type given before.
	 * @param identLu The name of the parameter.
	 */
	public void addParam(String identLu) {
		// TODO
	}

	/**
	 * Add a type for the next parameter to be declared.
	 * @param typeLu The type of the parameter.
	 */
	public void addTypeParam(Type typeLu) {
		// TODO
	}

	/**
	 * Check the type of an argument of a function.
	 * Need to check the arguments in the right order.
	 */
	public void checkArgument() {
		// TODO
	}

	/**
	 * Called at the beginning of a function call to record the name of the function called.
	 */
	public void prepareCallFunction() {
		// TODO
	}

	/**
	 * Called at the end of a function call in the Yaka code.
	 * Check the number of arguments.
	 */
	public void callFunction() {
		// TODO
	}
}