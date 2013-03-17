package compilateur;

/**
 * Compute the iteration part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Iteration {
	
	/**
	 * Check that the last expression returned a boolean.
	 * Therefore it can be use as a predicate for the loop.
	 */
	public static void checkExpression() {
		if(Yaka.expression.getType()!=Type.BOOL) {
			System.err.println("Iteration: The expression is not boolean.");
		}
	}
	
	/**
	 * Called at the beginning of a loop.
	 * Generate the label corresponding.
	 */
	public static void tantQue() {
		Yaka.yvm.labelFaire();
	}
	
	/**
	 * Called at the beginning of the loop after the predicate.
	 * Generate the code to go out of the loop.
	 */
	public static void faire() {
		Yaka.yvm.ifFauxFait();
	}
	
	/**
	 * Called at the end of a loop.
	 * Generate the label corresponding.
	 */
	public static void fait() {
		Yaka.yvm.gotoFaire();
		Yaka.yvm.labelFait();		
	}
}