package compilateur;

/**
 * Represent an identificator of variable.
 * During the declaration of a function, the parameters' offsets are null.
 * At the end of the function, they are computed using the rank.
 * This is a necessary step as we don't know the number of parameters at the beginning.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class IdParam extends IdVar {
	private int rank; // Start at 1.
	
	/**
	 * Constructor
	 * @param type The type.
	 */
	public IdParam(Type type, int rank) {
		super(type, 0);
		this.rank = rank;
	}
	
	/**
	 * Compute the offset using the rank and the number of parameters.
	 * @param nbParameters The number of parameters.
	 */
	public void computeOffset(int nbParameters) {
		System.out.println(nbParameters+" - "+this.rank);
		this.offset = 4+2*nbParameters-2*this.rank;
	}

	@Override
	public boolean isConst() {
		return false;
	}

	@Override
	public boolean isVar() {
		return false;
	}

	@Override
	public boolean isParam() {
		return true;
	}

	@Override
	public boolean isFunction() {
		return false;
	}
}