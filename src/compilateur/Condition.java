package compilateur;

/**
 * Compute the conditional part.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class Condition {

	/**
	 * Check that the last expression returned a boolean.
	 * Therefore it can be use as a predicate for the condition bloc.
	 */
	public static void checkExpression() {
		if(Yaka.expression.getType()!=Type.BOOL) {
			System.err.println("Condition: The expression is not boolean.");
		}
	}
	
	/**
	 * Called at the beginning of a condition bloc after the predicate.
	 * Generate the code to skip the first part of the condition bloc.
	 */
	public static void alors() {
		Yaka.yvm.ifFauxSinon();
	}
	
	/**
	 * Called for the second part of the condition bloc.
	 * Generate the label corresponding.
	 */
	public static void sinon() {
		Yaka.yvm.gotoFsi();
		Yaka.yvm.labelSinon();
	}
	
	/**
	 * Called at the end of the condition bloc.
	 * Generate the label corresponding.
	 */
	public static void fsi() {
		Yaka.yvm.labelFsi();
	}
}