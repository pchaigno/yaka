package compilateur;

/**
 * Represent an identificator.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public abstract class Ident {
	protected Type type;
	
	/**
	 * Constructor
	 * @param type The type of the ident.
	 */
	public Ident(Type type) {
		this.type = type;
	}
	
	/**
	 * @return The type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * @return True if it is a constant.
	 */
	public abstract boolean isConst();
	
	/**
	 * @return True if it is a variable.
	 */
	public abstract boolean isVar();
	
	/**
	 * @return True if it is a parameter.
	 */
	public abstract boolean isParam();
	
	/**
	 * @return True if it is a function.
	 */
	public abstract boolean isFunction();
}