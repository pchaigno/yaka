package compilateur;

/**
 * Compute the iteration part.
 */
public class Iteration {
	
	/**
	 * Check that the last expression returned a boolean.
	 * Therefore it can be use as a predicate for the loop.
	 */
	public void checkExpression() {
		if(!Yaka.expression.isBool()) {
			System.err.println("Iteration: The expression is not boolean.");
		}
	}
	
	/**
	 * Called at the beginning of a loop.
	 * Generate the label corresponding.
	 */
	public void tantQue() {
		Yaka.yvm.labelFaire();
	}
	
	/**
	 * Called at the beginning of the loop after the predicate.
	 * Generate the code to go out of the loop.
	 */
	public void faire() {
		Yaka.yvm.ifFaux();
	}
	
	/**
	 * Called at the end of a loop.
	 * Generate the label corresponding.
	 */
	public void fait() {
		Yaka.yvm.gotoFaire();
		Yaka.yvm.labelFait();		
	}
}